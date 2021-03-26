#!/bin/bash

mvn package && native-image --report-unsupported-elements-at-runtime \
--initialize-at-build-time \
--no-server \
-jar target/bloggo-1.0.jar \
-H:Name=bloggo