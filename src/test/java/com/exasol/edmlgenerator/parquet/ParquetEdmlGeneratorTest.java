package com.exasol.edmlgenerator.parquet;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.exasol.adapter.document.edml.EdmlDefinition;

class ParquetEdmlGeneratorTest {

    @Test
    void testGenerateEdmlDefinition(@TempDir final Path tempDir) throws IOException {
        final Path parquetFile = tempDir.resolve("test.parquet");
        final ParquetTestFixture fixture = new ParquetTestFixture(parquetFile);
        final EdmlDefinition edmlDefinition = new ParquetEdmlGenerator().generateEdmlDefinition(parquetFile);
        fixture.assertGeneratedEdmlDefinition(edmlDefinition);
    }

    @Test
    void testFailedToOpen(@TempDir final Path tempDir) {
        final Path nonExistingFile = tempDir.resolve("nonExistingFile.parquet");
        final ParquetEdmlGenerator generator = new ParquetEdmlGenerator();
        final IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> generator.generateEdmlDefinition(nonExistingFile));
        assertThat(exception.getMessage(), startsWith("E-PEG-1: Failed to read parquet file "));
    }
}