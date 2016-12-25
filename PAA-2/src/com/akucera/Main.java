package com.akucera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static int TIMES_RUN = 10;

    //TODO predelat Array listy na pole?
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("ERROR: You have to specify a file with input data.");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/inst/knap_" + args[0] + ".inst.dat"));
            BufferedReader br2 = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/sol/knap_" + args[0] + ".sol.dat"));
            String line, line2;
            int reference;
            while ((line = br.readLine()) != null) {
                line2 = br2.readLine();
                KnapsackInstance solution = null;
                long sumTimes = 0, startTime, endTime;
                KnapsackConfiguration conf = LineParser.parseConfLine(line);
                reference = LineParser.parseResultLine(line2);

                Result result = new Result(conf, reference);

                KnapsackSolver solver = new KnapsackDPSolver(conf);

               for (int i = 0; i < Main.TIMES_RUN; i++) {

                    startTime = System.nanoTime();

                    solution = solver.solve();
                    endTime = System.nanoTime();

                    sumTimes += endTime - startTime;
                }

                result.setDpPrice(solution.getPrice());
                result.setDpTime(sumTimes / Main.TIMES_RUN);


                if (solution.getPrice() != reference) {
                    System.err.println(conf.getID() + " DP Wrong solution!");
                    System.err.println(solution);
                }


                solver = new KnapsackFPTASSolver(conf, 0.1);
                sumTimes = 0;


                for (int i = 0; i < Main.TIMES_RUN; i++) {

                    startTime = System.nanoTime();

                    solution = solver.solve();
                    endTime = System.nanoTime();

                    sumTimes += endTime - startTime;
                }


                result.setFptas1Price(solution.getPrice());
                result.setFptas1Time(sumTimes / Main.TIMES_RUN);

                solver = new KnapsackFPTASSolver(conf, 0.2);
                sumTimes = 0;

                for (int i = 0; i < Main.TIMES_RUN; i++) {

                    startTime = System.nanoTime();

                    solution = solver.solve();
                    endTime = System.nanoTime();

                    sumTimes += endTime - startTime;
                }


                result.setFptas2Price(solution.getPrice());
                result.setFptas2Time(sumTimes / Main.TIMES_RUN);


                solver = new KnapsackFPTASSolver(conf, 0.5);
                sumTimes = 0;

                for (int i = 0; i < Main.TIMES_RUN; i++) {

                    startTime = System.nanoTime();

                    solution = solver.solve();
                    endTime = System.nanoTime();

                    sumTimes += endTime - startTime;
                }


                result.setFptas3Price(solution.getPrice());
                result.setFptas3Time(sumTimes / Main.TIMES_RUN);


                if (!(args.length > 1 && args[1].equals("n"))) {
                    solver = new KnapsackBABExactSolver(conf);
                    sumTimes = 0;
                    for (int i = 0; i < Main.TIMES_RUN; i++) {

                        startTime = System.nanoTime();

                        solution = solver.solve();
                        endTime = System.nanoTime();

                        sumTimes += endTime - startTime;
                    }

                    result.setBabPrice(solution.getPrice());
                    result.setBabTime(sumTimes / Main.TIMES_RUN);


                    if (solution.getPrice() != reference) {
                        System.err.println(conf.getID() + " BAB Wrong solution!");
                        System.err.println(solution);
                    }
                }

                System.out.println(result);
            }
        } catch (IOException exp) {
            System.err.println("ERROR: Error while reading file " + args[0]);
        }
    }
}
