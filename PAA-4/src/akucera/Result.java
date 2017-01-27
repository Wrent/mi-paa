package akucera;

/**
 * Created by akucera on 10/20/16.
 */
public class Result {
    private KnapsackConfiguration conf;
    private long annTime = 0;
    private int annPrice = 0;

    private int reference = 0;

    public void setRelError(double relError) {
        this.relError = relError;
    }

    public double getRelError() {
        return relError;
    }

    private int error = 0;
    private double relError = 0;

    public Result(KnapsackConfiguration conf, int reference) {
        this.conf = conf;
        this.reference = reference;
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
        this.error = reference - annPrice;
        this.relError = (double) this.error / reference;
    }

    @Override
    public String toString() {
        return conf.getID() +"\t" + reference +"\t" + annTime +"\t" + annPrice +"\t";
    }

    public String avg(int i) {
        return conf.getID() +"\t" + reference +"\t" + annTime/i +"\t" + relError/i +"\t";
    }
}
