#!/usr/bin/env bash
echo "" > res3.res
export LC_NUMERIC="en_US.UTF-8"
for a in $(seq 0.3 0.01 0.7)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_4_jar/PAA-4.jar 30 7500 0.003 $a n)"
    echo -e "$a\t${OUTPUT}" >> res3.res
done
