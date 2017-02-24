package akucera;

/**
 * Represents the SATConfiguration, which should be solved. Also contains annealing parameters.
 */
public class SATConfiguration {
    private SATFormula formula;

    private int id;

    private double temp;
    private double coolingRate;
    private int neighboursToBeGenerated;
    private double increaseDecreaseThruthnessRatio;
    private double worseSolutionPenalization;

    public SATConfiguration(SATFormula formula) {
        this.formula = formula;
    }

    public SATFormula getFormula() {
        return formula;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getCoolingRate() {
        return coolingRate;
    }

    public void setCoolingRate(double coolingRate) {
        this.coolingRate = coolingRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNeighboursToBeGenerated() {
        return neighboursToBeGenerated;
    }

    public void setNeighboursToBeGenerated(int neighboursToBeGenerated) {
        this.neighboursToBeGenerated = neighboursToBeGenerated;
    }

    public double getIncreaseDecreaseThruthnessRatio() {
        return increaseDecreaseThruthnessRatio;
    }

    public void setIncreaseDecreaseThruthnessRatio(double increaseDecreaseThruthnessRatio) {
        this.increaseDecreaseThruthnessRatio = increaseDecreaseThruthnessRatio;
    }

    public double getWorseSolutionPenalization() {
        return worseSolutionPenalization;
    }

    public void setWorseSolutionPenalization(double worseSolutionPenalization) {
        this.worseSolutionPenalization = worseSolutionPenalization;
    }
}
