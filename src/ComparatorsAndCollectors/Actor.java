package ComparatorsAndCollectors;

/**
 * @author Mohammed Amr
 * @created 06/11/2020 - 02:18
 * @project Modern Java Recipes
 */
public class Actor {

    private String name;
    private String role;

    public Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
