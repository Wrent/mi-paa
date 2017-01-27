package akucera;

/**
 * Created by akucera on 10/19/16.
 */
public class Item {
    private int price;
    private int weight;


    public Item(int weight, int price) {
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", weight=" + weight +
                '}';
    }
}
