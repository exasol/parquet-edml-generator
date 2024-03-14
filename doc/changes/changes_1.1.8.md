# Parquet EDML Generator 1.1.8, released 2024-03-14

Code name: Fixed CVE-2024-26308, CVE-2024-25710 and CVE-2023-52428 in compile dependencies

## Summary

This release fixes vulnerabilities in the following compile dependencies:
* `org.apache.commons:commons-compress:jar:1.21:compile`:
  * CVE-2024-26308
  * CVE-2024-25710
* `com.nimbusds:nimbus-jose-jwt:jar:9.8.1:compile`:
  * CVE-2023-52428

## Security

* #46: Fixed CVE-2024-26308 in `org.apache.commons:commons-compress:jar:1.21:compile`
* #45: Fixed CVE-2024-25710 in `org.apache.commons:commons-compress:jar:1.21:compile`
* #44: Fixed CVE-2023-52428 in `com.nimbusds:nimbus-jose-jwt:jar:9.8.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:edml-java:1.2.1` to `2.0.0`
* Added `org.apache.commons:commons-compress:1.26.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.10.1` to `5.10.2`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.10.1` to `5.10.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.10.1` to `5.10.2`
* Updated `org.mockito:mockito-junit-jupiter:5.7.0` to `5.11.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.16` to `2.9.17`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.1` to `2.16.2`
