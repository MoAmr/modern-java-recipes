package Streams;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammed Amr
 * @created 03/11/2020 - 00:38
 * @project Modern Java Recipes
 */

// A one-to-many relationship
public class Customer {

    private String name;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Customer addOrder(Order order) {
        orders.add(order);
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
