package com.exasol.edmlgenerator.parquet;

import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.INT32;
import static org.apache.parquet.schema.Type.Repetition.REQUIRED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.apache.parquet.schema.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exasol.adapter.document.edml.ToDecimalMapping;

class ParquetColumnToMappingDefinitionConverterTest {

    @ParameterizedTest
    @CsvSource({ //
            "INT32, 10", "INT64, 20", "INT96, 29", })
    void testConvertIntType(final PrimitiveType.PrimitiveTypeName type, final int expectedPrecision) {
        final PrimitiveType intType = Types.primitive(type, REQUIRED).named("id");
        assertConvertsToToDecimalMapping(intType, 0, expectedPrecision, "ID");
    }

    @Test
    void testConvertLogicalTypeDecimal() {
        final Type decimalType = Types.primitive(INT32, REQUIRED).as(LogicalTypeAnnotation.decimalType(2, 8))
                .named("price");
        assertConvertsToToDecimalMapping(decimalType, 2, 8, "PRICE");
    }

    @Test
    void testConvertLogicalTypeInt() {
        final Type logicalIntType = Types.primitive(INT32, REQUIRED).as(LogicalTypeAnnotation.intType(8, true))
                .named("id");
        assertConvertsToToDecimalMapping(logicalIntType, 0, 3, "ID");
    }

    private void assertConvertsToToDecimalMapping(final Type type, final int scale, final int precision,
            final String name) {
        final ToDecimalMapping toDecimalMapping = (ToDecimalMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(type);
        assertAll(//
                () -> assertThat(toDecimalMapping.getDecimalScale(), equalTo(scale)),
                () -> assertThat(toDecimalMapping.getDecimalPrecision(), equalTo(precision)),
                () -> assertThat(toDecimalMapping.getDestinationName(), equalTo(name))//
        );
    }
}