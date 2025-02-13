# Parquet EDML Generator 1.1.13, released 2025-02.13

Code name: Fixed CVE-2025-24970 and CVE-2025-25193

## Summary

This update fixes CVE-2025-24970 (crash on special network package) by updating `netty` to the latest version.
It also fixes CVE-2025-25193 (an issue under Windows). Note that this issue does not affect the virtual schema since it is not used under Windows anyway.

Also, please note that the vulnerable package was in the test code only, so a production update is not strictly necessary.

## Features

* #60: CVE-2025-24970
* #61: CVE-2025-25193

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:maven-project-version-getter:1.2.0` to `1.2.1`
* Updated `io.netty:netty-common:4.1.115.Final` to `4.1.118.Final`
* Updated `org.apache.parquet:parquet-hadoop:1.14.4` to `1.15.0`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.11.3` to `5.11.4`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.11.3` to `5.11.4`
* Updated `org.junit.jupiter:junit-jupiter-params:5.11.3` to `5.11.4`
* Updated `org.mockito:mockito-junit-jupiter:5.14.2` to `5.15.2`
