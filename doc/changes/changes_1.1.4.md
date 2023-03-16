# Parquet EDML Generator 1.1.4, released 2023-03-??

Code name: Upgrade dependencies

## Summary

This release fixes vulnerabilities in dependencies:

* `com.fasterxml.woodstox:woodstox-core:jar:5.3.0`: CVE-2022-40152
* `org.json:json:jar:20220320:compile`: CVE-2022-45688
* `com.fasterxml.jackson.core:jackson-core:jar:2.12.7:compile` [sonatype-2022-6438](https://ossindex.sonatype.org/vulnerability/sonatype-2022-6438)
* `commons-net:commons-net:jar:3.6:compile`: CVE-2021-37533

## Security

* #33: Upgrade dependencies to fix vulnerabilities:

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:edml-java:1.1.1` to `1.2.0`
* Updated `com.exasol:error-reporting-java:1.0.0` to `1.0.1`
* Added `com.fasterxml.woodstox:woodstox-core:6.5.0`
* Updated `info.picocli:picocli:4.6.3` to `4.7.1`
* Removed `org.apache.commons:commons-compress:1.21`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.9.1` to `5.9.2`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.1` to `5.9.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.1` to `5.9.2`
* Updated `org.mockito:mockito-junit-jupiter:4.8.1` to `5.2.0`

### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.4.0` to `0.4.2`
* Updated `com.exasol:error-code-crawler-maven-plugin:1.1.2` to `1.2.2`
* Updated `com.exasol:project-keeper-maven-plugin:2.8.0` to `2.9.4`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.15` to `0.16`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.3.0` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.1.0` to `3.2.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M5` to `3.0.0-M8`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.2` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M5` to `3.0.0-M8`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.2.7` to `1.3.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.10.0` to `2.14.2`
