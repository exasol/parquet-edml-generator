package com.exasol.edmlgenerator.parquet;

import static com.exasol.edmlgenerator.parquet.ToUpperSnakeCaseConverter.toUpperSnakeCase;

import java.util.Optional;

import org.apache.parquet.schema.*;

import com.exasol.adapter.document.edml.*;
import com.exasol.errorreporting.ExaError;

/**
 * This class creates {@link MappingDefinition}s for parquet columns.
 */
class ParquetColumnToMappingDefinitionConverter {
    private static final int INT_32_DIGITS = getNumberOfDigitsForInt(32);
    private static final int INT_64_DIGITS = getNumberOfDigitsForInt(64);
    private static final int MAX_VARCHAR_COLUMN_SIZE = 2_000_000;

    private static int getNumberOfDigitsForInt(final int numberOfBits) {
        return (int) Math.ceil(Math.log10(Math.pow(2, numberOfBits)));
    }

    private static String buildColumnName(final Type column) {
        return toUpperSnakeCase(column.getName());
    }

    private static UnsupportedOperationException getUnsupportedTypeException(final String typeName,
            final String columnName) {
        return new UnsupportedOperationException(ExaError.messageBuilder("E-PEG-2")
                .message("Unsupported parquet type {{type}} for column {{column name}}.", typeName, columnName)
                .mitigation("Update your parquet files to use a different type.")
                .mitigation(
                        "Create a ticket at https://github.com/exasol/virtual-schema-common-document/issues to support this type.")
                .toString());
    }

    /**
     * Create a mapping definition for a parquet column.
     * 
     * @param column column to create definition for
     * @return generated mapping definition
     */
    MappingDefinition convert(final Type column) {
        final LogicalTypeAnnotation logicalTypeAnnotation = column.getLogicalTypeAnnotation();
        if (logicalTypeAnnotation != null) {
            final LogicalTypeConvertVisitor visitor = new LogicalTypeConvertVisitor(buildColumnName(column));
            logicalTypeAnnotation.accept(visitor);
            return visitor.getResult();
        } else {
            final NonLogicalTypeConvertVisitor visitor = new NonLogicalTypeConvertVisitor();
            column.accept(visitor);
            return visitor.getResult();
        }
    }

    private static class NonLogicalTypeConvertVisitor implements TypeVisitor {
        private MappingDefinition result;

        @Override
        public void visit(final GroupType groupType) {
            groupType.getLogicalTypeAnnotation();
            final Fields.FieldsBuilder fieldsBuilder = Fields.builder();
            for (final Type column : groupType.getFields()) {
                final NonLogicalTypeConvertVisitor visitor = new NonLogicalTypeConvertVisitor();
                column.accept(visitor);
                fieldsBuilder.mapField(column.getName(), visitor.getResult());
            }
            this.result = fieldsBuilder.build();
        }

        @Override
        public void visit(final MessageType messageType) {
            // message type is just a special group type
            this.visit((GroupType) messageType);
        }

        @Override
        public void visit(final PrimitiveType primitiveType) {
            this.result = buildMappingForPrimitiveType(primitiveType);
        }

        private MappingDefinition buildMappingForPrimitiveType(final PrimitiveType primitiveType) {
            final PrimitiveType.PrimitiveTypeName primitiveTypeName = primitiveType.getPrimitiveTypeName();
            switch (primitiveTypeName) {
            case INT32:
                return buildToDecimalMapping(primitiveType, INT_32_DIGITS);
            case INT64:
                return buildToDecimalMapping(primitiveType, INT_64_DIGITS);
            case INT96:
                return ToTimestampMapping.builder().destinationName(buildColumnName(primitiveType)).build();
            case BOOLEAN:
                return ToBoolMapping.builder().destinationName(buildColumnName(primitiveType)).build();
            case BINARY:
                return ToVarcharMapping.builder().destinationName(buildColumnName(primitiveType))
                        .varcharColumnSize(MAX_VARCHAR_COLUMN_SIZE).build();
            case FIXED_LEN_BYTE_ARRAY:
                return ToVarcharMapping.builder().destinationName(buildColumnName(primitiveType))
                        .varcharColumnSize(primitiveType.getTypeLength()).build();
            case FLOAT:
            case DOUBLE:
                return ToDoubleMapping.builder().destinationName(buildColumnName(primitiveType)).build();
            default:
                throw getUnsupportedTypeException(primitiveTypeName + "-primitive-type",
                        buildColumnName(primitiveType));
            }
        }

        private ToDecimalMapping buildToDecimalMapping(final PrimitiveType primitiveType, final int precision) {
            return ToDecimalMapping.builder().decimalPrecision(precision).decimalScale(0)
                    .destinationName(buildColumnName(primitiveType)).build();
        }

        /**
         * Get the result.
         * 
         * @return result
         */
        public MappingDefinition getResult() {
            return this.result;
        }
    }

    /**
     * Converter for parquet logical types. See: https://github.com/apache/parquet-format/blob/master/LogicalTypes.md
     */
    private static class LogicalTypeConvertVisitor implements LogicalTypeAnnotation.LogicalTypeAnnotationVisitor<Void> {
        private final String columnName;
        private MappingDefinition result;

        private LogicalTypeConvertVisitor(final String columnName) {
            this.columnName = columnName;
        }

        /**
         * Get the result.
         *
         * @return result
         */
        public MappingDefinition getResult() {
            return this.result;
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
            this.result = ToDecimalMapping.builder().decimalPrecision(decimalLogicalType.getPrecision())
                    .decimalScale(decimalLogicalType.getScale()).destinationName(this.columnName).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
            this.result = ToVarcharMapping.builder().destinationName(this.columnName)
                    .varcharColumnSize(MAX_VARCHAR_COLUMN_SIZE).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.MapLogicalTypeAnnotation mapLogicalType) {
            this.result = getToJsonMapping();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.ListLogicalTypeAnnotation listLogicalType) {
            this.result = getToJsonMapping();
            return Optional.empty();
        }

        private ToJsonMapping getToJsonMapping() {
            return ToJsonMapping.builder().destinationName(this.columnName).varcharColumnSize(MAX_VARCHAR_COLUMN_SIZE)
                    .build();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
            throw getUnsupportedTypeException("enum-logical-type", this.columnName);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
            this.result = ToDateMapping.builder().destinationName(this.columnName).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
            this.result = ToDecimalMapping.builder().decimalPrecision(INT_64_DIGITS).decimalScale(0)
                    .destinationName(this.columnName).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
            this.result = ToTimestampMapping.builder().destinationName(this.columnName).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
            this.result = ToDecimalMapping.builder()
                    .decimalPrecision(getNumberOfDigitsForInt(intLogicalType.getBitWidth())).decimalScale(0)
                    .destinationName(this.columnName).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
            this.result = ToVarcharMapping.builder().destinationName(this.columnName)
                    .varcharColumnSize(MAX_VARCHAR_COLUMN_SIZE).build();
            return Optional.empty();
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
            throw getUnsupportedTypeException("bson-logical-type", this.columnName);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.UUIDLogicalTypeAnnotation uuidLogicalType) {
            throw getUnsupportedTypeException("uuid-logical-type", this.columnName);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
            throw getUnsupportedTypeException("interval-logical-type", this.columnName);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.MapKeyValueTypeAnnotation mapKeyValueLogicalType) {
            throw getUnsupportedTypeException("map-key-value-logical-type", this.columnName);
        }
    }
}
