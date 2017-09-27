#!/usr/bin/env bash
./gradlew clean fatJar
java -jar build/libs/com.ubs.takehome-all-1.0.jar
