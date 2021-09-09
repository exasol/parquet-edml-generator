package com.exasol.edmlgenerator.parquet;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;

public class ParquetTestSetup {

    public ParquetTestSetup(final Path fileToWriteTo, final Type... columnTypes) throws IOException {
        final MessageType schema = new MessageType("test", columnTypes);
        final ParquetWriter<Group> parquetWriter = new ParquetTestWriterBuilder(fileToWriteTo, schema).build();
        parquetWriter.close();
    }
}
