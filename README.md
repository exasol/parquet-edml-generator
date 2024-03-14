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

Download the binary for your operating system from the latest [release](https://github.com/exasol/parquet-edml-generator/releases).

## Usage

### Windows

Run on your local PC:

```shell
parquet-edml-generator.exe <parquet file>
```

### Linux

Run on your local PC:

```shell
./parquet-edml-generator-linux-binary <parquet file>
```

### macOS

Please note that we currently don't provide native binaries for macOS, because execution fails on macOS 13 Ventura. Support for native binaries on macOS will be added in issue [#36](https://github.com/exasol/parquet-edml-generator/issues/36).

### Other

On other operating systems you can use this tool using Java. For that you need Java >= 17 installed.

```shell
java -jar parquet-edml-generator.jar <parquet file>
```

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)
