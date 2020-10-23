package Java.util.function;

import org.junit.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.AssertEquals.assertEquals;

/**
 * @author Mohammed Amr
 * @created 22/10/2020 - 23:57
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Finding a name from a collection */
        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara",
                "Zoë", "Jayne", "Simon", "River", "Shepherd Book");

        Optional<String> first = names.stream()
                .filter(name -> name.startsWith("C"))
                .findFirst();

        // Prints Optional.empty
        System.out.println(first);
        // Prints the string "None"
        System.out.println(first.orElse("None"));

        // Forms the comma-separated collection, even when name is found
        System.out.println(first.orElse(String.format("No result found in %s",
                names.stream().collect(Collectors.joining(",")))));

        // Forms the comma-separated collection only if the Optional is empty
        System.out.println(first.orElseGet(() ->
                String.format("No result found in %s",
                        names.stream().collect(Collectors.joining(", ")))));
    }

}

/** Finding strings that satisfy an arbitrary predicate */
class ImplementPredicate {

    /** Adding constants for common cases */
    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    public static final Predicate<String> STARTS_WITH_S =  s -> s.startsWith("s");

    public String getNamesSatisfyingConditions(Predicate<String> condition, String... names) {
        return Arrays.stream(names)
                // Filter by supplied predicate
                .filter(condition)
                .collect(Collectors.joining(", "));
    }

    /** Finding strings of a given length */
    public String getNamesOfLength(int length, String... names) {
        return Arrays.stream(names)
                // Predicate for strings of given length only
                .filter(str -> str.length() == length)
                .collect(Collectors.joining(", "));
    }

    /** Finding strings that start with a given string */
    public String getNamesStartingWith(String s, String... names) {
        return Arrays.stream(names)
                //Predicate to return strings starting with a given string
                .filter(str2 -> str2.startsWith(s))
                .collect(Collectors.joining(", "));
    }

    // ... other methods ...
}

/**  JUnit test for predicate methods */
class ImplementPredicateTest {

    private ImplementPredicate demo = new ImplementPredicate();

    private String[] names;


    @Before
    public void setUp() {
        names = Stream.of("Mal", "Wash", "Kaylee", "Inara", "Zoë",
                "Jayne", "Simon", "River", "Shepherd Book")
                .sorted()
                .toArray(String[]::new);
    }

    @Test
    public void getNamesOfLength5() throws Exception {
        assertEquals("Inara, Jayne, River, Simon",
                demo.getNamesOfLength(5, names));
    }

}