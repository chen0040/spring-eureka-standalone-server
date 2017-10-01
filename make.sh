#!/usr/bin/env bash

mvn -f pom.xml clean package -Plocal -U

cp /target/spring-eureka-server.jar bin/spring-eureka-server.jar
