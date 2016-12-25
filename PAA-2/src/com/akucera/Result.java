package com.akucera;

/**
 * Created by akucera on 10/20/16.
 */
public class Result {
    private KnapsackConfiguration conf;
    private long babTime = 0;
    private int babPrice = 0;
    private long dpTime = 0;
    private int dpPrice = 0;
    private long fptas1Time = 0;
    private int fptas1Price = 0;
    private long fptas2Time = 0;
    private int fptas2Price = 0;
    private long fptas3Time = 0;
    private int fptas3Price = 0;
    private int reference = 0;

    public Result(KnapsackConfiguration conf, int reference) {
        this.conf = conf;
        this.reference = reference;
    }

    public long getBabTime() {
        return babTime;
    }

    public void setBabTime(long babTime) {
        this.babTime = babTime;
    }

    public int getBabPrice() {
        return babPrice;
    }

    public void setBabPrice(int babPrice) {
        this.babPrice = babPrice;
    }

    public long getDpTime() {
        return dpTime;
    }

    public void setDpTime(long dpTime) {
        this.dpTime = dpTime;
    }

    public int getDpPrice() {
        return dpPrice;
    }

    public void setDpPrice(int dpPrice) {
        this.dpPrice = dpPrice;
    }

    public long getFptas1Time() {
        return fptas1Time;
    }

    public void setFptas1Time(long fptas1Time) {
        this.fptas1Time = fptas1Time;
    }

    public int getFptas1Price() {
        return fptas1Price;
    }

    public void setFptas1Price(int fptas1Price) {
        this.fptas1Price = fptas1Price;
    }

    public long getFptas2Time() {
        return fptas2Time;
    }

    public void setFptas2Time(long fptas2Time) {
        this.fptas2Time = fptas2Time;
    }

    public int getFptas2Price() {
        return fptas2Price;
    }

    public void setFptas2Price(int fptas2Price) {
        this.fptas2Price = fptas2Price;
    }

    public long getFptas3Time() {
        return fptas3Time;
    }

    public void setFptas3Time(long fptas3Time) {
        this.fptas3Time = fptas3Time;
    }

    public int getFptas3Price() {
        return fptas3Price;
    }

    public void setFptas3Price(int fptas3Price) {
        this.fptas3Price = fptas3Price;
    }

    @Override
    public String toString() {
        return conf.getID() +"\t" + reference +"\t" + babTime +"\t" + babPrice +"\t" + dpTime +"\t" + dpPrice  +"\t" + fptas1Time +"\t" + fptas1Price  +"\t" + fptas2Time +"\t" + fptas2Price  +"\t" + fptas3Time +"\t" + fptas3Price;
    }
}
