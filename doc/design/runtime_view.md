# Runtime View

This chapter describes relevant runtime interactions for the main use cases. Terms use the definitions from [User and System Requirements](../requirements/requirements.md).

## Generate a Definition From a File

### Read, Convert, and Serialize a Parquet File
`dsn~generate-definition-runtime~1`

**Given** a readable Parquet file with supported schema types
**When** the user invokes the CLI with its path
**Then** Picocli dispatches to `Main`, the generator reads the Parquet schema, converters create an EDML definition, and the serializer writes it to standard output with a successful exit status.

Covers:
- `scn~generate-definition-from-file~1`
- `constr~local-file-conversion-only~1`

Needs: impl, itest

## Convert Schema Fields

### Convert Primitive Schema Fields
`dsn~convert-primitive-schema-fields~1`

**Given** a schema containing supported primitive fields
**When** the generator converts the schema
**Then** primitive types use the corresponding primitive EDML mapping.

Covers:
- `scn~convert-primitive-schema-fields~1`

Needs: impl, utest

### Convert Logical Schema Fields
`dsn~convert-logical-schema-fields~1`

**Given** a schema containing supported logical fields
**When** the generator converts the schema
**Then** logical annotations use the corresponding logical EDML mapping.

Covers:
- `scn~convert-logical-schema-fields~1`

Needs: impl, utest

### Preserve Nested Schema Fields
`dsn~preserve-nested-schema-fields~1`

**Given** a schema containing supported nested fields
**When** the generator converts the schema
**Then** group fields are traversed recursively and represented as nested EDML fields.

Covers:
- `scn~preserve-nested-schema-fields~1`

Needs: impl, utest

### Normalize Destination Field Names
`dsn~normalize-destination-field-names~1`

**Given** a schema field with a non-upper-snake-case name
**When** the generator creates its EDML mapping
**Then** the destination field name is converted to upper snake case.

Covers:
- `scn~normalize-destination-field-names~1`

Needs: impl, utest

## Reject Unsupported Types

### Report an Unsupported Schema Type
`dsn~reject-type-runtime~1`

**Given** a schema node with an unsupported type
**When** the converter handles that node
**Then** it raises a structured unsupported-type exception that identifies the type and column.

Covers:
- `scn~reject-unsupported-schema-type~1`

Needs: impl, utest

## Deployment Constraints

### Documented Runtime and Distribution Modes
`dsn~documented-runtime-distributions~1`

PEG runs from a Java JAR with Java 21 or later, or from self-contained native executables for Linux and Windows.

Covers:
- `scn~run-from-documented-distribution~1`
- `constr~java-runtime-for-jar~1`
- `constr~native-binary-availability~1`
- `constr~self-contained-native-executables~1`

Needs: bld

### Manual Release Packaging
`dsn~manual-release-packaging~1`

The release process packages and publishes artifacts through the documented manual release-droid workflow.

Covers:
- `constr~manual-release-process~1`

Needs: dman
