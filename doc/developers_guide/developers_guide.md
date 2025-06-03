# Developers Guide

## Initial Setup of GraalVM

By default, this project builds a native executable. For that you need to install the GraalVM including the native-image tool.

* Download and unzip [GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases/tag/jdk-17.0.9). Pick the latest version with same version as the `version` property of the `graalvm/setup-graalvm@v1` action in [`ci-build.yml`](../../.github/workflows/ci-build.yml).
* Install native image: run `./bin/gu install native-image` in the unzipped GraalVM dir.
* Add GraalVM to your `~/.m2/toolchains.xml`:
   ```xml
   <toolchain>
       <type>jdk</type>
       <provides>
           <version>17</version>
           <vendor>graalvm</vendor>
       </provides>
       <configuration>
           <jdkHome>/path/to/graalvm-ce-java17-22.3.1/</jdkHome>
       </configuration>
   </toolchain>
   ```

## Building Without Native Image

Building the native image is quite resource expensive (~4GB RAM, 2 min CPU). If you just want to quickly run the build you can also skip it by adding `--activate-profiles skipNativeImage` as parameter to `mvn`.
