# Parquet EDML Generator 2.0.3, released 2026-09-16

Code name: Fixed vulnerabilities GHSA-R7WM-3CXJ-WFF9, GHSA-r7wm-3cxj-wff9, CVE-2026-8763, CVE-2026-59651, CVE-2026-59650, CVE-2026-59638, CVE-2026-58063, CVE-2026-58062, CVE-2026-58060, CVE-2026-58059, CVE-2026-14682, CVE-2026-13586, CVE-2026-13506, CVE-2026-12860, CVE-2026-12816, CVE-2026-12803

## Summary

This release fixes the following 16 vulnerabilities:

### GHSA-R7WM-3CXJ-WFF9 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-core:jar:unknown:compile`
jackson-core: Async parser maxNumberLength bypass via chunked digit accumulation (incomplete fix for GHSA-72hv-8253-57qq)
#### References
* https://github.com/FasterXML/jackson-core/security/advisories/GHSA-r7wm-3cxj-wff9
* https://github.com/FasterXML/jackson-core/pull/1611
* https://github.com/FasterXML/jackson-core/commit/050b429804dce2a7e08f0be1b0b4c3d040fdb9cd
* https://github.com/FasterXML/jackson-core/commit/4cdd529749da396cc7edf6d4a2aad41d47902641
* https://github.com/FasterXML/jackson-core/commit/c5941e5aae7fd5aeac55d66933cfb82b9aabeef8
* https://github.com/advisories/GHSA-r7wm-3cxj-wff9

### GHSA-R7WM-3CXJ-WFF9 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.0:compile`
jackson-core: Async parser maxNumberLength bypass via chunked digit accumulation (incomplete fix for GHSA-72hv-8253-57qq)
#### References
* https://github.com/FasterXML/jackson-core/security/advisories/GHSA-r7wm-3cxj-wff9
* https://github.com/FasterXML/jackson-core/pull/1611
* https://github.com/FasterXML/jackson-core/commit/050b429804dce2a7e08f0be1b0b4c3d040fdb9cd
* https://github.com/FasterXML/jackson-core/commit/4cdd529749da396cc7edf6d4a2aad41d47902641
* https://github.com/FasterXML/jackson-core/commit/c5941e5aae7fd5aeac55d66933cfb82b9aabeef8
* https://github.com/FasterXML/jackson-core

## Security

* #98: Fixed vulnerability GHSA-R7WM-3CXJ-WFF9 in dependency `com.fasterxml.jackson.core:jackson-core:jar:unknown:compile`
* #98: Fixed vulnerability GHSA-R7WM-3CXJ-WFF9 in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.0:compile`
* #114: Fixed vulnerability CVE-2026-8763 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #113: Fixed vulnerability CVE-2026-59651 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #112: Fixed vulnerability CVE-2026-59650 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #111: Fixed vulnerability CVE-2026-59638 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #110: Fixed vulnerability CVE-2026-58063 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #109: Fixed vulnerability CVE-2026-58062 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #108: Fixed vulnerability CVE-2026-58060 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #107: Fixed vulnerability CVE-2026-58059 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #106: Fixed vulnerability CVE-2026-14682 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #105: Fixed vulnerability CVE-2026-13586 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #104: Fixed vulnerability CVE-2026-13506 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #103: Fixed vulnerability CVE-2026-12860 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #102: Fixed vulnerability CVE-2026-12816 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`
* #101: Fixed vulnerability CVE-2026-12803 in dependency `org.bouncycastle:bcprov-jdk18on:jar:1.84:runtime`

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.parquet:parquet-hadoop:1.17.1` to `1.18.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:6.1.0` to `6.1.3`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.1.0` to `2.1.1`
* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.5`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:10.0.0` to `10.0.1`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.5.0` to `3.5.1`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.2.0` to `3.3.0`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.3` to `1.8.0`
* Updated `org.graalvm.buildtools:native-maven-plugin:1.1.2` to `1.1.13`
