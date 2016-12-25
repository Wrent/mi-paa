#!/usr/bin/env bash
echo "" > ../inst/res2.res

OUTPUT="$(java -jar ../out/artifacts/PAA_3_jar/PAA-3.jar 2 bab)"
echo -e "${OUTPUT}" >> ../inst/res2.res

