package akucera;

import java.util.Arrays;

/**
 * Class representing a single clause.
 */
public class SATClause {

    private int clauseDef[];

    public SATClause(int n) {
        clauseDef = new int[n];

        //set all variables to 0 = not in clause
        //1 = in clause
        //-1 = in clause as negation
        for (int i = 0; i < n; i++) {
            clauseDef[i] = 0;
        }
    }

    /**
     * Evaluates the clause according to given solution.
     * @param solution
     * @return Boolean
     */
    public boolean eval(SATSolution solution) {
        boolean res = false;
        for (int i = 0; i < clauseDef.length; i++) {
            //if not in clause
            if (clauseDef[i] == 0) {
                continue;
            }
            //if in clause
            else if (clauseDef[i] == 1) {
                res = res || solution.getVarVal(i);
            }
            //if in clause as negation
            else {
                res = res || !solution.getVarVal(i);
            }
        }
        return res;
    }

    public void setVarInClause(int i, int val) {
        clauseDef[i - 1] = val;
    }

    @Override
    public String toString() {
        return "SATClause{" +
                "clauseDef=" + Arrays.toString(clauseDef) +
                '}';
    }
}
