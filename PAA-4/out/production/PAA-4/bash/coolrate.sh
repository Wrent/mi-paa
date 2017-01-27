#!/usr/bin/env bash
echo "" > res2.res
export LC_NUMERIC="en_US.UTF-8"
for a in $(seq 0.0005 0.0005 0.1)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_4_jar/PAA-4.jar 30 7500 $a 0.5 n)"
    echo -e "$a\t${OUTPUT}" >> res2.res
done
