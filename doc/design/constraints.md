# Architecture Constraints

This chapter records constraints stated in project documentation or build configuration. Build choices are distinguished from product support claims.

## Technical Constraints

### Java Runtime for JAR Use
`constr~java-runtime-for-jar~1`

The JAR distribution runs with Java 21 or later.

Needs: dsn

### Native Binary Availability
`constr~native-binary-availability~1`

The project supplies native executables for Windows and Linux.

Needs: dsn

### Self-Contained Native Executables
`constr~self-contained-native-executables~1`

The Linux and Windows native executables include the runtime required to run PEG.

Needs: dsn

### Local File Conversion Only
`constr~local-file-conversion-only~1`

The PEG converts local files.

Needs: dsn

## Organizational Constraints

### Manual Release Process
`constr~manual-release-process~1`

The releases use a manual release-droid process.

Needs: dsn
