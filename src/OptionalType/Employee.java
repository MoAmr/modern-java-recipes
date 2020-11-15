package OptionalType;

import java.util.Optional;

/**
 * @author Mohammed Amr
 * @created 15/11/2020 - 22:38
 * @project Modern Java Recipes
 */
public class Employee {

    private String name;
    private Integer salary;
    private Integer id;
    private String department;

    public Employee() {}

    public Employee(String name, Integer salary, Integer id, String department) {
        this.name = name;
        this.salary = salary;
        this.id = id;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Optional<Employee> findEmployeeById(int id) {
        return new Optional<>();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                ", department='" + department + '\'' +
                '}';
    }
}
