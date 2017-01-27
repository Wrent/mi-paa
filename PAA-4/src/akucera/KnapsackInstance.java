package akucera;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackInstance {

    private KnapsackConfiguration conf;
    private String instance;
    private int price = -1;
    private int weight = -1;
    private int numberOfItems = 0;

    public KnapsackInstance(KnapsackConfiguration conf) {
        this.conf = conf;
        this.instance = new String();
        for (int i = 0; i < this.conf.getN(); i++) {
            this.instance = this.instance.concat("0");
        }
        numberOfItems = 0;
    }

    public KnapsackInstance(KnapsackConfiguration conf, String instance) {
        this.conf = conf;
        this.instance = instance;
        numberOfItems = countNumberOfItems(instance);
    }

    public void generateRandom() {
        int items = 0;
        int k = 0;
        this.instance = new String();
        do {
            for (int i = 0; i < this.conf.getN(); i++) {
                String append;
                //TODO include to params
                if (Math.random() > conf.getRandomInstanceItemProbability()) {
                    items++;
                    append = "1";
                } else {
                    append = "0";
                }
                this.instance = this.instance.concat(append);
            }
            k++;
            //TODO include to Params?
            if (k > conf.getNeighbourTries()) {
                this.instance = new String();
                for (int i = 0; i < this.conf.getN(); i++) {
                    this.instance = this.instance.concat("0");
                }
                items = 0;
                break;
            }
        } while (!fits());
        numberOfItems = items;
    }

    private int countNumberOfItems(String instance) {
        int count = 0;
        for (int i=0; i < instance.length(); i++)
        {
            if (instance.charAt(i) == '1')
            {
                count++;
            }
        }
        return count;
    }


    public void setInstance(String instance) {
        //doplnit instanci nulama
        while (instance.length() < this.conf.getN()) {
            instance = instance.concat("0");
        }
        this.instance = instance;
    }

    public void setNthItem(int n, char c) {
        StringBuilder sb = new StringBuilder(this.instance);
        sb.setCharAt(n, c);
        this.instance = sb.toString();
    }

    public void evaluate() {
        getPrice();
        getWeight();
    }


    public boolean fits() {
        if (getWeight() > conf.getM()) {
            return false;
        }
        return true;
    }

    public int getPrice() {
        price = 0;
        Item[] items = conf.getItems();

        for (int i = 0; i < items.length; i++) {
            price += items[i].getPrice() * Character.getNumericValue(instance.charAt(i));
        }

        return price;
    }

    public int getWeight() {
        weight = 0;
        Item[] items = conf.getItems();

        for (int i = 0; i < items.length; i++) {
            weight += items[i].getWeight() * Character.getNumericValue(instance.charAt(i));
        }

        return weight;
    }

    public String getInstance() {
        return instance;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public KnapsackConfiguration getConf() {
        return conf;
    }

    @Override
    public String toString() {
        return "KnapsackInstance{" +
                "instance='" + instance + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }

    public void removeRandomItem() {
        int pos = getRandomPositionOf('1');
        setNthItem(pos, '0');
        numberOfItems--;
    }

    public void insertRandomItem() {
        int pos = getRandomPositionOf('0');
        setNthItem(pos, '1');
        numberOfItems++;
    }

    private int getRandomPositionOf(char c) {
        int[] positions = new int[conf.getN()];
        int i = 0;

        int index = -1;
        do {
            index = instance.indexOf(c, index + 1);
            if (index != -1) {
                positions[i++] = index;
            }
        } while (index > 0);

        Random rand = new Random();
        return positions[rand.nextInt(i)];
    }


}
