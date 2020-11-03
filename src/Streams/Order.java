package Streams;

/**
 * @author Mohammed Amr
 * @created 03/11/2020 - 00:37
 * @project Modern Java Recipes
 */
public class Order {

    private int id;

    public Order(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
