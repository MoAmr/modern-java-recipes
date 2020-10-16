package ConstructorReferences;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mohammed Amr
 * @created 16/10/2020 - 23:02
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Constructor References */

        /** Transforming strings into Person instances */
        List<String> names =
                Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace",
                        "Karen Spärck Jones");

        List<Person> people = names.
                stream()
                // Using a lambda expression to invoke the constructor
                .map(name -> new Person(name))
                .collect(Collectors.toList());

        /** ore, alternatively */
        List<Person> people1 = names.stream()
                // Using a constructor reference instantiating Person
                .map(Person::new)
                .collect(Collectors.toList());

        /** Converting a list to a stream and back */
        Person before = new Person("Grace Hopper");

        List<Person> people2 = Stream.of(before)
                .collect(Collectors.toList());

        Person after = people2.get(0);

        assertTrue(before == after);

        before.setName("Grace Murray Hopper");
        assertEquals("Grace Murray Hopper", after.getName());

        /** Using the copy constructor */
        people2 = Stream.of(before)
                // Use copy constructor
                .map(Person::new)
                .collect(Collectors.toList());

        after = people2.get(0);
        // Different objects
        assertFalse(before == after);
        // But equivalent
        assertEquals(before, after);

        before.setName("Rear Admiral Dr. Grace Murray Hopper");
        assertFalse(before.equals(after));

        /** Create a stream of strings */
        names.stream()
                // Map to a stream of string arrays
                .map(name -> name.split(" "))
                // Map to a stream of Person
                .map(Person::new)
                // Collect to a list of Person
                .collect(Collectors.toList());

        System.out.println("Varargs ctor, names=" + Arrays.asList(names));

        /** Constructor references can also be used with arrays. If you want an array of Person instances,
         * Person[], instead of a list, you can use the toArray method on Stream. */
        /** Creating an array of Person references */
        Person[] people3 = names.stream()
                // Constructor reference for Person
                .map(Person::new)
                // Constructor reference for an array of Person
                .toArray(Person[]::new);
    }
}
