package akucera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackBABExactSolver implements KnapsackSolver {
    private KnapsackConfiguration conf;
    private KnapsackInstance bestInstance;
    private int iterations = 0;

    public KnapsackBABExactSolver(KnapsackConfiguration conf) {
        this.conf = conf;
        this.bestInstance = new KnapsackInstance(conf);
    }

    public KnapsackInstance solve() {
        generateInstances(conf.getN(), "");
        return bestInstance;
    }

    private ArrayList<String> generateInstances(int n, String generated) {
        ArrayList<String> instances = new ArrayList<>();
        KnapsackInstance knapsack = new KnapsackInstance(this.conf);
        knapsack.setInstance(generated);

        this.iterations++;

        //this bag will be too heavy
        if (!knapsack.fits()) {
            return instances;
        }

        if (knapsack.getPrice() > bestInstance.getPrice()) {
            bestInstance = knapsack;
        }

        //this bag cannot be better than best
        if (knapsack.getPrice() + countPriceOfRemainingItems(generated) < bestInstance.getPrice()) {
            return instances;
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


    private int countPriceOfRemainingItems(String instance) {
        List<Item> items = this.conf.getItems();
        int price = 0;
        for (int i = instance.length(); i < this.conf.getN(); i++) {
            price += items.get(i).getPrice();
        }
        return price;
    }

    @Override
    public long getIterations() {
        return iterations;
    }
}
