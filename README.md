# MSL Ratings Edge

This repository is a sub-repository of the [Kenzan Million Song Library](https://github.com/kenzanmedia/million-song-library) (MSL) project, a microservices-based Web application built using [AngularJS](https://angularjs.org/), a [Cassandra](http://cassandra.apache.org/) NoSQL database, and [Netflix OSS](http://netflix.github.io/) tools.

> **NOTE:** For an overview of the Million Song Library microservices architecture, as well as step-by-step instructions for running the MSL demonstration, see the [Million Song Library Project Documentation](https://github.com/kenzanmedia/million-song-library/tree/develop/docs).

## Overview

You can run build all of the MSL microservices by running `mvn clean compile` from the `server` directory of the main [million-song-library](https://github.com/kenzanmedia/million-song-library/tree/develop/server) repository. Or use the commands below to build, run, and test a single microservice.

> **NOTE:** If you receive an error when running any of the commands below, try using `sudo` (Mac and Linux) or run PowerShell as an administrator (Windows).

## Server Generation

Use the following command to generate server source code from the Swagger definition, run tests, and install dependencies:

```
mvn -P build clean generate-sources install
```

Use the following command to run the server on port `9004`:

```
mvn -P dev clean jetty:run
```

## Code Formatting

If you make changes to the application code, use the following command to format the code according to [project styles and standards](https://github.com/kenzanmedia/styleguide):

```
mvn clean formatter:format
```

## Dependencies

Use the following command to install dependencies without running tests:

```
mvn -P no-tests clean install
```

## JAR Packaging

Use the following command to package the application code:

```
mvn -P no-tests package
```

## Testing and Reports

You can use either Cobertura or EclEmma to run tests, whichever you prefer.

### Cobertura

Use the following command to run all unit tests and generate a report on test coverage (report is located in `/target/site/cobertura/index.html`):

```
mvn cobertura:cobertura
```

### EclEmma 

Use the following command to run all unit tests and generate a report on test coverage (report is located in `/target/site/jacoco`):

```
mvn package
```
