package com.akucera;

/**
 * Created by akucera on 7.11.16.
 */
public class KnapsackFPTASSolver implements KnapsackSolver {
    private  KnapsackConfiguration origConf;
    private int[][] solutions;
    private double err;
    private int k;
    private int pricesSum = -1;

    public KnapsackFPTASSolver(KnapsackConfiguration conf, double err) {
        origConf = conf;
        this.err = err;
        k = (int) ((this.err*conf.getMaxPrice())/conf.getN());
        if (k == 0) {
            k = 1;
        }
    }

    @Override
    public KnapsackInstance solve() {
        solutions = new int[origConf.getN() + 1][countPricesSum(origConf) + 1];
        for (int i = 0; i < origConf.getN() + 1; i++) {
            for (int j = 0; j < countPricesSum(origConf) + 1; j++) {
                solutions[i][j] = 0;
            }
        }
        for (int i = 1; i < countPricesSum(origConf) + 1; i++) {
            solutions[0][i] = Integer.MAX_VALUE;
        }


        for (int i = 1; i < countPricesSum(origConf) + 1; i++) {
            for (int j = 1; j < origConf.getN() + 1; j++) {
                int res = countCell(j, i);
                solutions[j][i] = res;
            }
        }

        //find best M in last column
        int m = solutions[origConf.getN()][countPricesSum(origConf)];
        int i = 1;
        while (m > origConf.getM()) {
            m = solutions[origConf.getN()][countPricesSum(origConf) - i];
            i++;
        }
        int price = countPricesSum(origConf) - i + 1;

        KnapsackInstance instance = new KnapsackInstance(origConf);
        for (int j = origConf.getN() - 1; j > -1; j--){
            if (solutions[j+1][price] == solutions[j][price]) {
                instance.setNthItem(j, '0');
            } else {
                instance.setNthItem(j, '1');
                price -= countItemPrice(origConf.getNthItem(j + 1));
            }
        }
        return instance;
    }

    private int countCell(int i, int c) {
        int a = solutions[i-1][c];
        Item nth = origConf.getNthItem(i);
        int b;
        if (c - countItemPrice(nth) < 0 ) {
            b = Integer.MAX_VALUE;
        } else {
            int weight = solutions[i - 1][c - countItemPrice(nth)];
            if (weight + nth.getWeight() < 0) {
                b = Integer.MAX_VALUE;
            } else {
                b = weight + nth.getWeight();
            }
        }
        return Math.min(a, b);
    }

    private int countItemPrice(Item i) {
        return i.getPrice() / k;
    }

    private int countPricesSum(KnapsackConfiguration conf) {
        if (pricesSum != -1) {
            return pricesSum;
        }
        int sum = 0;
        for (Item i : conf.getItems()) {
            sum += countItemPrice(i);
        }
        //System.out.println("sum: "+sum);
        pricesSum = sum;
        return sum;
    }
}
