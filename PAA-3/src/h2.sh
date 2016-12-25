#!/usr/bin/env bash
export LC_NUMERIC="en_US.UTF-8"
echo "" > ../inst/res8.res
for a in $(seq 10 5 100)
do
    echo $a
    ../knapgen/gen -n $a -N 10 -m 0.4 -W 80 -C 100 -k 1 -d 0 > ../inst/test8.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 8 dp)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res8.res
done