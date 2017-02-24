package akucera;

/**
 * Created by akucera on 10/20/16.
 */
public class Result {
    private SATConfiguration conf;
    private long annTime = 0;
    private int annPrice = 0;
    private int maxPrice = 0;
    private double relError = 0;

    public Result(SATConfiguration conf) {
        this.conf = conf;
    }

    public double getRelError() {
        return relError;
    }

    public void setRelError(double relError) {
        this.relError = relError;
    }

    public long getAnnTime() {
        return annTime;
    }

    public void setAnnTime(long annTime) {
        this.annTime = annTime;
    }

    public int getAnnPrice() {
        return annPrice;
    }

    public void setAnnPrice(int annPrice) {
        this.annPrice = annPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return conf.getId() +"\t" + annTime +"\t" + annPrice +"\t" + relError +"\t";
    }

    public String avg(int i) {
        return conf.getId() +"\t" + annTime/i +"\t" + relError/i;
    }
}
