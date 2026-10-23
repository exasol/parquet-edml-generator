# Design

This document describes the architecture of Parquet EDML Generator.

User perspective, audience, features, user requirements, and acceptance scenarios are defined in [User and System Requirements](../requirements/requirements.md). This design document focuses on architecture, technical decisions, runtime behavior, and implementation structure.

The structure follows arc42 with the user-perspective parts reduced to short references instead of duplicated content.

## Structure

### Introduction and Goals

The system is a local command-line application that reads a Parquet schema and serializes a corresponding EDML mapping definition. Its primary architecture goals to explain the architectural design of the converter and ensure that it is easily testable.

### Architecture Constraints

See [Architecture Constraints](constraints.md).

### Context and Scope

See [Context and Scope](context_and_scope.md).

### Solution Strategy

See [Solution Strategy](solution_strategy.md).

### Building Block View

See [Building Block View](building_block_view.md).

### Runtime View

See [Runtime View](runtime_view.md).

### Deployment View

See [Deployment View](deployment_view.md).

### Crosscutting Concepts

See [Crosscutting Concepts](crosscutting_concepts.md).

### Architecture Decisions

See [Architecture Decisions](architecture_decisions.md).

### Quality Requirements

See [Quality Requirements](quality_requirements.md).

### Risks and Technical Debt

See [Risks and Technical Debt](risks_and_technical_debt.md).

### Glossary

See [Glossary](glossary.md).

### Open Issues

See [Open Issues](open_issues.md).
