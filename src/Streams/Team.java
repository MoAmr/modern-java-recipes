package Streams;

import java.text.NumberFormat;

/**
 * @author Mohammed Amr
 * @created 31/10/2020 - 23:11
 * @project Modern Java Recipes
 */
public class Team {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance();

    private int id;
    private String name;
    private double salary;

    public Team(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String toString() { return "Team{" +
            "id="+id+
            ", name='" + name + '\'' +
            ", salary=" + nf.format(salary) + '}';
    }
}
