# Parquet EDML Generator 2.0.1, released 2026-06-11

Code name: Fixed vulnerability CVE-2026-45205 in org.apache.commons:commons-configuration2:jar:2.10.1:compile

## Summary

This release fixes the following vulnerability:

### CVE-2026-45205 (CWE-674) in dependency `org.apache.commons:commons-configuration2:jar:2.10.1:compile`

Uncontrolled Recursion vulnerability in Apache Commons.

When processing an untrusted configuration file, Commons Configuration will throw a StackOverflowError for YAML input with cycles.
This issue affects Apache Commons: from 2.2 before 2.15.0.

Users are recommended to upgrade to version 2.15.0, which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-45205?component-type=maven&component-name=org.apache.commons%2Fcommons-configuration2&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-45205
* https://github.com/advisories/GHSA-337m-mw94-2v6g
* https://lists.apache.org/thread/q3q3j10ohcqhs6o0rg1v7kz6kk27vtkk

## Security

* #79: Fixed vulnerability CVE-2026-45205 in dependency `org.apache.commons:commons-configuration2:jar:2.10.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Added `org.apache.commons:commons-configuration2:2.15.1`
* Updated `org.apache.parquet:parquet-hadoop:1.17.0` to `1.17.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:6.0.3` to `6.1.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.1` to `5.6.2`
* Updated `org.graalvm.buildtools:native-maven-plugin:1.1.0` to `1.1.2`
