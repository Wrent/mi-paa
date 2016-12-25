#!/usr/bin/env bash
echo "" > ../inst/res4.res
for (( a=10 ; $a-120 ; a=$a+5 ))
do
    ../knapgen/gen -n 25 -N 10 -m 0.$a -W 80 -C 100 -k 1 -d 0 > ../inst/test4.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 4 bab)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res4.res
done
