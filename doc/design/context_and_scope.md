# Context and Scope

This chapter describes the technical context, neighboring systems, external interfaces, and system boundary. Terms use the definitions from [User and System Requirements](../requirements/requirements.md).

## System Boundary

The application accepts a local Parquet file path, reads the file schema, constructs an EDML definition, serializes it, and writes it to standard output. The downstream use of the generated definition is outside the application boundary.

## Users and Neighboring Systems

* Data engineers invoke the command-line application.
* The local file system supplies the Parquet input.
* Apache Parquet libraries read file metadata and schema.
* EDML Java libraries provide the definition model and serializer.
* A downstream Exasol integration consumes the generated EDML.

## Supported Environment

PEG provides self-contained native executables for Windows and Linux, and a Java JAR that runs with Java 21 or higher.

## External Interfaces

The CLI accepts one Parquet file path. Picocli supplies standard help and version options. Successful execution writes serialized EDML to standard output.

## State and Persistence

The implementation reads the input file and writes the generated definition to standard output. The application does not persist state. The application does not contain a network client.

## Explicit Non-Goals

* Editing Parquet files.
* Persisting EDML output to a destination file on behalf of the user.
* Native macOS distribution.
