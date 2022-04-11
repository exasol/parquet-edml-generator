package com.exasol.edmlgenerator.parquet;

import java.io.*;
import java.nio.file.Path;

import org.apache.parquet.io.OutputFile;
import org.apache.parquet.io.PositionOutputStream;

public class LocalOutputFile implements OutputFile {
    private final Path target;

    public LocalOutputFile(final Path target) {
        this.target = target;
    }

    @Override
    public PositionOutputStream create(final long blockSizeHint) throws IOException {
        return new LocalPositionOutputStream(new FileOutputStream(this.target.toFile()));
    }

    @Override
    public PositionOutputStream createOrOverwrite(final long blockSizeHint) throws IOException {
        return new LocalPositionOutputStream(new FileOutputStream(this.target.toFile()));
    }

    @Override
    public boolean supportsBlockSize() {
        return false;
    }

    @Override
    public long defaultBlockSize() {
        return 0;
    }

    @Override
    public String getPath() {
        return this.target.toString();
    }

    private static class LocalPositionOutputStream extends PositionOutputStream {
        private final OutputStream target;
        long position = 0;

        private LocalPositionOutputStream(final OutputStream target) {
            this.target = target;
        }

        @Override
        public void write(final int b) throws IOException {
            this.target.write(b);
            this.position++;
        }

        @Override
        public void write(final byte[] bytes) throws IOException {
            this.target.write(bytes);
            this.position += bytes.length;
        }

        @Override
        public void write(final byte[] bytes, final int off, final int len) throws IOException {
            this.target.write(bytes, off, len);
            this.position += len;
        }

        @Override
        public void flush() throws IOException {
            this.target.flush();
        }

        @Override
        public void close() throws IOException {
            this.target.close();
        }

        @Override
        public long getPos() {
            return this.position;
        }
    }
}
