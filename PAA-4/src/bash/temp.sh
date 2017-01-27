#!/usr/bin/env bash
echo "" > res1.res
for a in $(seq 100 500 25100)
do
    OUTPUT="$(java -jar ../../out/artifacts/PAA_4_jar/PAA-4.jar 30 $a 0.003 0.5 n)"
    echo -e "$a\t${OUTPUT}" >> res1.res
done
