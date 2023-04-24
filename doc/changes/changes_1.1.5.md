# Parquet EDML Generator 1.1.5, released 2023-04-24

Code name: Fix CVE-2023-26048

## Summary

This release fixes vulnerability CVE-2023-26048 (Uncontrolled Resource Consumption) in transitive dependency `org.eclipse.jetty:jetty-util:jar:9.4.48.v20220622` by excluding it as it is not used.

## Security

* #37: Fixed CVE-2023-26048

## Dependency Updates

### Compile Dependency Updates

* Removed `com.fasterxml.woodstox:woodstox-core:6.5.0`
* Updated `info.picocli:picocli:4.7.1` to `4.7.3`
* Updated `org.apache.hadoop:hadoop-client:3.3.4` to `3.3.5`
* Updated `org.apache.parquet:parquet-hadoop:1.12.3` to `1.13.0`
* Removed `org.slf4j:slf4j-jdk14:1.7.36`

### Test Dependency Updates

* Updated `org.mockito:mockito-junit-jupiter:5.2.0` to `5.3.1`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.2` to `1.2.3`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.4` to `2.9.7`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.2.1` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M8` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8` to `3.0.0`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.4.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.14.2` to `2.15.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.9`
