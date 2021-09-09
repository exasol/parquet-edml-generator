package com.exasol.edmlgenerator.parquet;

import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.INT32;
import static org.apache.parquet.schema.Type.Repetition.REQUIRED;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.parquet.schema.Types;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.exasol.adapter.document.edml.EdmlDefinition;
import com.exasol.adapter.document.edml.Fields;

class ParquetEdmlGeneratorTest {

    @Test
    void testGenerateEdmlDefinition(@TempDir final Path tempDir) throws IOException {
        final Path parquetFile = tempDir.resolve("test.parquet");
        new ParquetTestSetup(parquetFile, Types.primitive(INT32, REQUIRED).named("element"));
        final EdmlDefinition edmlDefinition = new ParquetEdmlGenerator().generateEdmlDefinition(parquetFile);
        assertAll(//
                () -> assertThat(edmlDefinition.getMapping(), instanceOf(Fields.class)),
                () -> assertThat(edmlDefinition.getSource(), equalTo("test.parquet")),
                () -> assertThat(edmlDefinition.getDestinationTable(), equalTo("TEST")),
                () -> assertThat(edmlDefinition.getSchema(), startsWith("https://schemas.exasol.com/edml-"))//
        );
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