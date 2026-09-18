#!/bin/sh
set -e
rm -rf out
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out com.smartcampus.app.Main
