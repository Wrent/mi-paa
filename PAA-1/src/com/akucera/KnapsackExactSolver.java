package com.akucera;

import java.util.ArrayList;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackExactSolver {
    private KnapsackConfiguration conf;
    private KnapsackInstance bestInstance;
    private ArrayList<KnapsackInstance> allInstances;


    public KnapsackExactSolver(KnapsackConfiguration conf) {
        this.conf = conf;
        this.bestInstance = new KnapsackInstance(conf);
        allInstances = new ArrayList<>();
    }

    public KnapsackInstance solve() {
        generateInstances(conf.getN());

        for (int i = 0; i < allInstances.size(); i++) {
            KnapsackInstance knapsack = allInstances.get(i);
            if (knapsack.fits() && knapsack.getPrice() > bestInstance.getPrice()) {
                bestInstance = knapsack;
            }
        }
        return bestInstance;
    }

    private void generateInstances(int n) {
        ArrayList<String> strings = generateStrings(n, "");

        for (int i = 0; i < strings.size(); i ++) {
            KnapsackInstance knapsack = new KnapsackInstance(this.conf);
            knapsack.setInstance(strings.get(i));
            allInstances.add(knapsack);
        }
    }

    private ArrayList<String> generateStrings(int n, String generated) {
        ArrayList<String> instances = new ArrayList<>();
        if (generated.length() == n) {
            instances.add(generated);
            return instances;
        }

        instances.addAll(generateStrings(n, generated + "0"));
        instances.addAll(generateStrings(n, generated + "1"));

        return instances;
    }
}
