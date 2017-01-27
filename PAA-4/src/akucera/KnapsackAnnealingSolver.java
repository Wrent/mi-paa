package akucera;

/**
 * Created by akucera on 17.12.16.
 */
public class KnapsackAnnealingSolver implements KnapsackSolver {


    private final KnapsackConfiguration conf;
    private KnapsackInstance currentSolution;
    private KnapsackInstance best;

    private double temp;
    private double coolingRate;
    private double removeInsertWeight;
    private int neighbourTries;


    public KnapsackAnnealingSolver(KnapsackConfiguration conf) {
        this.conf = conf;
        this.currentSolution = new KnapsackInstance(conf);
        this.currentSolution.generateRandom();
        this.best = currentSolution;

        this.temp = conf.getTemp();
        this.coolingRate = conf.getCoolingRate();
        this.removeInsertWeight = conf.getRemoveInsertWeight();
        this.neighbourTries = conf.getNeighbourTries();
    }

    @Override
    public KnapsackInstance solve() {
        while (temp > 1) {
            //todo get more neighbours
            KnapsackInstance newSolution = getNeighbour(this.currentSolution);

            int currentEnergy = currentSolution.getPrice();
            int neighbourEnergy = newSolution.getPrice();

            if (acceptanceProbability(currentEnergy, neighbourEnergy) >= Math.random()) {
                currentSolution = newSolution;
            }
            if (conf.isFirstInDetail()) {
                System.out.print(currentSolution.getPrice()+",");
            }
            if (currentSolution.getPrice() > best.getPrice()) {
                best = currentSolution;
            }

            cool();
        }
        if (conf.isFirstInDetail()) {
            System.out.print("\n");
        }
        return best;
    }

    private void cool() {
        temp *= 1-coolingRate;
    }

    private double acceptanceProbability(int energy, int newEnergy) {
        if (newEnergy > energy) {
            return 1.0;
        }
//        System.out.println("temp "+temp);
        return Math.exp((newEnergy - energy) / temp);
    }

    private KnapsackInstance getNeighbour(KnapsackInstance currentSolution) {
        KnapsackInstance neighbour = new KnapsackInstance(conf, currentSolution.getInstance());

        int i = 0;
        do {
            double random = Math.random();

            if (random > removeInsertWeight) {
                if (neighbour.getNumberOfItems() == 0) {
                    neighbour.insertRandomItem();
                } else {
                    neighbour.removeRandomItem();
                }
            } else {
                if (neighbour.getNumberOfItems() == conf.getN()) {
                    neighbour.removeRandomItem();
                } else {
                    neighbour.insertRandomItem();
                }
            }
            //TODO include to params?
            if (i > neighbourTries) {
                return currentSolution;
            }
        } while (!neighbour.fits());

        return neighbour;

    }

    @Override
    public long getIterations() {
        return 0;
    }
}
