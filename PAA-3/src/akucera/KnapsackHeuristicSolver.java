package akucera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by akucera on 10/17/16.
 */
public class KnapsackHeuristicSolver implements KnapsackSolver {
    private KnapsackConfiguration conf;
    private int iterations = 0;

    public KnapsackHeuristicSolver(KnapsackConfiguration conf) {
        this.conf = conf;
    }

    public KnapsackInstance solve() {
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < conf.getItems().size(); i++) {
            items.add(conf.getItems().get(i));
            iterations++;
        }

        Collections.sort(items);
        iterations += items.size() + Math.log(items.size());

        int M = conf.getM();
        int capacity = 0;
        KnapsackInstance instance = new KnapsackInstance(conf);

        for (int i = 0; i < items.size(); i++) {
            iterations++;
            if (capacity + items.get(i).getWeight() < M) {
                instance.setNthItem(items.get(i).getOrigPosition(), '1');
                capacity += items.get(i).getWeight();
            }
        }

        return instance;
    }

    @Override
    public long getIterations() {
        return iterations;
    }

}
