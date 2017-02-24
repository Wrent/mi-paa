#!/usr/bin/env bash
echo "" > res1.res
for a in $(seq 100 500 25100)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_5_jar/PAA-5.jar 1 20 $a 0.01 5 0.5 0.05)"
    echo -e "$a\t${OUTPUT}" >> res1.res
done
