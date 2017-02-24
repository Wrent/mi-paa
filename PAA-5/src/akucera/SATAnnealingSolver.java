package akucera;

import java.text.DecimalFormat;

/**
 * Simulated annealing solver.
 */
public class SATAnnealingSolver {
    private SATConfiguration conf;

    private SATSolution currentSolution;
    private SATSolution best;

    private double temp;
    private int restarted = 0;

    /**
     * Default init constructor, which generates random solution.
     *
     * @param conf
     */
    public SATAnnealingSolver(SATConfiguration conf) {
        this.conf = conf;
        this.currentSolution = new SATSolution(conf.getFormula());
        this.currentSolution.generateRandom();
        this.best = currentSolution;

        setDefaultTemp(conf);
    }

    private void setDefaultTemp(SATConfiguration conf) {
        temp = conf.getTemp() * conf.getFormula().getN();
    }

    /**
     * Main cycle of simulated annealing.
     *
     * @return Best found solution
     */
    public SATSolution solve() {
        while (temp > 1) {
            SATSolution newSolution = getBestNeighbour();

            int currentEnergy = currentSolution.getSolutionWeight();
            int neighbourEnergy = newSolution.getSolutionWeight();

            //neighbour penalization if it is worse than current solution
            if (best.getClausesFulfilled() > newSolution.getClausesFulfilled()) {
                neighbourEnergy = (int) (neighbourEnergy / 1 + (conf.getWorseSolutionPenalization() * (best.getClausesFulfilled() - newSolution.getClausesFulfilled())));
            }

            //accept new solution as current solution?
            if (acceptanceProbability(currentEnergy, neighbourEnergy) >= Math.random()) {
                currentSolution = newSolution;
                //System.out.println(currentSolution);
            }

            //if best is not solution, then we accept better solutions according to clauses
            if (!best.eval()) {
                if (currentSolution.getClausesFulfilled() > best.getClausesFulfilled()) {
                    best = currentSolution;
                }

                if (currentSolution.getClausesFulfilled() == best.getClausesFulfilled()) {
                    if (currentSolution.getSolutionWeight() > best.getSolutionWeight()) {
                        best = currentSolution;
                    }
                }
            }
            //otherwise we care only about weight
            else {
                if (currentSolution.eval() && currentSolution.getSolutionWeight() > best.getSolutionWeight()) {
                    best = currentSolution;
                }
            }

            cool();
            //DecimalFormat df = new DecimalFormat(" #.###");
            //System.out.println(df.format(temp) + "\t" + currentSolution.getSolutionWeight() + "\t" + currentSolution.getClausesFulfilled() + "\t" + best.getSolutionWeight() + "\t" + best.getClausesFulfilled());
            checkSolution();
        }
        return best;
    }

    /**
     * Method checks if the solution has been found and if not, it will restart the temperature, but it will do it maximally 4 times.
     */
    private void checkSolution() {
        if (temp < 1.05 && restarted < 4 && !best.eval()) {
            setDefaultTemp(conf);
            restarted++;
        }
    }

    /**
     * Generates multiple neighbours and chooses the best one.
     *
     * @return best neighbour
     */
    private SATSolution getBestNeighbour() {
        SATSolution newSolution = getNeighbour(this.currentSolution);
        for (int i = 0; i < conf.getNeighboursToBeGenerated(); i++) {
            SATSolution newSolution2 = getNeighbour(this.currentSolution);
            if (newSolution2.getClausesFulfilled() > newSolution.getClausesFulfilled()) {
                newSolution = newSolution2;
            } else if (newSolution2.getClausesFulfilled() == newSolution.getClausesFulfilled()) {
                if (newSolution2.getSolutionWeight() > newSolution.getSolutionWeight()) {
                    newSolution = newSolution2;
                }
            }
        }
        return newSolution;
    }

    /**
     * Cooling method.
     */
    private void cool() {
        temp *= (1 - conf.getCoolingRate());
    }

    /**
     * Acceptance method.
     *
     * @param energy
     * @param newEnergy
     * @return
     */
    private double acceptanceProbability(int energy, int newEnergy) {
        if (newEnergy > energy) {
            return 1.0;
        }
        return Math.exp((newEnergy - energy) / temp);
    }

    /**
     * Method generating neighbours from a solution.
     *
     * @param currentSolution
     * @return neigbour
     */
    private SATSolution getNeighbour(SATSolution currentSolution) {
        //copy the solution
        SATSolution neighbour = new SATSolution(currentSolution);

        double random = Math.random();

        //change random variable
        if (random > conf.getIncreaseDecreaseThruthnessRatio()) {
            if (neighbour.getNumberOfTrues() == 0) {
                neighbour.setRandomVarTrue();
            } else {
                neighbour.setRandomVarFalse();
            }
        } else {
            if (neighbour.getNumberOfTrues() == conf.getFormula().getN()) {
                neighbour.setRandomVarFalse();
            } else {
                neighbour.setRandomVarTrue();
            }
        }

        return neighbour;
    }

}
