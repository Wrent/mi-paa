package akucera;

import java.util.Arrays;

/**
 * Class representing the SAT formula.
 */
public class SATFormula {
    //number of variables
    private int n;
    //number of clauses
    private int clausesSize;
    //current number of inserted clauses
    private int clausesCount = 0;

    //how many clauses are fulfilled by the solution
    private int clausesFulfilled = 0;

    //clauses
    private SATClause clauses[];
    //weights of variables
    private int weights[];

    public SATFormula(int n, int clausesSize) {
        this.n = n;
        this.clausesSize = clausesSize;

        this.clauses = new SATClause[clausesSize];
        this.weights = new int[n];

        //random weights between 1 to 10
        for (int i = 0; i < n; i++) {
            this.weights[i] = 1 + (int)(Math.random() * 10);
        }
    }

    /**
     * Inserts SAT clause
     * @param clause
     */
    public void addClause(SATClause clause) {
        clauses[clausesCount++] = clause;
    }

    /**
     * Evaluates the formula with the given variables setting.
     * @param solution
     * @return Boolean
     */
    public boolean evalFormula(SATSolution solution) {
        boolean res = true;
        clausesFulfilled = 0;
        for (int i = 0; i < clausesSize; i++) {
            boolean subResult = clauses[i].eval(solution);
            if (subResult) {
                clausesFulfilled++;
            }
            res = res && subResult;
        }
        return res;
    }

    /**
     * Count how many clauses are fulfilled.
     * @param solution
     * @return
     */
    public int getClausesFulfilled(SATSolution solution) {
        evalFormula(solution);
        return clausesFulfilled;
    }

    /**
     * Computes the sum of weights of the solution.
     * @param solution
     * @return total weight
     */
    public int getSolutionWeight(SATSolution solution) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (solution.getVarVal(i)) {
                res += weights[i];
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "SATFormula{" +
                "clauses=" + Arrays.toString(clauses) +
                "weights=" + Arrays.toString(weights) +
                '}';
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getClausesSize() {
        return clausesSize;
    }

    public void setClausesSize(int clausesSize) {
        this.clausesSize = clausesSize;
    }
}
