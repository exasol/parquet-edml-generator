package com.exasol.edmlgenerator.parquet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.logging.Logger;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.exasol.adapter.document.edml.EdmlDefinition;
import com.exasol.adapter.document.edml.deserializer.EdmlDeserializer;

class MainIT {
    private static final Logger LOGGER = Logger.getLogger(MainIT.class.getSimpleName());

    @Test
    void testGenerateDefinitionWithJar(@TempDir final Path tempDir) throws IOException, InterruptedException {
        final Path parquetFile = tempDir.resolve("test.parquet");
        final ParquetTestFixture fixture = new ParquetTestFixture(parquetFile);
        final String[] command = { "java", "-jar", "target/parquet-edml-generator.jar", parquetFile.toString() };
        runCommandAndAssertOutput(fixture, command);
    }

    @Test
    @Tag("native-image")
    void testGenerateDefinitionWithNativeImage(@TempDir final Path tempDir) throws IOException, InterruptedException {
        final Path parquetFile = tempDir.resolve("test.parquet");
        final ParquetTestFixture fixture = new ParquetTestFixture(parquetFile);
        final String[] command = { "target/parquet-edml-generator", parquetFile.toString() };
        runCommandAndAssertOutput(fixture, command);
    }

    private void runCommandAndAssertOutput(final ParquetTestFixture fixture, final String[] command)
            throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec(command);
        final int exitCode = process.waitFor();
        final String error = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
        if (!error.isBlank()) {
            LOGGER.severe(error);
        }
        final String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        final EdmlDefinition result = new EdmlDeserializer().deserialize(output);
        assertAll(//
                () -> fixture.assertGeneratedEdmlDefinition(result), //
                () -> assertThat(exitCode, equalTo(0))//
        );
    }
}