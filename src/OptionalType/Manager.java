package OptionalType;

/**
 * @author Mohammed Amr
 * @created 15/11/2020 - 21:48
 * @project Modern Java Recipes
 */
public class Manager {

    private String name; // Assumed not null, so no need for Optionals

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                '}';
    }
}
