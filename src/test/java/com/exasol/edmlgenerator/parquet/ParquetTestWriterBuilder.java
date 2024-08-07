package com.exasol.edmlgenerator.parquet;

import java.nio.file.Path;
import java.util.Collections;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;

public class ParquetTestWriterBuilder extends ParquetWriter.Builder<Group, ParquetTestWriterBuilder> {
    private final MessageType schema;

    public ParquetTestWriterBuilder(final Path destinationFile, final MessageType schema) {
        super(new LocalOutputFile(destinationFile));
        this.schema = schema;
    }

    @Override
    protected ParquetTestWriterBuilder self() {
        return this;
    }

    @SuppressWarnings("deprecation") // Argument type is now ParquetConfiguration
    @Override
    protected WriteSupport<Group> getWriteSupport(final Configuration conf) {
        return new MyGroupWriteSupport(this.schema);
    }

    @Override
    protected WriteSupport<Group> getWriteSupport(final ParquetConfiguration conf) {
        return getWriteSupport(ConfigurationUtil.createHadoopConfiguration(conf));
    }

    private static class MyGroupWriteSupport extends WriteSupport<Group> {
        private final MessageType schema;
        private GroupWriter writer;

        private MyGroupWriteSupport(final MessageType schema) {
            this.schema = schema;
        }

        @SuppressWarnings("deprecation") // Argument type is now ParquetConfiguration
        @Override
        public WriteContext init(final Configuration configuration) {
            return new WriteContext(this.schema, Collections.emptyMap());
        }

        @Override
        public WriteContext init(final ParquetConfiguration configuration) {
            return init(ConfigurationUtil.createHadoopConfiguration(configuration));
        }

        @Override
        public void prepareForWrite(final RecordConsumer recordConsumer) {
            this.writer = new GroupWriter(recordConsumer, this.schema);
        }

        @Override
        public void write(final Group group) {
            this.writer.write(group);
        }
    }
}
