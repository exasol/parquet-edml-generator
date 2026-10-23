# Quality Requirements

User-facing acceptance scenarios are defined in [User and System Requirements](../requirements/requirements.md).

## Requirement Quality

The draft uses the hierarchy `feat` → `req` → `scn` → `dsn` → implementation and test evidence.

## Code Quality

The Maven compiler enables lint warnings and treats warnings as errors. CI runs Maven verification and builds native images on Windows and Linux.

## Test Quality

JUnit tests cover type converters, naming conversion, local input stream operations, and generated EDML. An integration test runs both the JAR and native executable and parses their output.

## Dependency Policy

The project manages dependencies through Maven and includes explicit dependency overrides and exclusions with comments identifying security fixes. CI includes an OSS Index audit.

## Static Analysis and Security Gates

CI runs Sonar analysis when its token is available, OSS Index auditing, and Zizmor GitHub Actions linting. Maven project-keeper verification is part of the project build.

## Testability and Coverage

Unit and integration tests exist. No minimum coverage threshold was established from inspected project documentation or build configuration.

## Open Issues

* The quality-gate policy and required coverage threshold are not described in the developers guide.
