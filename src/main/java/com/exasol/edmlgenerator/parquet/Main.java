package com.exasol.edmlgenerator.parquet;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import com.exasol.adapter.document.edml.EdmlDefinition;
import com.exasol.adapter.document.edml.serializer.EdmlSerializer;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

import picocli.CommandLine;

/**
 * This class is the entrypoint for the CLI client.
 */
@CommandLine.Command(name = "parquet-edml-generator", versionProvider = Main.VersionProvider.class, mixinStandardHelpOptions = true, description = "This tool builds EDML mapping definitions for a given parquet file.")
public class Main implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "Parquet file to build a mapping for.")
    private Path parquetFile;

    /**
     * Entrypoint method.
     *
     * @param args command line arguments
     */
    public static void main(final String... args) {
        final int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    @SuppressWarnings("java:S106") // we really want to print to stdout here
    public Integer call() throws Exception {
        final EdmlDefinition edmlDefinition = new ParquetEdmlGenerator().generateEdmlDefinition(this.parquetFile);
        System.out.println(new EdmlSerializer().serialize(edmlDefinition));
        return 0;
    }

    /**
     * This class provides the current project's version.
     */
    static class VersionProvider implements CommandLine.IVersionProvider {
        @Override
        public String[] getVersion() throws Exception {
            return new String[] { MavenProjectVersionGetter.getCurrentProjectVersion() };
        }
    }
}
