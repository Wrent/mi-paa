package com.akucera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static int TIMES_RUN = 10;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("ERROR: You have to specify a file with input data.");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/inst/knap_"+args[0]+".inst.dat"));
            BufferedReader br2 = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/sol/knap_"+args[0]+".sol.dat"));
            String line, line2;
            int reference;
            while ((line = br.readLine()) != null) {
                line2 = br2.readLine();
                KnapsackInstance solution = null;
                long sumTimes = 0, startTime, endTime;
                KnapsackConfiguration conf = LineParser.parseConfLine(line);
                reference = LineParser.parseResultLine(line2);

                KnapsackExactSolver solver = new KnapsackExactSolver(conf);

                Result result = new Result(conf, reference);


                if (args[1].equals("e") || args[1].equals("b")) {


                    for (int i = 0; i < Main.TIMES_RUN; i++) {

                        startTime = System.nanoTime();

                        solution = solver.solve();

                        endTime = System.nanoTime();

                        sumTimes += endTime - startTime;
                    }

                    result.setExactPrice(solution.getPrice());
                    result.setExactTime(sumTimes / Main.TIMES_RUN);

                }

                if (args[1].equals("h") || args[1].equals("b")) {
                    sumTimes = 0;
                    KnapsackHeuristicSolver solver2 = new KnapsackHeuristicSolver(conf);
                    for (int i = 0; i < Main.TIMES_RUN; i++) {

                        startTime = System.nanoTime();

                        solution = solver2.solve();

                        endTime = System.nanoTime();

                        sumTimes += endTime - startTime;
                    }

                    result.setHeuristicPrice(solution.getPrice());
                    result.setHeuristicTime(sumTimes / Main.TIMES_RUN);
                }
                System.out.println(result);
            }
        } catch (IOException exp) {
            System.err.println("ERROR: Error while reading file "+args[0]);
        }
    }
}
