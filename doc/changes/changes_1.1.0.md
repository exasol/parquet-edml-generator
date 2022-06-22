# Parquet EDML Generator 1.1.0, released 2022-06-22

Code name: Added native images

## Summary

This release adds a native-executable build. In addition we fixed the following vulnerabilities by updating dependencies: `CVE-2021-0341`, `CVE-2022-26612`, `CVE-2021-22569`.

## Features

* #3: Added native image build

## Dependency Updates

### Compile Dependency Updates

* Added `com.exasol:edml-java:1.0.0`
* Updated `com.exasol:error-reporting-java:0.4.0` to `0.4.1`
* Updated `com.exasol:maven-project-version-getter:0.1.0` to `1.1.0`
* Removed `com.exasol:virtual-schema-common-document-files:2.2.0`
* Updated `info.picocli:picocli:4.6.1` to `4.6.3`
* Added `org.apache.hadoop:hadoop-client:3.3.3`
* Updated `org.apache.parquet:parquet-hadoop:1.12.0` to `1.12.3`
* Added `org.slf4j:slf4j-jdk14:1.7.36`

### Test Dependency Updates

* Added `org.junit.jupiter:junit-jupiter-api:5.8.2`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.7.2` to `5.8.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.7.2` to `5.8.2`
* Updated `org.mockito:mockito-junit-jupiter:3.11.2` to `4.6.1`

### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.3.1` to `0.4.0`
* Updated `com.exasol:error-code-crawler-maven-plugin:0.1.1` to `1.1.1`
* Updated `com.exasol:project-keeper-maven-plugin:1.1.0` to `2.4.6`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.13` to `0.15`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.10.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M3` to `3.0.0-M5`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.0` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M3` to `3.0.0-M5`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.2.7`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.10.0`
* Added `org.graalvm.nativeimage:native-image-maven-plugin:21.2.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.8`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`
