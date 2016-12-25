package com.akucera;

import java.util.ArrayList;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackConfiguration {
    private int n;
    private int M;
    private int id;
    private ArrayList<Item> items;
    private int sumPrices = -1;

    public KnapsackConfiguration(int n, int m, ArrayList items, int id) {
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


    public ArrayList<Item> getItems() {
        return items;
    }

    public void removeItem(int id) {
        this.items.remove(id);
        n--;
        sumPrices = -1;
    }

    public void decreaseM(int dif) {
        this.M -= dif;
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
        return items.get(n - 1);
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
}
