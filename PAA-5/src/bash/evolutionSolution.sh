#!/usr/bin/env bash
echo "" > res6.res
export LC_NUMERIC="en_US.UTF-8"
OUTPUT="$(java -jar ../../out/artifacts/PAA_5_jar/PAA-5.jar 20 20 5000 0.0085 5 0.45 0.3)"
echo -e "$a\t${OUTPUT}" >> res6.res
