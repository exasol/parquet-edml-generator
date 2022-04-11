package com.exasol.edmlgenerator.parquet;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.SeekableInputStream;

import com.exasol.errorreporting.ExaError;

class LocalInputFile implements InputFile {
    private static final int COPY_BUFFER_SIZE = 8192;
    private final long size;
    private final Path localFile;

    LocalInputFile(final Path localFile) {
        this.localFile = localFile;
        try {
            this.size = Files.size(localFile);
        } catch (final IOException exception) {
            throw new UncheckedIOException(
                    ExaError.messageBuilder("E-PEG-4").message("Failed to get size of parquet file {{file}}.", this.localFile).toString(),
                    exception);
        }
    }

    @Override
    public long getLength() {
        return this.size;
    }

    @Override
    public SeekableInputStream newStream() {
        return new LocalFileSeekableInputStream(this.localFile);
    }

    @FunctionalInterface
    private interface ByteBufReader {
        int read(byte[] b, int off, int len) throws IOException;
    }

    private static class LocalFileSeekableInputStream extends SeekableInputStream {
        private final byte[] tmpBuf = new byte[COPY_BUFFER_SIZE];
        private final RandomAccessFile randomAccessFile;

        private LocalFileSeekableInputStream(final Path localFile) {
            try {
                this.randomAccessFile = new RandomAccessFile(localFile.toFile(), "r");
            } catch (final FileNotFoundException exception) {
                throw new UncheckedIOException(
                        ExaError.messageBuilder("E-PEG-3").message("Failed to open parquet file.").toString(),
                        exception);
            }
        }

        private static int readDirectBuffer(final ByteBuffer byteBufr, final byte[] tmpBuf, final ByteBufReader rdr)
                throws IOException {
            // copy all the bytes that return immediately, stopping at the first
            // read that doesn't return a full buffer.
            int nextReadLength = Math.min(byteBufr.remaining(), tmpBuf.length);
            int totalBytesRead = 0;
            int bytesRead;

            while ((bytesRead = rdr.read(tmpBuf, 0, nextReadLength)) == tmpBuf.length) {
                byteBufr.put(tmpBuf);
                totalBytesRead += bytesRead;
                nextReadLength = Math.min(byteBufr.remaining(), tmpBuf.length);
            }

            if (bytesRead < 0) {
                // return -1 if nothing was read
                return totalBytesRead == 0 ? -1 : totalBytesRead;
            } else {
                // copy the last partial buffer
                byteBufr.put(tmpBuf, 0, bytesRead);
                totalBytesRead += bytesRead;
                return totalBytesRead;
            }
        }

        private static void readFullyDirectBuffer(final ByteBuffer byteBufr, final byte[] tmpBuf,
                final ByteBufReader rdr) throws IOException {
            int nextReadLength = Math.min(byteBufr.remaining(), tmpBuf.length);
            int bytesRead = 0;

            while (nextReadLength > 0 && (bytesRead = rdr.read(tmpBuf, 0, nextReadLength)) >= 0) {
                byteBufr.put(tmpBuf, 0, bytesRead);
                nextReadLength = Math.min(byteBufr.remaining(), tmpBuf.length);
            }

            if (bytesRead < 0 && byteBufr.remaining() > 0) {
                throw new EOFException(ExaError.messageBuilder("F-PEG-5")
                        .message("Reached the end of stream with {{remaining}} bytes left to read.",
                                byteBufr.remaining())
                        .toString());
            }
        }

        @Override
        public long getPos() throws IOException {
            return this.randomAccessFile.getFilePointer();
        }

        @Override
        public void seek(final long pos) throws IOException {
            this.randomAccessFile.seek(pos);
        }

        @Override
        public void readFully(final byte[] bytes) throws IOException {
            readFully(bytes, 0, bytes.length);
        }

        @Override
        public void readFully(final byte[] bytes, final int offset, final int length) throws IOException {
            int n = 0;
            do {
                final int count = this.read(bytes, offset + n, length - n);
                if (count < 0) {
                    throw new EOFException();
                }
                n += count;
            } while (n < length);
        }

        @Override
        public int read(final ByteBuffer byteBuffer) throws IOException {
            return readDirectBuffer(byteBuffer, this.tmpBuf, this.randomAccessFile::read);
        }

        @Override
        public void readFully(final ByteBuffer byteBuffer) throws IOException {
            readFullyDirectBuffer(byteBuffer, this.tmpBuf, this.randomAccessFile::read);
        }

        @Override
        public int read() throws IOException {
            return this.randomAccessFile.read();
        }

        @Override
        public void close() throws IOException {
            this.randomAccessFile.close();
        }
    }
}
