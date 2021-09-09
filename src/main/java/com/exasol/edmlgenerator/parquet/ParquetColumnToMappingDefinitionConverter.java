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
    private static final int INT_96_DIGITS = getNumberOfDigitsForInt(96);

    private static int getNumberOfDigitsForInt(final int numberOfBits) {
        return (int) Math.ceil(Math.log10(Math.pow(2, numberOfBits)));
    }

    /**
     * Create a mapping definition for a parquet column.
     * 
     * @param column column to create definition for
     * @return generated mapping definition
     */
    MappingDefinition convert(final Type column) {
        final ConvertVisitor visitor = new ConvertVisitor();
        column.accept(visitor);
        return visitor.getResult();
    }

    private static class ConvertVisitor
            implements TypeVisitor, LogicalTypeAnnotation.LogicalTypeAnnotationVisitor<Void> {
        private MappingDefinition result;

        @Override
        public void visit(final GroupType groupType) {
            final Fields.FieldsBuilder fieldsBuilder = Fields.builder();
            for (final Type column : groupType.getFields()) {
                final ConvertVisitor visitor = new ConvertVisitor();
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
            final LogicalTypeAnnotation logicalTypeAnnotation = primitiveType.getLogicalTypeAnnotation();
            if (logicalTypeAnnotation != null) {
                final LogicalTypeConvertVisitor visitor = new LogicalTypeConvertVisitor(buildColumnName(primitiveType));
                logicalTypeAnnotation.accept(visitor);
                this.result = visitor.getResult();
            } else {
                this.result = buildMappingForPrimitiveType(primitiveType);
            }
        }

        private MappingDefinition buildMappingForPrimitiveType(final PrimitiveType primitiveType) {
            final PrimitiveType.PrimitiveTypeName primitiveTypeName = primitiveType.getPrimitiveTypeName();
            switch (primitiveTypeName) {
            case INT32:
                return buildToDecimalMapping(primitiveType, INT_32_DIGITS);
            case INT64:
                return buildToDecimalMapping(primitiveType, INT_64_DIGITS);
            case INT96:
                return buildToDecimalMapping(primitiveType, INT_96_DIGITS);
            default:
                throw new UnsupportedOperationException(ExaError.messageBuilder("F-PEG-2")
                        .message("Unsupported parquet type  {{type}} for column {{name}}.", primitiveTypeName,
                                primitiveType.getName())
                        .ticketMitigation().toString());
            }
        }

        private ToDecimalMapping buildToDecimalMapping(final PrimitiveType primitiveType, final int precision) {
            return ToDecimalMapping.builder().decimalPrecision(precision).decimalScale(0)
                    .destinationName(buildColumnName(primitiveType)).build();
        }

        private String buildColumnName(final Type column) {
            return toUpperSnakeCase(column.getName().toUpperCase());
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
            throw getUnsupportedException(stringLogicalType);
        }

        private UnsupportedOperationException getUnsupportedException(final LogicalTypeAnnotation type) {
            return new UnsupportedOperationException(ExaError.messageBuilder("F-PEG-3")
                    .message("Unsupported logical parquet type {{type}} for column {{name}}.", type, this.columnName)
                    .ticketMitigation().toString());
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.MapLogicalTypeAnnotation mapLogicalType) {
            throw getUnsupportedException(mapLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.ListLogicalTypeAnnotation listLogicalType) {
            throw getUnsupportedException(listLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
            throw getUnsupportedException(enumLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
            throw getUnsupportedException(dateLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
            throw getUnsupportedException(timeLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
            throw getUnsupportedException(timestampLogicalType);
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
            throw getUnsupportedException(jsonLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
            throw getUnsupportedException(bsonLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.UUIDLogicalTypeAnnotation uuidLogicalType) {
            throw getUnsupportedException(uuidLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
            throw getUnsupportedException(intervalLogicalType);
        }

        @Override
        public Optional<Void> visit(final LogicalTypeAnnotation.MapKeyValueTypeAnnotation mapKeyValueLogicalType) {
            throw getUnsupportedException(mapKeyValueLogicalType);
        }
    }
}
