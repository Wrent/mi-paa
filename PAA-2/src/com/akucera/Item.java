package com.akucera;

/**
 * Created by akucera on 10/19/16.
 */
public class Item implements Comparable {
    private int price;
    private int weight;
    private int origPosition;

    private float ratio;

    public int getOrigPosition() {
        return origPosition;
    }

    public Item(int weight, int price, int position) {
        this.price = price;
        this.weight = weight;
        this.origPosition = position;


        /*if (weight != 0) {
            this.ratio = ( (float) price / weight);
        } else {
            this.ratio = Float.MAX_VALUE;
        }*/

    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public float getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", weight=" + weight +
                ", ratio=" + ratio +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Item item = (Item) o;
        if (this.getRatio() < item.getRatio()) {
            return 1;
        } else if(this.getRatio() == item.getRatio()) {
            return 0;
        } else {
            return -1;
        }
    }
}
