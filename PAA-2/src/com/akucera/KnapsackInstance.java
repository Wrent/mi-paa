package com.akucera;

import java.util.ArrayList;

/**
 * Created by akucera on 10/16/16.
 */
public class KnapsackInstance {

    private KnapsackConfiguration conf;
    private String instance;
    private int price = -1;
    private int weight = -1;

    public KnapsackInstance(KnapsackConfiguration conf) {
        this.conf = conf;
        this.instance = new String();
        for (int i = 0; i < this.conf.getN(); i++) {
            this.instance = this.instance.concat("0");
        }
    }

    public KnapsackInstance(KnapsackConfiguration conf, String instance) {
        this.conf = conf;
        this.instance = instance;
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
        ArrayList<Item> items = conf.getItems();

        for (int i = 0; i < items.size(); i++) {
            price += items.get(i).getPrice() * Character.getNumericValue(instance.charAt(i));
        }

        return price;
    }

    public int getWeight() {
        weight = 0;
        ArrayList<Item> items = conf.getItems();

        for (int i = 0; i < items.size(); i++) {
            weight += items.get(i).getWeight() * Character.getNumericValue(instance.charAt(i));
        }

        return weight;
    }

    public String getInstance() {
        return instance;
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
}
