# Developers Guide

## Building the native image

Current try to build a native image:

```shell
 ~/programme/graalvm-ce-java11-21.1.0/bin/native-image --static -cp target/parquet-edml-generator.jar com.exasol.edmlgenerator.parquet.Main --no-fallback
```

See https://picocli.info/#_how_do_i_create_a_native_image_for_my_application