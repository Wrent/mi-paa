#!/usr/bin/env bash
echo "" > res3.res
export LC_NUMERIC="en_US.UTF-8"
for a in $(seq 1 1 10)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_5_jar/PAA-5.jar 1 20 8100 0.0085 $a 0.5 0.05)"
    echo -e "$a\t${OUTPUT}" >> res3.res
done
