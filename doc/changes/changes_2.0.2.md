# Parquet EDML Generator 2.0.2, released 2026-07-28

Code name: Fixed vulnerabilities CVE-2026-54512, CVE-2026-54513, CVE-2026-54514, CVE-2026-54515, CVE-2026-54518, CVE-2026-59888, CVE-2026-59889, CVE-2026-57914, CVE-2026-10050

## Summary

This release fixes the following 9 vulnerabilities:

### CVE-2026-54512 (CWE-184) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.10.0 until 2.18.8, 2.21.4, and 3.1.4, jackson-databind's PolymorphicTypeValidator (PTV) is the primary safety mechanism guarding polymorphic deserialization. When polymorphic typing is enabled and a type identifier contains generic parameters (i.e. the type ID string contains <), DatabindContext._resolveAndValidateGeneric() validates only the raw container class name (the substring before <) against the configured PTV. If the container type is approved, the method parses the full canonical type string via TypeFactory.constructFromCanonical() and returns the fully parameterized type without ever validating the nested type arguments against the PTV. The nested type arguments are then resolved, instantiated, and populated as beans during deserialization. An attacker who controls the type ID can therefore place a denied class as a generic type parameter of an allowed container â for example java.util.ArrayList<com.evil.Gadget> when only java.util.ArrayList is allow-listed. The container passes the PTV check; com.evil.Gadget is loaded via Class.forName(name, true, loader), instantiated, and its properties are set from attacker-controlled JSON. This completely bypasses an explicitly configured PTV allow-list. This vulnerability is fixed in 2.18.8, 2.21.4, and 3.1.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54512?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54512
* https://bodhi.fedoraproject.org/updates/FEDORA-2026-ddde3cf003
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-j3rv-43j4-c7qm

### CVE-2026-54513 (CWE-184) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.10.0 until 2.18.8, 2.21.4, and 3.1.4, BasicPolymorphicTypeValidator.Builder.allowIfSubTypeIsArray() allowlists any array type based only on clazz.isArray(), without validating the array's component (element) type against the configured allowlist. A PTV built with allowIfSubTypeIsArray() plus an explicit concrete-type allowlist therefore still permits EvilType[] even though EvilType is not allowlisted. When Jackson deserializes the elements and no per-element type IDs are present, it instantiates the component type directly with no further PTV check, bypassing the allowlist. This vulnerability is fixed in 2.18.8, 2.21.4, and 3.1.4.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54513 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54513?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54513
* https://bodhi.fedoraproject.org/updates/FEDORA-2026-ddde3cf003
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-rmj7-2vxq-3g9f

### CVE-2026-54514 (CWE-918) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.0.0 until 2.18.8, 2.21.4, and 3.1.4, JDKFromStringDeserializer constructed InetSocketAddress with new InetSocketAddress(host, port), which performs eager DNS name resolution for hostname inputs at deserialization time. An application that binds untrusted JSON into a type containing an InetSocketAddress field issues an attacker-chosen DNS query during readValue, before any application-level validation or connect logic. The fix uses InetSocketAddress.createUnresolved(host, port), deferring DNS to an explicit connect. This vulnerability is fixed in 2.18.8, 2.21.4, and 3.1.4.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54514 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54514?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54514
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-hgj6-7826-r7m5

### CVE-2026-54515 (CWE-915) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.8.0 until 2.18.9, 2.21.5, and 3.1.4, in BeanDeserializerBase.createContextual(), per-property @JsonIgnoreProperties exclusions are applied by _handleByNameInclusion(), producing a contextual deserializer whose BeanPropertyMap has the ignored properties removed. The subsequent per-property case-insensitivity block (triggered by @JsonFormat(ACCEPT_CASE_INSENSITIVE_PROPERTIES)) rebuilds from this._beanProperties (the original, unfiltered map) instead of contextual._beanProperties, then overwrites the filtered map â restoring every property _handleByNameInclusion had just removed. The ignored property becomes writable again. This vulnerability is fixed in 2.18.9, 2.21.5, and 3.1.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54515?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54515
* https://bodhi.fedoraproject.org/updates/FEDORA-2026-ddde3cf003
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-5jmj-h7xm-6q6v

### CVE-2026-54518 (CWE-863) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.21.0 until 2.21.4 and 3.1.4, UnwrappedPropertyHandler.processUnwrappedCreatorProperties() replays buffered JSON into creator parameters but never consults prop.visibleInView(activeView). The normal property-based creator path gates creator properties on the active view, but this unwrapped-creator replay path bypasses that check, so a constructor parameter annotated with both @JsonView(AdminView.class) and @JsonUnwrapped is populated from attacker JSON even when a more restrictive view is active. This vulnerability is fixed in 2.21.4 and 3.1.4.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54518 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54518?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54518
* https://bodhi.fedoraproject.org/updates/FEDORA-2026-ddde3cf003
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-rcqc-6cw3-h962

### CVE-2026-59888 (CWE-915) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.15.0 until 2.18.8, 2.21.4, and 3.1.4, Java Records using a PropertyNamingStrategy can bypass @JsonIgnore because POJOPropertiesCollector._removeUnwantedIgnorals() records an ignored component under its original implicit name before _renameUsing() applies the naming strategy, allowing the renamed JSON key to be assigned to the Record constructor parameter. This issue is fixed in versions 2.18.8, 2.21.4, and 3.1.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59888?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59888
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-3pjw-73gf-8qr5

### CVE-2026-59889 (CWE-863) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.18.0 until 2.18.9, 2.21.5, 2.22.1, 3.1.5, and 3.2.1, UnwrappedPropertyHandler.processUnwrapped() replays buffered JSON for a @JsonUnwrapped property and calls prop.deserializeAndSet() without a prop.visibleInView(ctxt.getActiveView()) guard, allowing a property annotated with both @JsonView and @JsonUnwrapped to be written from attacker JSON under a less-privileged active view. This issue is fixed in versions 2.18.9, 2.21.5, 2.22.1, 3.1.5, and 3.2.1.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59889?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59889
* https://github.com/FasterXML/jackson-databind/issues/6060
* https://github.com/FasterXML/jackson-databind/pull/6056
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-5gvw-p9qm-jgwh

### CVE-2026-57914 (CWE-400) in dependency `org.apache.kerby:kerby-asn1:jar:2.0.3:compile`
By sending a deeply nested ASN1 structure to a Apache Kerby client or service, it's possible to trigger a StackOverFlow Exception which can lead to denial of service issues. Users are recommended to upgrade to version 2.1.2, which fixes this issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-57914?component-type=maven&component-name=org.apache.kerby%2Fkerby-asn1&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-57914
* http://www.openwall.com/lists/oss-security/2026/06/26/7
* https://lists.apache.org/thread/w98h2q8wz0bq97vhz4vf55hqomcb2j1m

### CVE-2026-10050 (CWE-173) in dependency `org.eclipse.jetty:jetty-security:jar:9.4.58.v20250814:compile`
org.eclipse.jetty:jetty-security - Improper Handling of Alternate Encoding
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-10050?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-security&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-10050
* https://github.com/advisories/GHSA-2fvj-hgj9-j2gr

## Security

* #81: Fixed vulnerability CVE-2026-54512 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #82: Fixed vulnerability CVE-2026-54513 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #83: Fixed vulnerability CVE-2026-54514 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #84: Fixed vulnerability CVE-2026-54515 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #85: Fixed vulnerability CVE-2026-54518 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #86: Fixed vulnerability CVE-2026-59888 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #87: Fixed vulnerability CVE-2026-59889 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.6:compile`
* #88: Fixed vulnerability CVE-2026-57914 in dependency `org.apache.kerby:kerby-asn1:jar:2.0.3:compile`
* #91: Fixed vulnerability CVE-2026-10050 in dependency `org.eclipse.jetty:jetty-security:jar:9.4.58.v20250814:compile`

## Dependency Updates

### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.4.4` to `1.0.1`
* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.7` to `2.1.0`
* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.4`
* Removed `com.exasol:quality-summarizer-maven-plugin:0.2.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.5` to `3.5.6`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.21.0` to `3.22.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.5` to `3.5.6`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.14` to `0.8.15`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356` to `5.7.0.6970`
* Added `org.spdx:spdx-maven-plugin:1.0.4`
