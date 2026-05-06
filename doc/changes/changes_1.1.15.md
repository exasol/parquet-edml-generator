# Parquet EDML Generator 1.1.15, released 2026-05-07

Code name: Fixes for vulnerabilities CVE-2025-48924, CVE-2025-49128, CVE-2025-52999 and CVE-2025-53864

## Summary

This release fixes the following vulnerabilities:

### CVE-2025-53864 (CWE-121) in dependency `com.google.code.gson:gson:jar:2.9.0:compile`

Connect2id Nimbus JOSE + JWT before 10.0.2 allows a remote attacker to cause a denial of service via a deeply nested JSON object supplied in a JWT claim set, because of uncontrolled recursion. NOTE: this is independent of the Gson 2.11.0 issue because the Connect2id product could have checked the JSON object nesting depth, regardless of what limits (if any) were imposed by Gson.

CVE: CVE-2025-53864
CWE: CWE-121

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2025-53864?component-type=maven&component-name=com.google.code.gson%2Fgson&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-53864
- https://github.com/advisories/GHSA-2025-53864

### CVE-2025-53864 (CWE-674) in dependency `com.nimbusds:nimbus-jose-jwt:jar:9.37.2:compile`

Connect2id Nimbus JOSE + JWT before 10.0.2 allows a remote attacker to cause a denial of service via a deeply nested JSON object supplied in a JWT claim set, because of uncontrolled recursion. NOTE: this is independent of the Gson 2.11.0 issue because the Connect2id product could have checked the JSON object nesting depth, regardless of what limits (if any) were imposed by Gson.

CVE: CVE-2025-53864
CWE: CWE-674

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2025-53864?component-type=maven&component-name=com.nimbusds%2Fnimbus-jose-jwt&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-53864
- https://bitbucket.org/connect2id/nimbus-jose-jwt/issues/583/stackoverflowerror-due-to-deeply-nested

### CVE-2025-48924 (CWE-674) in dependency `org.apache.commons:commons-lang3:jar:3.12.0:compile`

Uncontrolled Recursion vulnerability in Apache Commons Lang.

This issue affects Apache Commons Lang: Starting with commons-lang:commons-lang 2.0 to 2.6, and, from org.apache.commons:commons-lang3 3.0 before 3.18.0.

The methods ClassUtils.getClass(...) can throw StackOverflowError on very long inputs. Because an Error is usually not handled by applications and libraries, a 
StackOverflowError could cause an application to stop.

Users are recommended to upgrade to version 3.18.0, which fixes the issue.

CVE: CVE-2025-48924
CWE: CWE-674

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2025-48924?component-type=maven&component-name=org.apache.commons%2Fcommons-lang3&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-48924
- https://github.com/advisories/GHSA-j288-q9x7-2f5v

### CVE-2025-52999 (CWE-121) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.12.7:compile`

jackson-core contains core low-level incremental ("streaming") parser and generator abstractions used by Jackson Data Processor. In versions prior to 2.15.0, if a user parses an input file and it has deeply nested data, Jackson could end up throwing a StackoverflowError if the depth is particularly large. jackson-core 2.15.0 contains a configurable limit for how deep Jackson will traverse in an input document, defaulting to an allowable depth of 1000. jackson-core will throw a StreamConstraintsException if the limit is reached. jackson-databind also benefits from this change because it uses jackson-core to parse JSON inputs. As a workaround, users should avoid parsing input files from untrusted sources.

CVE: CVE-2025-52999
CWE: CWE-121

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2025-52999?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-52999
- https://github.com/FasterXML/jackson-core/security/advisories/GHSA-h46c-h94j-95f3

### CVE-2025-49128 (CWE-209) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.12.7:compile`

Jackson-core contains core low-level incremental ("streaming") parser and generator abstractions used by Jackson Data Processor. Starting in version 2.0.0 and prior to version 2.13.0, a flaw in jackson-core's `JsonLocation._appendSourceDesc` method allows up to 500 bytes of unintended memory content to be included in exception messages. When parsing JSON from a byte array with an offset and length, the exception message incorrectly reads from the beginning of the array instead of the logical payload start. This results in possible information disclosure in systems using pooled or reused buffers, like Netty or Vert.x. This issue was silently fixed in jackson-core version 2.13.0, released on September 30, 2021, via PR #652. All users should upgrade to version 2.13.0 or later. If upgrading is not immediately possible, applications can mitigate the issue by disabling exception message exposure to clients to avoid returning parsing exception messages in HTTP responses and/or disabling source inclusion in exceptions to prevent Jackson from embedding any source content in exception messages, avoiding leakage.

CVE: CVE-2025-49128
CWE: CWE-209

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2025-49128?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-49128
- https://github.com/FasterXML/jackson-core/security/advisories/GHSA-wf8f-6423-gfxg

## Security

* #72: Fixed vulnerability CVE-2025-53864 in dependency `com.google.code.gson:gson:jar:2.9.0:compile`
* #70: Fixed vulnerability CVE-2025-53864 in dependency `com.nimbusds:nimbus-jose-jwt:jar:9.37.2:compile`
* #69: Fixed vulnerability CVE-2025-48924 in dependency `org.apache.commons:commons-lang3:jar:3.12.0:compile`
* #68: Fixed vulnerability CVE-2025-52999 in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.12.7:compile`
* #67: Fixed vulnerability CVE-2025-49128 in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.12.7:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:edml-java:2.1.0` to `2.1.1`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `org.apache.hadoop:hadoop-client:3.4.1` to `3.5.0`
* Updated `org.apache.parquet:parquet-hadoop:1.15.2` to `1.17.0`

### Runtime Dependency Updates

* Removed `commons-beanutils:commons-beanutils:1.11.0`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-api:5.13.0` to `6.0.3`
* Updated `org.junit.jupiter:junit-jupiter-params:5.13.0` to `6.0.3`
* Updated `org.mockito:mockito-junit-jupiter:5.18.0` to `5.23.0`

### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.4.3` to `0.4.4`
* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.3` to `2.0.7`
* Updated `com.exasol:project-keeper-maven-plugin:5.1.0` to `5.6.1`
* Updated `com.exasol:quality-summarizer-maven-plugin:0.2.0` to `0.2.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.1` to `10.0.0`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.0` to `3.6.1`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.7.1` to `3.8.0`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.0` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.5.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.3` to `3.5.5`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.4.2` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.3` to `3.5.5`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.0` to `1.7.3`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.18.0` to `2.21.0`
* Updated `org.graalvm.buildtools:native-maven-plugin:0.10.6` to `1.1.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.13` to `0.8.14`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.1.0.4751` to `5.5.0.6356`
