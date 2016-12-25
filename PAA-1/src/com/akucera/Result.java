package com.akucera;

/**
 * Created by akucera on 10/20/16.
 */
public class Result {
    private KnapsackConfiguration conf;
    private long exactTime = 0;
    private int exactPrice = 0;
    private long heuristicTime = 0;
    private int heuristicPrice = 0;
    private int reference = 0;

    public Result(KnapsackConfiguration conf, int reference) {
        this.conf = conf;
        this.reference = reference;
    }

    public long getExactTime() {
        return exactTime;
    }

    public void setExactTime(long exactTime) {
        this.exactTime = exactTime;
    }

    public int getExactPrice() {
        return exactPrice;
    }

    public void setExactPrice(int exactPrice) {
        this.exactPrice = exactPrice;
    }

    public long getHeuristicTime() {
        return heuristicTime;
    }

    public void setHeuristicTime(long heuristicTime) {
        this.heuristicTime = heuristicTime;
    }

    public int getHeuristicPrice() {
        return heuristicPrice;
    }

    public void setHeuristicPrice(int heuristicPrice) {
        this.heuristicPrice = heuristicPrice;
    }

    @Override
    public String toString() {
        return conf.getID() + " " + reference +" " + exactTime + " " + exactPrice + " " + heuristicTime + " " + heuristicPrice;
    }
}
