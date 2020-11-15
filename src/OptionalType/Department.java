package OptionalType;

import java.util.Optional;

/**
 * @author Mohammed Amr
 * @created 15/11/2020 - 21:49
 * @project Modern Java Recipes
 */
public class Department {

    private Manager boss;

    public Optional<Manager> getBoss() { // Might be null, so wrap getter return in an Optional, but not setter
        return Optional.ofNullable(boss);
    }

    public void setBoss(Manager boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Department{" +
                "boss=" + boss +
                '}';
    }
}
