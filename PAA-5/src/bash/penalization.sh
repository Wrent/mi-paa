#!/usr/bin/env bash
echo "" > res5.res
export LC_NUMERIC="en_US.UTF-8"
for a in $(seq 0.01 0.01 0.3)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_5_jar/PAA-5.jar 1 20 8100 0.0085 5 0.5 $a)"
    echo -e "$a\t${OUTPUT}" >> res5.res
done
