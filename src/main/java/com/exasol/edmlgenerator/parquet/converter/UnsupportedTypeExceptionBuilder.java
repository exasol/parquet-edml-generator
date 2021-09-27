package com.exasol.edmlgenerator.parquet.converter;

import com.exasol.errorreporting.ExaError;

/**
 * This class builds exception messages for unsupported parquet types.
 */
class UnsupportedTypeExceptionBuilder {

    private UnsupportedTypeExceptionBuilder() {
        // empty on purpose
    }

    /**
     * Build an exception message for an unsupported parquet type.
     * 
     * @param typeName   parquet type name
     * @param columnName name of the column
     * @return build exception
     */
    static UnsupportedOperationException createUnsupportedTypeException(final String typeName,
            final String columnName) {
        return new UnsupportedOperationException(ExaError.messageBuilder("E-PEG-2")
                .message("Unsupported parquet type {{type}} for column {{column name}}.", typeName, columnName)
                .mitigation("Update your parquet files to use a different type.")
                .mitigation(
                        "Create a ticket at https://github.com/exasol/virtual-schema-common-document/issues to support this type.")
                .toString());
    }
}
