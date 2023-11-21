# Parquet EDML Generator 1.1.7, released 2023-11-21

Code name: Fix vulnerabilities in dependencies

## Summary

This release fixes the following vulnerabilities the following compile time dependencies:

* `org.eclipse.jetty:jetty-http:jar:9.4.51.v20230217`
    * CVE-2023-40167, severity CWE-130: Improper Handling of Length Parameter Inconsistency  (5.3)
* `org.xerial.snappy:snappy-java:jar:1.1.10.1`
    * CVE-2023-43642, severity CWE-770: Allocation of Resources Without Limits or Throttling (7.5)
* `org.apache.avro:avro:jar:1.7.7`
    * CVE-2023-39410, severity CWE-502: Deserialization of Untrusted Data (7.5)

## Security

* #42: Fixed vulnerabilities CVE-2023-40167, CVE-2023-43642 and CVE-2023-39410 in dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:edml-java:1.2.0` to `1.2.1`
* Updated `info.picocli:picocli:4.7.4` to `4.7.5`
* Updated `org.xerial.snappy:snappy-java:1.1.10.1` to `1.1.10.5`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.9.3` to `5.10.1`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.3` to `5.10.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.3` to `5.10.1`
* Updated `org.mockito:mockito-junit-jupiter:5.4.0` to `5.7.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.3` to `1.3.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.7` to `2.9.16`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.5.0` to `3.6.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.3.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0` to `3.2.2`
* Updated `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1` to `2.0.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.4.1` to `1.5.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.15.0` to `2.16.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.9` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
