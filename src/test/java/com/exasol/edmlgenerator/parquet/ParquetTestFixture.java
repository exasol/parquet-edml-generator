package com.exasol.edmlgenerator.parquet;

import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.INT32;
import static org.apache.parquet.schema.Type.Repetition.REQUIRED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.schema.*;

import com.exasol.adapter.document.edml.*;

public class ParquetTestFixture {

    public ParquetTestFixture(final Path fileToWriteTo) throws IOException {
        final Type[] columnTypes = new Type[] { Types.primitive(INT32, REQUIRED).named("element") };
        final MessageType schema = new MessageType("test", columnTypes);
        final ParquetWriter<Group> parquetWriter = new ParquetTestWriterBuilder(fileToWriteTo, schema).build();
        parquetWriter.close();
    }

    public void assertGeneratedEdmlDefinition(final EdmlDefinition edmlDefinition) {
        final Fields fields = (Fields) edmlDefinition.getMapping();
        final ToDecimalMapping toDecimalMapping = (ToDecimalMapping) fields.getFieldsMap().get("element");
        assertAll(//
                () -> assertThat(toDecimalMapping.getDecimalPrecision(), equalTo(10)),
                () -> assertThat(toDecimalMapping.getDecimalScale(), equalTo(0)),
                () -> assertThat(edmlDefinition.getSource(), equalTo("test.parquet")),
                () -> assertThat(edmlDefinition.getDestinationTable(), equalTo("TEST")));
    }
}
