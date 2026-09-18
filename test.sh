#!/bin/sh
set -e
rm -rf out
mkdir -p out
javac -d out $(find src tests -name '*.java')
java -ea -cp out tests.TaskManagerTest
