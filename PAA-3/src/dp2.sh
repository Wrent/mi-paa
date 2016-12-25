#!/usr/bin/env bash
export LC_NUMERIC="en_US.UTF-8"
echo "" > ../inst/res6.res
for a in $(seq 0.1 0.1 3)
do
    echo $a
    ../knapgen/gen -n 25 -N 10 -m 0.4 -W 80 -C 100 -k $a -d 1 > ../inst/test6.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 6 dp)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res6.res
done