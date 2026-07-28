# Parquet EDML Generator 2.0.3, released 2026-07-28

Code name: Fixed vulnerabilities GHSA-R7WM-3CXJ-WFF9, GHSA-r7wm-3cxj-wff9

## Summary

This release fixes the following 2 vulnerabilities:

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
