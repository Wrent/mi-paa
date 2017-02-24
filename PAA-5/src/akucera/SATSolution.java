package akucera;

import java.util.Arrays;
import java.util.Random;

/**
 * Class representing an evalution of variables.
 */
public class SATSolution {

    private SATFormula formula;
    private boolean solution[];

    private int trues = 0;

    private int res = -1;
    private int weight = -1;
    private int clausesFulfilled = -1;

    /**
     * Default constructor generating all falses.
     * @param formula
     */
    public SATSolution(SATFormula formula) {
        this.formula = formula;
        solution = new boolean[formula.getN()];

        for (int i = 0; i<formula.getN(); i++) {
            solution[i] = false;
        }
    }

    /**
     * Copy construktor
     * @param currentSolution
     */
    public SATSolution(SATSolution currentSolution) {
        formula = currentSolution.getFormula();
        solution = new boolean[currentSolution.getSolutionLength()];

        for (int i = 0; i < solution.length; i++) {
            solution[i] = currentSolution.getVarVal(i);
            if (solution[i] == true) {
                trues++;
            }
        }
    }

    /**
     * Randomizing the generated solution.
     */
    public void generateRandom() {
        boolean val;
        for (int i = 0; i < solution.length; i++) {
            if (Math.random() < 0.55) {
                val = true;
                trues++;
            } else {
                val = false;
            }
            solution[i] = val;
        }
    }

    /**
     * method for setting a value for specific variable.
     * @param i
     * @param val
     */
    public void setVarVal(int i, boolean val) {
        //we count change in "trues" count only if the value of changed variable differs
        if (solution[i] != val) {
            if (val == true) {
                trues++;
            } else {
                trues--;
            }
            resetResults();
        }
        solution[i] = val;
    }

    /**
     * Evaluates the formula with the given variables setting.
     * @return Boolean
     */
    public boolean eval() {
        if (res == -1) {
            res = formula.evalFormula(this) ? 1 : 0;
        }
        return res == 1;
    }

    /**
     * Finds out how many clauses are evaluated.
     * @return
     */
    public int getClausesFulfilled() {
        if (clausesFulfilled == -1) {
            clausesFulfilled = formula.getClausesFulfilled(this);
        }
        return clausesFulfilled;
    }

    /**
     * Finds out the weight of the solution.
     * @return
     */
    public int getSolutionWeight() {
        if (weight == -1) {
            weight = formula.getSolutionWeight(this);
        }
        return weight;
    }

    /**
     * Sets random false variable to true.
     */
    public void setRandomVarTrue() {
        int pos = getRandomPositionOfVarWithVal(false);
        setVarVal(pos, true);
    }

    /**
     * Sets random true variable to false.
     */
    public void setRandomVarFalse() {
        int pos = getRandomPositionOfVarWithVal(true);
        setVarVal(pos, false);
    }

    /**
     * Finds a position of a random variable with a given value.
     * @param val
     * @return
     */
    private int getRandomPositionOfVarWithVal(boolean val) {
        int[] positions = new int[solution.length];
        int j = 0;

        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == val) {
                positions[j++] = i;
            }
        }

        Random rand = new Random();
        return positions[rand.nextInt(j)];
    }


    public boolean getVarVal(int i) {
        return solution[i];
    }

    public int getSolutionLength() {
        return solution.length;
    }

    public int getNumberOfTrues() {
        return trues;
    }

    public SATFormula getFormula() {
        return formula;
    }

    private void resetResults() {
        weight = -1;
        clausesFulfilled = -1;
        res = -1;
    }

    @Override
    public String toString() {
        return "SATSolution{" +
               // "solution=" + Arrays.toString(solution) +
                "eval=" + eval() +
                ", clauses=" + getClausesFulfilled() +
                ", weight=" + getSolutionWeight() +
                '}';
    }

}
