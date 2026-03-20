# ether-http-problem

Problem Details utilities for Ether HTTP.

## Scope

- RFC 9457 style problem document model
- Problem exception for application flows
- JSON rendering/parsing helpers
- Bridge to `ether-http-core` error mapping

## Current integration

- `ProblemException` can be thrown from application handlers.
- `ether-http-jetty12` now renders transport-generated errors as Problem Details too.
- The payload includes normalized metadata such as `error`, optional `code`, `path` and timestamp in `properties`.

## Maven

```xml
<dependency>
  <groupId>dev.rafex.ether.http</groupId>
  <artifactId>ether-http-problem</artifactId>
  <version>8.0.0-SNAPSHOT</version>
</dependency>
```
