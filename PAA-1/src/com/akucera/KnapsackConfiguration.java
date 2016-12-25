package com.akucera;

import java.util.ArrayList;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackConfiguration {
    private int n;
    private int M;
    private int id;
    private ArrayList items;

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


    @Override
    public String toString() {
        return "KnapsackConfiguration{" +
                "n=" + n +
                ", M=" + M +
                ", items=" + items +
                '}';
    }
}
