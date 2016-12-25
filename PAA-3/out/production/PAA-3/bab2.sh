#!/usr/bin/env bash
echo "" > ../inst/res9.res
for (( a=5 ; $a-28 ; a=$a+1 ))
do
    ../knapgen/gen -n $a -N 10 -m 0.4 -W 80 -C 100 -k 1 -d 0 > ../inst/test9.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 9 bab)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res9.res
done
