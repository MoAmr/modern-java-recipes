package OptionalType;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mohammed Amr
 * @created 14/11/2020 - 01:03
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Are Optionals immutable? */
        AtomicInteger counter = new AtomicInteger();
        Optional<AtomicInteger> optional = Optional.ofNullable(counter);

        System.out.println(optional); // Optional[0]

        counter.incrementAndGet(); // Increment using counter directly
        System.out.println(optional); // Optional[1]

        optional.get().incrementAndGet(); // Retrieve contained value and increment
        System.out.println(optional); // Optional[2]

        optional = Optional.ofNullable(new AtomicInteger()); // Optional reference can be reassigned

        /** Retrieving the first even-length string from an optional */
        Optional<String> firstEven = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 == 0)
                .findFirst();
        System.out.println(firstEven.get());

        /** Retrieving the first even-length string with a protected get using isPresent method of Optional */
        Optional<String> firstEven1 = Stream.of("five", "even", "length", "string", "values") // Same as before
                .filter(s -> s.length()  % 2 == 0)
                .findFirst();
        // Only call get if isPresent returns true
        System.out.println(firstEven1.isPresent() ? firstEven1.get() : "No even length strings!");

        /** Retrieving the first odd-length string with a protected get using orElse method of Optional */
        Optional<String> firstOdd = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 != 0)
                .findFirst();
        System.out.println(firstOdd.orElse("No odd length strings!"));

        /** Using orElseThrow of Optional as a Supplier */
        Optional<String> first = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 == 0)
                .findFirst();
        System.out.println(first.orElseThrow(NoSuchElementException::new));

        /** Using the ifPresent method of Optional */
        Optional<String> firstEven2 = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 == 0)
                .findFirst();

        firstEven2.ifPresent(val -> System.out.println("Found an even-length string!"));

        Optional<String> firstOdd1 = Stream.of("five", "even", "cat", "length", "string", "values")
                .filter(s -> s.length() % 2 != 0)
                .findFirst();

        firstOdd1.ifPresent(val -> System.out.println("Found an odd-length string!"));

        /**  Returning an Optional */
        Manager mrSlate = new Manager("Mr. State");

        Department d = new Department();
        d.setBoss(mrSlate); // Department with a nonnull manager
        System.out.println("Boss: " + d.getBoss()); // Prints Boss: Optional[Manager{name='Mr. Slate'}]

        Department d1 = new Department(); // Department without a manager
        System.out.println("Boss: " + d1.getBoss()); // Prints Boss: Optional.empty

        /** Extracting a value from an Optional */
        // Extract boss from Optional before calling getName
        System.out.println("Name: " + d.getBoss().orElse(new Manager("Unknown")).getName());

        System.out.println("Name: " + d1.getBoss().orElse(new Manager("Unknown")).getName());

        // Use Optional.map to apply getName to contained Manager
        System.out.println("Name: " + d.getBoss().map(Manager::getName));
        System.out.println("Name: " + d1.getBoss().map(Manager::getName));

        /** An Optional wrapped inside an Optional */
        Company co = new Company();
        co.setDepartment(d);

        // Prints Company Dept: Optional[Department{boss=Manager{name='Mr.Slate'}}]
        System.out.println("Company Dept: " + co.getDepartment());

        // Prints Company Dept Manager: Optional[Optional[Manager{name='Mr. Slate'}]]
        System.out.println("Company Dept Manager: " + co.getDepartment()
        .map(Department::getBoss));

        /** Using flatMap on a company */
        System.out.println(
                co.getDepartment() // Optional<Department>
                        .flatMap(Department::getBoss) // Optional<Manager>
                        .map(Manager::getName)); // Optional<String>

        /** Using flatMap on an optional company */
        Optional<Company> company = Optional.of(co);

        System.out.println(
                // Optional<Company>
                company
                        // Optional<Department>
                        .flatMap(Company::getDepartment)
                        // Optional<Manager>
                        .flatMap(Department::getBoss)
                        // Optional<String>
                        .map(Manager::getName)
        );



    }

    /** Creating an Optional with “of ” */
    public static <T> Optional<T> createOptionalTheHardWay(T value) {
        return value == null ? Optional.empty() : Optional.of(value);
    }

    /** Creating an Optional with “ofNullable” */
    public static <T> Optional<T> createOptionalTheEasyWay(T value) {
        return Optional.ofNullable(value);
    }

    /** Finding Employees by ID */
//    public List<Employee> findEmployeesByIds(List<Integer> ids) {
//        return ids.stream()
//                // Stream<Optional<Employee>>
//                .map(Employee::findEmployeeById)
//                // Remove empty Optionals
//                .filter(Optional::isPresent)
//                // Retrieve values you know exist
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }

    /** Using Optional.map */
//    public List<Employee> findEmployeesByIdsUsingFlatMap(List<Integer> ids) {
//        return ids.stream()
//                // Stream<Optional<Employee>>
//                .map(Employee::findEmployeeById)
//                .flatMap(optional ->
//                        // Turns nonempty Optional<Employee> into Optional<Stream<Employee>>
//                        optional.map(Stream::of)
//                                // Extracts the Stream<Employee> from the Optional
//                                .orElseGet(Stream::empty))
//                .collect(Collectors.toList());
//    }
}
