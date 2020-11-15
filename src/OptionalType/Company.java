package OptionalType;

import java.util.Optional;

/**
 * @author Mohammed Amr
 * @created 15/11/2020 - 22:05
 * @project Modern Java Recipes
 */
public class Company {

    private Department department;

    public Optional<Department> getDepartment() {
        return Optional.ofNullable(department);
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Company{" +
                "department=" + department +
                '}';
    }
}
