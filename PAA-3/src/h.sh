#!/usr/bin/env bash
export LC_NUMERIC="en_US.UTF-8"
echo "" > ../inst/res7.res
for a in $(seq 0.05 0.05 1.1)
do
    ../knapgen/gen -n 60 -N 10 -m $a -W 80 -C 100 -k 1 -d 0 > ../inst/test7.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 7 dp)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res7.res
done