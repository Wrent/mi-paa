#!/usr/bin/env bash
echo "" > ../inst/res5.res
for (( a=30 ; $a-1000 ; a=$a+10 ))
do
    ../knapgen/gen -n 25 -N 10 -m 0.4 -W $a -C 100 -k 1 -d 0 > ../inst/test5.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 5 dp)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res5.res
done
