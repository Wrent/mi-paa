package akucera;

import java.util.ArrayList;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackConfiguration {
    private int n;
    private int M;
    private int id;
    private Item[] items;
    private int sumPrices = -1;

    private int temp;
    private double coolingRate;
    private double removeInsertWeight;
    private int neighbourTries;
    private double randomInstanceItemProbability;

    private boolean firstInDetail;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public double getCoolingRate() {
        return coolingRate;
    }

    public void setCoolingRate(double coolingRate) {
        this.coolingRate = coolingRate;
    }

    public double getRemoveInsertWeight() {

        return removeInsertWeight;
    }

    public void setRemoveInsertWeight(double removeInsertWeight) {
        this.removeInsertWeight = removeInsertWeight;
    }

    public int getNeighbourTries() {
        return neighbourTries;
    }

    public void setNeighbourTries(int neighbourTries) {
        this.neighbourTries = neighbourTries;
    }

    public double getRandomInstanceItemProbability() {
        return randomInstanceItemProbability;
    }

    public void setRandomInstanceItemProbability(double randomInstanceItemProbability) {
        this.randomInstanceItemProbability = randomInstanceItemProbability;
    }


    public KnapsackConfiguration(int n, int m, Item[] items, int id) {
        this.n = n;
        this.M = m;
        this.items = items;
        this.id = id;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return M;
    }

    public int getID() {
        return id;
    }


    public Item[] getItems() {
        return items;
    }

    public int getPricesSum() {
        if (sumPrices == -1) {
            int sum = 0;
            for (Item item : items) {
                sum += item.getPrice();
            }
            sumPrices = sum;
            return sum;
        }
        else {
            return sumPrices;
        }
    }

    public Item getNthItem(int n) {
        return items[n];
    }

    @Override
    public String toString() {
        return "KnapsackConfiguration{" +
                "n=" + n +
                ", M=" + M +
                ", items=" + items +
                '}';
    }

    public int getMaxPrice() {
        int max = 0;
        for (Item i : items) {
            if (i.getPrice() > max) {
                max = i.getPrice();
            }
        }
        return max;
    }

    public boolean isFirstInDetail() {
        return firstInDetail;
    }

    public void logFirstInDetail(boolean b) {
        firstInDetail = b;
    }
}
