# Solution Strategy

## Main Technical Approach

The CLI parses a local path, an adapter backed by `RandomAccessFile` exposes seekable input to Apache Parquet, and `ParquetEdmlGenerator` reads the file schema. A recursive converter maps Parquet groups and primitive or logical types into EDML mapping objects. The EDML serializer then writes the result to standard output.

## Key Quality Drivers

* Portability through a Java JAR and native binaries.
* Predictable schema conversion, verified with unit and integration tests.
* A simple command-line interaction with no application-managed persistent state.

## Reuse of Existing Facilities

The implementation uses Picocli for CLI parsing, Apache Parquet for schema access, Exasol EDML Java for mapping objects and serialization, and the project's error-reporting library for structured diagnostic messages.

## Data and Control Flow Strategy

The input path flows from the CLI to the generator and local input adapter. The Parquet reader extracts metadata schema, converters recursively map schema nodes to EDML fields, and the serializer produces the CLI output. Conversion failures propagate as exceptions.
