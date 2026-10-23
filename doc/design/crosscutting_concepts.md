# Crosscutting Concepts

## Domain Model

The input Parquet schema is represented by Apache Parquet schema nodes. The output EDML definition contains a source filename, a destination table name derived from the filename, a generated description, and recursively converted field mappings.

## Configuration

The input path is supplied as a CLI argument. That is the only configuration parameter.

## Error Handling

File size and open failures are wrapped in structured unchecked I/O errors. Parquet schema read failures become an `IllegalStateException`. Unsupported types raise an `UnsupportedOperationException` with an error code, type, column, and mitigation text.

## Logging and Observability

Successful EDML output is written to standard output. There is no application logging, metrics, tracing, or telemetry.

## Security and Privacy

The application reads a user-selected local file and emits schema-derived EDML. No authentication, authorization, secret storage, telemetry, or explicit network communication is present in the application.
