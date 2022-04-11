package com.exasol.edmlgenerator.parquet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.parquet.io.SeekableInputStream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

class LocalInputFileTest {
    private static final byte[] TEST_CONTENT = "my test content 12234567890".getBytes(StandardCharsets.UTF_8);
    @TempDir
    static Path tempDir;
    private static LocalInputFile localInputFile;

    @BeforeAll
    static void beforeAll() throws IOException {
        final Path testFile = tempDir.resolve("testFile.txt");
        Files.write(testFile, TEST_CONTENT);
        localInputFile = new LocalInputFile(testFile);
    }

    @Test
    void getSize() {
        assertThat(localInputFile.getLength(), equalTo((long) TEST_CONTENT.length));
    }

    @Test
    void testSingleByteRead() throws IOException {
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            assertThat(inputStream.read(), equalTo((int) TEST_CONTENT[0]));
        }
    }

    @Test
    void testGetPos() throws IOException {
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            assertThat(inputStream.getPos(), equalTo(0L));
            inputStream.read();
            assertThat(inputStream.getPos(), equalTo(1L));
        }
    }

    @Test
    void testSeek() throws IOException {
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            assertThat(inputStream.getPos(), equalTo(0L));
            inputStream.seek(10);
            assertThat(inputStream.getPos(), equalTo(10L));
        }
    }

    @Test
    void testReadFully() throws IOException {
        final byte[] result = new byte[TEST_CONTENT.length];
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            inputStream.readFully(result);
        }
        assertThat(result, equalTo(TEST_CONTENT));
    }

    @Test
    void testReadToByteBuffer() throws IOException {
        final byte[] result = new byte[TEST_CONTENT.length];
        final ByteBuffer byteBuffer = ByteBuffer.wrap(result);
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            inputStream.read(byteBuffer);
        }
        assertThat(result, equalTo(TEST_CONTENT));
    }

    @Test
    void testReadFullyToByteBuffer() throws IOException {
        final byte[] result = new byte[TEST_CONTENT.length];
        final ByteBuffer byteBuffer = ByteBuffer.wrap(result);
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            inputStream.readFully(byteBuffer);
        }
        assertThat(result, equalTo(TEST_CONTENT));
    }

    @Test
    void testReadFullyToByteBufferWithTooLargeBuffer() throws IOException {
        final byte[] result = new byte[TEST_CONTENT.length + 1];
        final ByteBuffer byteBuffer = ByteBuffer.wrap(result);
        try (final SeekableInputStream inputStream = localInputFile.newStream()) {
            final EOFException exception = Assertions.assertThrows(EOFException.class,
                    () -> inputStream.readFully(byteBuffer));
            assertThat(exception.getMessage(),
                    equalTo("F-PEG-5: Reached the end of stream with 1 bytes left to read."));
            ;
        }
    }

    @Test
    void testNonExistingFile() {
        final Path nonExistingPath = tempDir.resolve("nonExisting");
        final UncheckedIOException exception = Assertions.assertThrows(UncheckedIOException.class,
                () -> new LocalInputFile(nonExistingPath));
        assertThat(exception.getMessage(), startsWith("E-PEG-4: Failed to get size of parquet file "));
    }
}