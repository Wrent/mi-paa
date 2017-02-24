#!/usr/bin/env bash
echo "" > res2.res
export LC_NUMERIC="en_US.UTF-8"
for a in $(seq 0.0005 0.0005 0.1)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_5_jar/PAA-5.jar 1 20 8100 $a 5 0.5 0.05)"
    echo -e "$a\t${OUTPUT}" >> res2.res
done
