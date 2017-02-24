#!/usr/bin/env bash
echo "" > res4.res
export LC_NUMERIC="en_US.UTF-8"
for a in $(seq 0.2 0.05 0.8)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_5_jar/PAA-5.jar 1 20 8100 0.0085 5 $a 0.05)"
    echo -e "$a\t${OUTPUT}" >> res4.res
done
