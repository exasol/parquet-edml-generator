# Parquet EDML Generator 1.0.4, released 2021-11-26

Code name: Fixed transitive CVE-2021-31684

## Summary

In this release we updated dependencies and by that fixed the transitive CVE-2021-31684.

## Bug Fixes:

* #15: Fixed transitive CVE-2021-31684

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.0` to `0.4.1`
* Updated `com.exasol:maven-project-version-getter:0.1.0` to `1.0.0`
* Updated `com.exasol:virtual-schema-common-document-files:2.2.0` to `3.0.0`
* Updated `info.picocli:picocli:4.6.1` to `4.6.2`
* Updated `org.apache.parquet:parquet-hadoop:1.12.0` to `1.12.2`

### Test Dependency Updates

* Added `org.junit.jupiter:junit-jupiter-api:5.8.1`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.7.2` to `5.8.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.7.2` to `5.8.1`
* Updated `org.mockito:mockito-junit-jupiter:3.11.2` to `4.1.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.1.1` to `0.6.0`
* Updated `com.exasol:project-keeper-maven-plugin:1.1.0` to `1.3.2`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M3` to `3.0.0-M5`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M3` to `3.0.0-M5`
