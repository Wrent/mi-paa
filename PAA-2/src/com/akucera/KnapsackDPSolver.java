package com.akucera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akucera on 30.10.16.
 */
public class KnapsackDPSolver implements KnapsackSolver {

    private KnapsackConfiguration origConf;
    private KnapsackInstance[][] solutions;

    public KnapsackDPSolver(KnapsackConfiguration conf) {
        this.origConf = conf;
    }

    @Override
    public KnapsackInstance solve() {
        solutions = new KnapsackInstance[origConf.getN() + 1][origConf.getM() + 1];
        KnapsackInstance solution = dynamicKnapsack(copyConf(this.origConf));
        return solution;
    }

    private KnapsackInstance dynamicKnapsack(KnapsackConfiguration conf) {
        if (isTrivial(conf)) {
            return trivialKnapsack();
        }

        int n = conf.getN() - 1;

        Item nth = this.origConf.getItems().get(n);

        KnapsackConfiguration conf0 = copyConf(conf);
        KnapsackConfiguration conf1 = copyConf(conf);

        conf0.removeItem(n);
        conf1.removeItem(n);
        conf1.decreaseM(nth.getWeight());

        KnapsackInstance instance0 = checkExistingSolutions(conf0);
        KnapsackInstance instance1 = checkExistingSolutions(conf1);

        if (instance0 == null) {
            instance0 = dynamicKnapsack(conf0);
        }
        if (instance1 == null) {
            instance1 = dynamicKnapsack(conf1);
        }

        if (instance1.getWeight() + nth.getWeight() <= conf.getM() && instance1.getPrice() + nth.getPrice() > instance0.getPrice()) {
            instance1.setNthItem(n, '1');
            solutions[conf.getN()][conf.getM()] = new KnapsackInstance(instance1.getConf(), instance1.getInstance());
            return instance1;
        } else {
            instance0.setNthItem(n, '0');
            solutions[conf.getN()][conf.getM()] = new KnapsackInstance(instance0.getConf(), instance0.getInstance());
            return instance0;
        }
    }

    private boolean isTrivial(KnapsackConfiguration conf) {
        if (conf.getItems().size() == 0) {
            return true;
        }

        if (conf.getM() <= 0) {
            return true;
        }
        return false;
    }

    private KnapsackInstance trivialKnapsack() {
        KnapsackInstance instance = new KnapsackInstance(this.origConf);
        return instance;
    }

    private KnapsackConfiguration copyConf(KnapsackConfiguration conf) {
        ArrayList<Item> items = new ArrayList<>();
        List<Item> origItems = conf.getItems();
        for (int i = 0; i < origItems.size(); i++) {
            items.add(origItems.get(i));
        }
        KnapsackConfiguration copy = new KnapsackConfiguration(conf.getN(), conf.getM(), items, conf.getID());
        return copy;
    }

    private KnapsackInstance checkExistingSolutions(KnapsackConfiguration conf) {
        if (conf.getM() < 0 || conf.getN() < 0) {
            return null;
        }

        if (solutions[conf.getN()][conf.getM()] != null) {
            return solutions[conf.getN()][conf.getM()];
        } else return null;
    }
}
