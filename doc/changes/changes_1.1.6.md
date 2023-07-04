# Parquet EDML Generator 1.1.6, released 2023-??-??

Code name: Dependency Updgrade on top of 1.1.5

## Summary

This release fixes the following vulnerabilities in transitive compile-time-dependency `org.xerial.snappy:snappy-java` via `org.apache.parquet:parquet-hadoop` by overriding the transitive dependency:
* CVE-2023-34453, severity CWE-190: Integer Overflow or Wraparound (7.5)
* CVE-2023-34454, severity CWE-190: Integer Overflow or Wraparound (7.5)
* CVE-2023-34455, severity CWE-770: Allocation of Resources Without Limits or Throttling (7.5)

## Features

* #39: Updated dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `info.picocli:picocli:4.7.3` to `4.7.4`
* Updated `org.apache.hadoop:hadoop-client:3.3.5` to `3.3.6`
* Updated `org.apache.parquet:parquet-hadoop:1.13.0` to `1.13.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.9.2` to `5.9.3`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.2` to `5.9.3`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.2` to `5.9.3`
* Updated `org.mockito:mockito-junit-jupiter:5.3.1` to `5.4.0`
