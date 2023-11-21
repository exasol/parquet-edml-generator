# Developers Guide

By default, this project builds a native executable. For that you need to install the GraalVM including the native-image tool.

* Download and unzip [GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.3.3). Pick the latest version with same version as the `version` property of the `graalvm/setup-graalvm@v1` action in [`ci-build.yml`](../../.github/workflows/ci-build.yml).
* Install native image: run `./bin/gu install native-image` in the unzipped GraalVM dir.
* Set `$JAVA_HOME` to the graalvm dir.

## Building Without Native Image

Building the native image is quite resource expensive (~4GB RAM, 2 min CPU). If you just want to quickly run the build you can also skip it by adding `-p skipNativeImage` as parameter to `mvn`.
