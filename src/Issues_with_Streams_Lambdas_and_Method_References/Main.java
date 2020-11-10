package Issues_with_Streams_Lambdas_and_Method_References;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mohammed Amr
 * @created 09/11/2020 - 07:39
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Returning a collection and filtering out nulls */
        List<String> strings = Arrays.asList(
                "this", null, "is", "a", null, "list", "of", "strings", null);

        List<String> nonNullStrings = strings.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()); // Filter out null elements
        System.out.println(nonNullStrings);

        /** Generating streams of random numbers */
        Random r = new Random();
        r.ints(5) // Five random integers
                .sorted()
                .forEach(System.out::println);

        // Five random doubles between 0 (inclusive) and 0.5 (exclusive)
        r.doubles(5, 0, 0.5)
                .sorted()
                .forEach(System.out::println);

        List<Long> longs = r.longs(5)
                .boxed() // Boxing long to Long so they can be collected
                .collect(Collectors.toList());

        List<Integer> litOfInts = r.ints(5, 10, 20)
                // Alternative form of collect instead of calling boxed
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println(litOfInts);

    }

    /** Using the Objects.deepEquals method for testing the filter */
    @Test
    public void testNonNulls() throws Exception {
        List<String> strings =
                Arrays.asList("this", "is", "a", "list", "of", "strings");

        List<String> nonNullStrings = strings.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        assertTrue(Objects.deepEquals(strings, nonNullStrings));
    }

    /** Filtering nulls from a generic list */
    public <T> List<T> getNonNullElements(List<T> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
