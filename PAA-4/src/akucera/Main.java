package akucera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static int TIMES_RUN = 7;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("ERROR: You have to specify a file with input data.");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/inst/knap_" + args[0] + ".inst.dat"));
            BufferedReader br2 = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/sol/knap_" + args[0] + ".sol.dat"));
            String line, line2;
            int reference, i = 0;
            Result avgResult = null;
            while ((line = br.readLine()) != null) {
                line2 = br2.readLine();
                KnapsackInstance solution = null;
                KnapsackSolver solver = null;
                long sumTimes = 0, startTime, endTime;
                KnapsackConfiguration conf = LineParser.parseConfLine(line);
                conf.setTemp(Integer.parseInt(args[1]));
                conf.setCoolingRate(Double.parseDouble(args[2]));
                //quite influence on time and precision
                conf.setRemoveInsertWeight(Double.parseDouble(args[3]));
                if (args[4].equals("l") && i < 1) {
                    conf.logFirstInDetail(true);
                } else {
                    conf.logFirstInDetail(false);
                }


                //fixed cause this is influencing just time a little
                conf.setNeighbourTries(100);
                //doesnt seem to be very significant, so we want rather instances with less items in the beginning
                conf.setRandomInstanceItemProbability(0.75);

                reference = LineParser.parseResultLine(line2);

                if (avgResult == null) {
                    avgResult = new Result(conf, 0);
                }
                Result result = new Result(conf, reference);

                solver = new KnapsackAnnealingSolver(conf);

                double relError = 0;
                for (int j = 0; j < TIMES_RUN; j++) {
                    startTime = System.nanoTime();
                    solution = solver.solve();
                    endTime = System.nanoTime();

                    sumTimes += endTime - startTime;
                    relError += (double) (reference - solution.getPrice())/reference;
                }
                result.setRelError(relError/TIMES_RUN);
                result.setAnnTime(sumTimes/TIMES_RUN);
                avgResult.setAnnTime(sumTimes + avgResult.getAnnTime());
                avgResult.setRelError(result.getRelError() + avgResult.getRelError());
                //System.out.println(result);
                i++;
            }
            //System.out.println("\n\n");
            System.out.println(avgResult.avg(i));
        } catch (IOException exp) {
            System.err.println(exp.toString());
            System.err.println("ERROR: Error while reading file " + args[0]);
        }
    }
}
