# Architecture Decisions

This chapter records architecture decisions inferred from the current implementation. The repository contains no formal architecture decision records, so these entries remain drafts.

## Local Command-Line Processing

### Process a File in a Local CLI

The application reads a local Parquet file and writes the generated EDML to standard output in one process.

Considered alternatives:

1. A hosted service or remote API.
2. A GUI tool.
3. Ad-hoc inference.

A hosted service is over-engineered for the task, since the data engineer has the sample file on the local machine usually to inspect it. Since this conversion has on interactive part, a GUI tool would add no real benefit and just raise complexity.

Ad-hoc inference (e.g., in a Virtual Schema) is convenient from a user's perspective, but since the source schema is fixed, does not make sense. Also, a fixed mapping does not add any noticeable runtime overhead. Unlike ad-hoc inference would with each request.

#### Keep the Tool as a Local CLI
`dsn~local-cli-processing~1`

The system processes the supplied file locally and emits the result to standard output.

Rationale:

Mappings are usually defined once by a data engineer. For that purpose the engineer uses a sample Parquet file and creates the first mapping before later maintaining it "by hand". The simplest way to do that is on the engineer's local machine.

Needs: impl
