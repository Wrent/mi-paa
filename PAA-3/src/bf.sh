#!/usr/bin/env bash
echo "" > ../inst/res3.res
for (( a=4 ; $a-23 ; a=$a+1 ))
do
    ../knapgen/gen -n $a -N 10 -m 0.35 -W 80 -C 100 -k 1 -d 0 > ../inst/test3.inst
    OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 3 bf)"
    echo -e "$a\t${OUTPUT}" >> ../inst/res3.res
done
