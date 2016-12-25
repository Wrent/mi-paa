package akucera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static int TIMES_RUN = 1;

    //TODO predelat Array listy na pole?
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("ERROR: You have to specify a file with input data.");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-3/inst/test" + args[0] + ".inst"));
            //BufferedReader br2 = new BufferedReader(new FileReader("/home/akucera/Dropbox/skola/PAA-1/sol/knap_" + args[0] + ".sol.dat"));
            String line, line2;
            int reference;
            int i = 0;
            Result avgResult = null;
            while ((line = br.readLine()) != null) {
               // line2 = br2.readLine();
                i++;
                //todo implementovat prumerovani instanci uz tady?
                KnapsackInstance solution = null;
                KnapsackSolver solver = null;
                long sumTimes = 0, startTime, endTime;
                KnapsackConfiguration conf = LineParser.parseConfLine(line);
                reference = 0;

                if (avgResult == null) {
                    avgResult = new Result(conf, reference);
                }
                Result result = new Result(conf, reference);

                if (args.length <= 1 || args.length > 1 && args[1].equals("bf")) {
                    solver = new KnapsackExactSolver(conf);

                    solution = solver.solve();

                    result.setBfPrice(solution.getPrice());
                    result.setBfTime(solver.getIterations());
                    avgResult.setBfPrice(solution.getPrice());
                    avgResult.setBfTime(avgResult.getBfTime() + solver.getIterations());
                }

                if (args.length <= 1 || args.length > 1 && args[1].equals("bab")) {

                    solver = new KnapsackBABExactSolver(conf);

                    solution = solver.solve();

                    result.setBabPrice(solution.getPrice());
                    result.setBabTime(solver.getIterations());
                    avgResult.setBabPrice(solution.getPrice());
                    avgResult.setBabTime(avgResult.getBabTime() + solver.getIterations());
                }

                if (args.length <= 1 || args.length > 1 && args[1].equals("dp")) {

                    solver = new KnapsackDPSolver(conf);

                    solution = solver.solve();

                    int dpPrice = solution.getPrice();
                    result.setDpPrice(solution.getPrice());
                    result.setDpTime(solver.getIterations());
                    avgResult.setDpPrice(solution.getPrice());
                    avgResult.setDpTime(avgResult.getDpTime() + solver.getIterations());


                    solver = new KnapsackHeuristicSolver(conf);

                    solution = solver.solve();

                    result.setHeuristicPrice(solution.getPrice());
                    result.setHeuristicTime(solver.getIterations());
                    double relError = (double) (dpPrice-solution.getPrice())/dpPrice;
                    result.setHeuristicRelError(relError);
                    avgResult.setHeuristicPrice(solution.getPrice());
                    avgResult.setHeuristicTime(avgResult.getHeuristicTime() + solver.getIterations());
                    avgResult.setHeuristicRelError(avgResult.getHeuristicRelError() + relError);
                }
                System.out.println(result);
            }
            System.out.println("\n\n");
            System.out.println(avgResult.avg(i));
        } catch (IOException exp) {
            System.err.println(exp.toString());
            System.err.println("ERROR: Error while reading file " + args[0]);
        }
    }
}
