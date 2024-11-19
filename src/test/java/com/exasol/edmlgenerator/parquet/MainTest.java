package com.exasol.edmlgenerator.parquet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.itsallcode.junit.sysextensions.AssertExit.assertExit;

import java.io.IOException;
import java.nio.file.Path;

import org.itsallcode.io.Capturable;
import org.itsallcode.junit.sysextensions.SystemOutGuard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import com.exasol.adapter.document.edml.EdmlDefinition;
import com.exasol.adapter.document.edml.deserializer.EdmlDeserializer;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

@ExtendWith(SystemOutGuard.class)
class MainTest {

    @Test
    void testGenerateDefinition(@TempDir final Path tempDir, final Capturable stream) throws IOException {
        final Path parquetFile = tempDir.resolve("test.parquet");
        final ParquetTestFixture fixture = new ParquetTestFixture(parquetFile);
        stream.capture();
        assertExit(() -> Main.main(parquetFile.toString()));
        final EdmlDefinition result = new EdmlDeserializer().deserialize(stream.getCapturedData());
        fixture.assertGeneratedEdmlDefinition(result);
    }

    @Test
    void testGetVersion(final Capturable stream) {
        stream.capture();
        assertExit(() -> Main.main("-V"));
        assertThat(stream.getCapturedData().trim(), equalTo(MavenProjectVersionGetter.getCurrentProjectVersion()));
    }
}
