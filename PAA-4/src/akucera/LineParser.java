package akucera;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by akucera on 10/16/16.
 */
public class LineParser {
    public static KnapsackConfiguration parseConfLine(String line) {
        Scanner scanner = new Scanner(line);
        List<Integer> list = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        int id, m, n;
        id = list.get(0);
        n = list.get(1);
        m = list.get(2);

        Item[] items = new Item[n];

        int i;
        for (i = 0; i < 2*n; i = i + 2) {
            items[i/2] = new Item(list.get(3 + i), list.get(3 + i + 1));
        }

        KnapsackConfiguration conf = new KnapsackConfiguration(n, m, items, id);
        return conf;
    };

    public static int parseResultLine(String line) {
        Scanner scanner = new Scanner(line);
        List<Integer> list = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        return list.get(2);
    };
}
