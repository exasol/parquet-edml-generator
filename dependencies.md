<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                         | License                                       |
| ---------------------------------- | --------------------------------------------- |
| [error-reporting-java][0]          | [MIT License][1]                              |
| [Apache Parquet Hadoop][2]         | [The Apache Software License, Version 2.0][3] |
| [snappy-java][4]                   | [Apache-2.0][5]                               |
| [EDML Java][6]                     | [MIT License][7]                              |
| [picocli][8]                       | [The Apache Software License, version 2.0][9] |
| [Maven Project Version Getter][10] | [MIT License][11]                             |
| Apache Hadoop Client Aggregator    | [Apache-2.0][3]                               |
| [Apache Commons Configuration][12] | [Apache-2.0][3]                               |

## Test Dependencies

| Dependency                       | License                           |
| -------------------------------- | --------------------------------- |
| [Hamcrest][13]                   | [BSD-3-Clause][14]                |
| [JUnit Jupiter (Aggregator)][15] | [Eclipse Public License v2.0][16] |
| [mockito-junit-jupiter][17]      | [MIT][18]                         |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][19]                       | [GNU LGPL 3][20]                               |
| [Apache Maven Toolchains Plugin][21]                    | [Apache-2.0][3]                                |
| [Apache Maven Compiler Plugin][22]                      | [Apache-2.0][3]                                |
| [Apache Maven Enforcer Plugin][23]                      | [Apache-2.0][3]                                |
| [Maven Flatten Plugin][24]                              | [Apache Software License][3]                   |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][25] | [ASL2][9]                                      |
| [Maven Surefire Plugin][26]                             | [Apache-2.0][3]                                |
| [Versions Maven Plugin][27]                             | [Apache License, Version 2.0][3]               |
| [duplicate-finder-maven-plugin Maven Mojo][28]          | [Apache License 2.0][29]                       |
| [Apache Maven Artifact Plugin][30]                      | [Apache-2.0][3]                                |
| [Project Keeper Maven plugin][31]                       | [The MIT License][32]                          |
| [Apache Maven Assembly Plugin][33]                      | [Apache-2.0][3]                                |
| [Apache Maven JAR Plugin][34]                           | [Apache-2.0][3]                                |
| [Artifact reference checker and unifier][35]            | [MIT License][36]                              |
| [spdx-maven-plugin Maven Plugin][37]                    | [The Apache Software License, Version 2.0][9]  |
| [GraalVM Native Maven Plugin][38]                       | [Universal Permissive License Version 1.0][39] |
| [Maven Failsafe Plugin][40]                             | [Apache-2.0][3]                                |
| [JaCoCo :: Maven Plugin][41]                            | [EPL-2.0][42]                                  |
| [error-code-crawler-maven-plugin][43]                   | [MIT License][44]                              |
| [Git Commit Id Maven Plugin][45]                        | [GNU Lesser General Public License 3.0][46]    |
| [Apache Maven Clean Plugin][47]                         | [Apache-2.0][3]                                |
| [Apache Maven Resources Plugin][48]                     | [Apache-2.0][3]                                |
| [Apache Maven Install Plugin][49]                       | [Apache-2.0][3]                                |
| [Apache Maven Site Plugin][50]                          | [Apache-2.0][3]                                |

[0]: https://github.com/exasol/error-reporting-java/
[1]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[2]: https://parquet.apache.org
[3]: https://www.apache.org/licenses/LICENSE-2.0.txt
[4]: https://github.com/xerial/snappy-java
[5]: https://www.apache.org/licenses/LICENSE-2.0.html
[6]: https://github.com/exasol/edml-java/
[7]: https://github.com/exasol/edml-java/blob/main/LICENSE
[8]: https://picocli.info
[9]: http://www.apache.org/licenses/LICENSE-2.0.txt
[10]: https://github.com/exasol/maven-project-version-getter/
[11]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[12]: https://commons.apache.org/proper/commons-configuration/
[13]: http://hamcrest.org/JavaHamcrest/
[14]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[15]: https://junit.org/
[16]: https://www.eclipse.org/legal/epl-v20.html
[17]: https://github.com/mockito/mockito
[18]: https://opensource.org/licenses/MIT
[19]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[20]: http://www.gnu.org/licenses/lgpl.txt
[21]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[22]: https://maven.apache.org/plugins/maven-compiler-plugin/
[23]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[24]: https://www.mojohaus.org/flatten-maven-plugin/
[25]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[26]: https://maven.apache.org/surefire/maven-surefire-plugin/
[27]: https://www.mojohaus.org/versions/versions-maven-plugin/
[28]: https://basepom.github.io/duplicate-finder-maven-plugin
[29]: http://www.apache.org/licenses/LICENSE-2.0.html
[30]: https://maven.apache.org/plugins/maven-artifact-plugin/
[31]: https://github.com/exasol/project-keeper/
[32]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[33]: https://maven.apache.org/plugins/maven-assembly-plugin/
[34]: https://maven.apache.org/plugins/maven-jar-plugin/
[35]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[36]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[37]: https://github.com/spdx/spdx-maven-plugin
[38]: https://github.com/graalvm/native-build-tools
[39]: http://oss.oracle.com/licenses/upl
[40]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[41]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[42]: https://www.eclipse.org/legal/epl-2.0/
[43]: https://github.com/exasol/error-code-crawler-maven-plugin/
[44]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[45]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[46]: http://www.gnu.org/licenses/lgpl-3.0.txt
[47]: https://maven.apache.org/plugins/maven-clean-plugin/
[48]: https://maven.apache.org/plugins/maven-resources-plugin/
[49]: https://maven.apache.org/plugins/maven-install-plugin/
[50]: https://maven.apache.org/plugins/maven-site-plugin/
