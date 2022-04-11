package com.exasol.edmlgenerator.parquet;

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
}