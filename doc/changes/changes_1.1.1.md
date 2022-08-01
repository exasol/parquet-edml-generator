# Parquet EDML Generator 1.1.1, released 2022-??-??

Code name: Upgrade dependencies to fix vulnerabilities

## Summary

Upgrade dependencies to fix vulnerabilities in dependencies:
* org.eclipse.jetty:jetty-client:jar:9.4.43.v20210629 in compile
    * CVE-2022-2047, severity CWE-20: Improper Input Validation (2.7)

## Bugfixes

* #27: Fixed vulnerabilities reported by dependencies_check.yml

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:edml-java:1.0.0` to `1.1.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.8.2` to `5.9.0`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.8.2` to `5.9.0`
* Updated `org.junit.jupiter:junit-jupiter-params:5.8.2` to `5.9.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.4.6` to `2.5.0`
