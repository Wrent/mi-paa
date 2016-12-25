package akucera;

/**
 * Created by akucera on 10/20/16.
 */
public class Result {
    private KnapsackConfiguration conf;
    private long bfTime = 0;
    private int bfPrice = 0;
    private long dpTime = 0;
    private int dpPrice = 0;
    private long babTime = 0;
    private int babPrice = 0;
    private long heuristicTime = 0;
    private int heuristicPrice = 0;
    private double heuristicRelError = 0;
    private int reference = 0;

    public Result(KnapsackConfiguration conf, int reference) {
        this.conf = conf;
        this.reference = reference;
    }

    public long getBfTime() {
        return bfTime;
    }

    public void setBfTime(long bfTime) {
        this.bfTime = bfTime;
    }

    public int getBfPrice() {
        return bfPrice;
    }

    public void setBfPrice(int bfPrice) {
        this.bfPrice = bfPrice;
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
        return conf.getID() +"\t" + bfTime +"\t" + bfPrice +"\t" + dpTime +"\t" + dpPrice  +"\t" + babTime +"\t" + babPrice +"\t" + heuristicTime +"\t" + heuristicPrice +"\t" + heuristicRelError;
    }

    public String avg(int i) {
        return conf.getID() +"\t" + bfTime/i +"\t" + bfPrice +"\t" + dpTime/i +"\t" + dpPrice  +"\t" + babTime/i +"\t" + babPrice +"\t" + heuristicTime/i +"\t" + heuristicPrice+"\t" + heuristicRelError/i;

    }

    public double getHeuristicRelError() {
        return heuristicRelError;
    }

    public void setHeuristicRelError(double heuristicRelError) {
        this.heuristicRelError = heuristicRelError;
    }
}
