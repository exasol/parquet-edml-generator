# Parquet EDML Generator 1.1.3, released 2022-10-26

Code name: Upgrade dependencies on top of 1.1.2

## Summary

This release upgrades dependencies compared to 1.1.2.

## Known Remaining Hadoop 3.3.4 Vulnerabilities and Sonatype Warnings

The following vulnerabilities are known in Hadoop 3.3.4, but no update is available at the time of this `parquet-edml-generator` update, so instead we evaluate the risks here.
* The command line tool `hdfs ec` has the known vulnerability [sonatype-2022-5732](https://ossindex.sonatype.org/vulnerability/sonatype-2022-5732), but the connector is not affected, since it does not use this tool. For more details see [HDFS-16766 on the Haddop issue tracker](https://issues.apache.org/jira/browse/HDFS-16766).
* Related to the vulnerability above the code creates `DocumentBuilderFactory` instances in various other locations, but the collection ticket [HADOOP-18469](https://issues.apache.org/jira/browse/HADOOP-18469) states that no additional issues are known as a result yet, also see [sonatype-2022-5820](https://ossindex.sonatype.org/vulnerability/sonatype-2022-5820).

## Features

* #31: Upgraded dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:maven-project-version-getter:1.1.1` to `1.2.0`

### Test Dependency Updates

* Updated `org.mockito:mockito-junit-jupiter:4.8.0` to `4.8.1`
