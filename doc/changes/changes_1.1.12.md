# Parquet EDML Generator 1.1.12, released 2024-11-19

Code name: Fixed vulnerability CVE-2024-47535 in transitive dependency `io.netty:netty-common:jar:4.1.104.Final` via `org.apache.hadoop:hadoop-client`.

## Summary

This release fixes the following vulnerability:

### CVE-2024-47535 (CWE-400) in dependency `io.netty:netty-common:jar:4.1.104.Final:test`

Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients. An unsafe reading of environment file could potentially cause a denial of service in Netty. When loaded on an Windows application, Netty attempts to load a file that does not exist. If an attacker creates such a large file, the Netty application crashes. This vulnerability is fixed in 4.1.115.

#### References

* https://ossindex.sonatype.org/vulnerability/CVE-2024-47535?component-type=maven&component-name=io.netty%2Fnetty-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-47535
* https://github.com/advisories/GHSA-xq3w-v528-46rv

## Security

* #57: Fixed vulnerability CVE-2024-47535 in dependency `io.netty:netty-common:jar:4.1.104.Final:test`
## Dependency Updates

### Compile Dependency Updates

* Removed `dnsjava:dnsjava:3.6.1`
* Removed `io.airlift:aircompressor:0.27`
* Added `io.netty:netty-common:4.1.115.Final`
* Removed `org.apache.commons:commons-compress:1.26.2`
* Updated `org.apache.hadoop:hadoop-client:3.4.0` to `3.4.1`
* Updated `org.apache.parquet:parquet-hadoop:1.14.1` to `1.14.4`
* Updated `org.itsallcode:junit5-system-extensions:1.2.0` to `1.2.2`
* Removed `org.xerial.snappy:snappy-java:1.1.10.5`

### Runtime Dependency Updates

* Removed `org.apache.commons:commons-configuration2:2.11.0`

### Test Dependency Updates

* Updated `org.hamcrest:hamcrest:2.2` to `3.0`
* Updated `org.junit.jupiter:junit-jupiter-api:5.10.3` to `5.11.3`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.10.3` to `5.11.3`
* Updated `org.junit.jupiter:junit-jupiter-params:5.10.3` to `5.11.3`
* Updated `org.mockito:mockito-junit-jupiter:5.12.0` to `5.14.2`
