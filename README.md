# Parquet EDML Generator

[![Build Status](https://github.com/exasol/parquet-edml-generator/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/parquet-edml-generator/actions/workflows/ci-build.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-edml-generator&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-edml-generator)

This is a tool that generates EDML definitions for Parquet files.

## Installation

In order to use this tool you need Java >= 11 installed on your system.

Download the jar from the latest [Releases](https://github.com/exasol/parquet-edml-generator/releases).

## Usage

Run on your local PC:

```shell
java -jar parquet-edml-generator.jar <parquet file>
```

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)
