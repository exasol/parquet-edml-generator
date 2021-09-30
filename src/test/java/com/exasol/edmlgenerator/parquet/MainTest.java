package com.exasol.edmlgenerator.parquet;

import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.INT32;
import static org.apache.parquet.schema.Type.Repetition.REQUIRED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.itsallcode.junit.sysextensions.AssertExit.assertExit;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.parquet.schema.Types;
import org.itsallcode.io.Capturable;
import org.itsallcode.junit.sysextensions.ExitGuard;
import org.itsallcode.junit.sysextensions.SystemOutGuard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

@ExtendWith(SystemOutGuard.class)
@ExtendWith(ExitGuard.class)
class MainTest {

    @Test
    void testGenerateDefinition(@TempDir final Path tempDir, final Capturable stream) throws IOException {
        final Path parquetFile = tempDir.resolve("test.parquet");
        new ParquetTestSetup(parquetFile, Types.primitive(INT32, REQUIRED).named("element"));
        stream.capture();
        assertExit(() -> Main.main(parquetFile.toString()));
        assertThat(stream.getCapturedData(), startsWith("{\"source\":\"test.parquet\",\"destinationTable\":\"TEST\","));
    }

    @Test
    void testGetVersion(final Capturable stream) {
        stream.capture();
        assertExit(() -> Main.main("-V"));
        assertThat(stream.getCapturedData().trim(), equalTo(MavenProjectVersionGetter.getCurrentProjectVersion()));
    }
}