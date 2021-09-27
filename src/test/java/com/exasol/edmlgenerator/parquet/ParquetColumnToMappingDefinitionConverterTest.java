package com.exasol.edmlgenerator.parquet;

import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.*;
import static org.apache.parquet.schema.Type.Repetition.REQUIRED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.apache.parquet.schema.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import com.exasol.adapter.document.edml.*;

class ParquetColumnToMappingDefinitionConverterTest {

    private static Stream<Arguments> testConvertToStringCases() {
        return Stream.of(//
                Arguments.of(LogicalTypeAnnotation.stringType()), //
                Arguments.of(LogicalTypeAnnotation.jsonType()) //
        );
    }

    static Stream<Arguments> getUnsupportedTypes() {
        return Stream.of(//
                Arguments.of(Types.primitive(BINARY, REQUIRED).as(LogicalTypeAnnotation.bsonType()).named("bson")), //
                Arguments.of(Types.primitive(FIXED_LEN_BYTE_ARRAY, REQUIRED).length(16)
                        .as(LogicalTypeAnnotation.uuidType()).named("id")), //
                Arguments.of(Types.primitive(FIXED_LEN_BYTE_ARRAY, REQUIRED).length(12)
                        .as(LogicalTypeAnnotation.IntervalLogicalTypeAnnotation.getInstance()).named("interval")), //
                Arguments.of(Types.primitive(BINARY, REQUIRED).length(12).as(LogicalTypeAnnotation.enumType())
                        .named("my_enum")), //
                Arguments.of(Types.requiredGroup().as(LogicalTypeAnnotation.MapKeyValueTypeAnnotation.getInstance())
                        .named("my_enum"))//
        );
    }

    @ParameterizedTest
    @CsvSource({ //
            "INT32, 10", "INT64, 20" })
    void testConvertIntType(final PrimitiveType.PrimitiveTypeName type, final int expectedPrecision) {
        final PrimitiveType intType = Types.primitive(type, REQUIRED).named("id");
        assertConvertsToToDecimalMapping(intType, 0, expectedPrecision, "ID");
    }

    @Test
    void testConvertBooleanType() {
        final PrimitiveType boolType = Types.primitive(BOOLEAN, REQUIRED).named("isActive");
        final ToBoolMapping toBoolMapping = (ToBoolMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(boolType);
        assertThat(toBoolMapping.getDestinationName(), equalTo("IS_ACTIVE"));
    }

    @Test
    void testConvertBinaryType() {
        final PrimitiveType type = Types.primitive(BINARY, REQUIRED).named("myString");
        assertConvertsToToVarcharMapping(type, "MY_STRING", 2_000_000);
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

    @Test
    void testConvertFixedLengthByteArrayType() {
        final PrimitiveType type = Types.primitive(FIXED_LEN_BYTE_ARRAY, REQUIRED).length(123).named("myString");
        assertConvertsToToVarcharMapping(type, "MY_STRING", 123);
    }

    @ParameterizedTest
    @CsvSource({ "FLOAT", "DOUBLE" })
    void testConvertFloatTypes(final PrimitiveType.PrimitiveTypeName floatingPointType) {
        final PrimitiveType boolType = Types.primitive(floatingPointType, REQUIRED).named("score");
        final ToDoubleMapping toDoubleMapping = (ToDoubleMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(boolType);
        assertThat(toDoubleMapping.getDestinationName(), equalTo("SCORE"));
    }

    @Test
    void testConvertLogicalTypeDate() {
        final Type logicalDateType = Types.primitive(INT32, REQUIRED).as(LogicalTypeAnnotation.dateType())
                .named("birthday");
        final ToDateMapping toDateMapping = (ToDateMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(logicalDateType);
        assertThat(toDateMapping.getDestinationName(), equalTo("BIRTHDAY"));
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

    @Test
    void testConvertLogicalTypeTime() {
        final Type logicalTimeType = Types.primitive(INT32, REQUIRED)
                .as(LogicalTypeAnnotation.timeType(true, LogicalTypeAnnotation.TimeUnit.MILLIS)).named("myTime");
        assertConvertsToToDecimalMapping(logicalTimeType, 0, 20, "MY_TIME");
    }

    @Test
    void testConvertLogicalTypeTimestamp() {
        final Type logicalTimestampType = Types.primitive(INT64, REQUIRED)
                .as(LogicalTypeAnnotation.timestampType(true, LogicalTypeAnnotation.TimeUnit.MILLIS))
                .named("myTimestamp");
        final ToTimestampMapping toTimestampMapping = (ToTimestampMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(logicalTimestampType);
        assertAll(//
                () -> assertThat(toTimestampMapping.getDestinationName(), equalTo("MY_TIMESTAMP")), //
                () -> assertThat(toTimestampMapping.isUseTimestampWithLocalTimezoneType(), equalTo(true))//
        );
    }

    @Test
    void testConvertInt96ToTimestamp() {
        final Type logicalTimestampType = Types.primitive(INT96, REQUIRED).named("myTimestamp");
        final ToTimestampMapping toTimestampMapping = (ToTimestampMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(logicalTimestampType);
        assertAll(//
                () -> assertThat(toTimestampMapping.getDestinationName(), equalTo("MY_TIMESTAMP")), //
                () -> assertThat(toTimestampMapping.isUseTimestampWithLocalTimezoneType(), equalTo(true))//
        );
    }

    @ParameterizedTest
    @MethodSource("testConvertToStringCases")
    void testConvertToString(final LogicalTypeAnnotation annotation) {
        final Type logicalStringType = Types.primitive(BINARY, REQUIRED).as(annotation).named("value");
        assertConvertsToToVarcharMapping(logicalStringType, "VALUE", 2_000_000);
    }

    private void assertConvertsToToVarcharMapping(final Type type, final String expectedName, final int expectedSize) {
        final ToVarcharMapping toVarcharMapping = (ToVarcharMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(type);
        assertAll(//
                () -> assertThat(toVarcharMapping.getVarcharColumnSize(), equalTo(expectedSize)),
                () -> assertThat(toVarcharMapping.getDestinationName(), equalTo(expectedName))//
        );
    }

    @Test
    void testConvertList() {
        final Type itemType = Types.primitive(INT32, REQUIRED).named("element");
        final GroupType listType = Types.list(REQUIRED).element(itemType).named("ids");
        System.out.println(listType.toString());
        assertConvertsToJsonMapping(listType, "IDS");
    }

    @Test
    void testConvertMap() {
        final Type keyType = Types.primitive(BINARY, REQUIRED).as(LogicalTypeAnnotation.stringType()).named("key");
        final Type valueType = Types.primitive(INT32, REQUIRED).named("value");
        final GroupType listType = Types.map(REQUIRED).key(keyType).value(valueType).named("scores");
        System.out.println(listType.toString());
        assertConvertsToJsonMapping(listType, "SCORES");
    }

    @Test
    void testConvertEnum() {
        final Type keyType = Types.primitive(BINARY, REQUIRED).as(LogicalTypeAnnotation.stringType()).named("key");
        final Type valueType = Types.primitive(INT32, REQUIRED).named("value");
        final GroupType listType = Types.map(REQUIRED).key(keyType).value(valueType).named("scores");
        System.out.println(listType.toString());
        assertConvertsToJsonMapping(listType, "SCORES");
    }

    private void assertConvertsToJsonMapping(final GroupType listType, final String expectedName) {
        final ToJsonMapping toJsonMapping = (ToJsonMapping) new ParquetColumnToMappingDefinitionConverter()
                .convert(listType);
        assertAll(//
                () -> assertThat(toJsonMapping.getVarcharColumnSize(), equalTo(2_000_000)),
                () -> assertThat(toJsonMapping.getDestinationName(), equalTo(expectedName))//
        );
    }

    @ParameterizedTest
    @MethodSource("getUnsupportedTypes")
    void testUnsupported(final Type unsupportedType) {
        final ParquetColumnToMappingDefinitionConverter converter = new ParquetColumnToMappingDefinitionConverter();
        final UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> converter.convert(unsupportedType));
        assertThat(exception.getMessage(), startsWith("E-PEG-2: Unsupported parquet type"));

    }
}