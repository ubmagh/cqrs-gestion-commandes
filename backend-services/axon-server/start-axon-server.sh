#!/bin/bash

IMAGE_NAME="axoniq/axonserver:4.6.7-jdk-17-dev-nonroot"

docker pull $IMAGE_NAME

docker run -d --name axonserver -p 8024:8024 -p 8124:8124 $IMAGE_NAME