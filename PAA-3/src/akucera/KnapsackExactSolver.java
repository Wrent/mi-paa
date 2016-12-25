package akucera;

import java.util.ArrayList;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackExactSolver implements KnapsackSolver{
    private KnapsackConfiguration conf;
    private KnapsackInstance bestInstance;

    private ArrayList<KnapsackInstance> allInstances;
    private int iterations = 0;


    public KnapsackExactSolver(KnapsackConfiguration conf) {
        this.conf = conf;
        this.bestInstance = new KnapsackInstance(conf);
        allInstances = new ArrayList<>();
    }

    public KnapsackInstance solve() {
        generateInstances(conf.getN(), "");
        return bestInstance;
    }

    private ArrayList<String> generateInstances(int n, String generated) {
        ArrayList<String> instances = new ArrayList<>();
        KnapsackInstance knapsack = new KnapsackInstance(this.conf);
        knapsack.setInstance(generated);

        iterations++;


        if (knapsack.fits() && knapsack.getPrice() > bestInstance.getPrice()) {
            bestInstance = knapsack;
        }

        //this is maximum instance
        if (generated.length() == n) {
            instances.add(generated);
            return instances;
        }

        instances.addAll(generateInstances(n, generated + "0"));
        instances.addAll(generateInstances(n, generated + "1"));

        return instances;
    }

    @Override
    public long getIterations() {
        return iterations;
    }
}
