package akucera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main class used for running the experiments and collecting the results.
 */
public class Main {

    private static int TIMES_RUN = 8;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("ERROR: You have to specify a file with input data.");
            return;
        }
        try {
            String from = args[0];
            String to = args[1];
            int i = 0;
            Result avgResult = null;
            for (int j = Integer.parseInt(from); j <= Integer.parseInt(to); j++) {

                BufferedReader br = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/mi-paa/PAA-5/inst/sat_" + j + ".inst.dat"));
                String line;
                SATSolution solution = null;
                long sumTimes = 0, startTime, endTime;
                int sumWeights = 0;

                SATConfiguration conf = FileParser.parseConfFile(br);
                conf.setId(j);

                conf.setTemp(Double.parseDouble(args[2]));
                conf.setCoolingRate(Double.parseDouble(args[3]));
                conf.setNeighboursToBeGenerated(Integer.parseInt(args[4]));
                conf.setIncreaseDecreaseThruthnessRatio(Double.parseDouble(args[5]));
                conf.setWorseSolutionPenalization(Double.parseDouble(args[6]));

                if (avgResult == null) {
                    avgResult = new Result(conf);
                }
                Result result = new Result(conf);


                double relError = 0;
                for (int k = 0; k < TIMES_RUN; k++) {
                    SATAnnealingSolver solver = new SATAnnealingSolver(conf);
                    startTime = System.nanoTime();
                    solution = solver.solve();
                    endTime = System.nanoTime();

                    sumTimes += endTime - startTime;
                    sumWeights += solution.getSolutionWeight();

                    if (solution.getSolutionWeight() > result.getMaxPrice()) {
                        result.setMaxPrice(solution.getSolutionWeight());
                    }
                }
                result.setAnnTime(sumTimes / TIMES_RUN);
                result.setAnnPrice(sumWeights / TIMES_RUN);
                result.setRelError((double) (result.getMaxPrice() - result.getAnnPrice()) / result.getMaxPrice());
                //System.out.println(result);
                avgResult.setAnnTime(sumTimes + avgResult.getAnnTime());
                avgResult.setRelError(result.getRelError() + avgResult.getRelError());
                i++;
            }
            //System.out.println(result);
            //System.out.println("\n\n");
            System.out.println(avgResult.avg(i));
        } catch (IOException exp) {
            System.err.println(exp.toString());
            System.err.println("ERROR: Error while reading file " + args[0]);
        }
    }
}
