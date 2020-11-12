package Issues_with_Streams_Lambdas_and_Method_References;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mohammed Amr
 * @created 09/11/2020 - 07:39
 * @project Modern Java Recipes
 */
public class Main {

    private Map<Long, BigInteger> cache = new HashMap<>();
    private static Logger logger = Logger.getLogger(String.valueOf(Main.class));

    private static List<String> data = new ArrayList<>();

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

        /** Calling the countWords method */

        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' Agent says, 'heard it'.";

        Map<String, Integer> counts = countWords(passage, "NSA", "agent", "joke");
        counts.forEach((word, count) -> System.out.println(word + "=" + count));

        /** Iterating over a linear collection */
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);

        integers.forEach(new Consumer<Integer>() { // Anonymous inner class implementation
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        integers.forEach((Integer n) -> { // Full verbose form of a block lambda
            System.out.println(n);
        });

        integers.forEach(n -> System.out.println(n)); // Expression lambda

        integers.forEach(System.out::println); // Method reference

        /** Iterating over a Map */
        Map<Long, String> map = new HashMap<>();
        map.put(86L, "Don Adams (Maxwell Smart)");
        map.put(99L, "Barbara Feldon");
        map.put(13L, "David Ketchum");

        map.forEach((num, agent) ->
                System.out.printf("Agent %d, played by %s%n", num, agent));

        /** Invoking logDataList to show log.info msgs with supplier */
        logDataList();

        /** Using the compose and andThen default methods in the Function Interface. */
        Function<Integer, Integer> add2 = x -> x + 2;
        Function<Integer, Integer> mult3 = x -> x * 3;

        Function<Integer, Integer> mult3add2 = add2.compose(mult3); // First mult3, then add2
        Function<Integer, Integer> add2mult3 = add2.andThen(mult3); // First add2, then mult3

        System.out.println("mult3add2(1): " + mult3add2.apply(1)); // because (1 * 3) + 2 == 5
        System.out.println("add2mult3(1): " + add2mult3.apply(1)); // because (1 + 2) * 3 == 9

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

    /**  Fibonacci calculation with a cache using computeIfAbsent default method of Map Interface */
    public BigInteger fib(long i) {
        if (i == 0) return BigInteger.ZERO;
        if (i == 1) return BigInteger.ONE;

        // Cache returns value if it exists, or computes and stores it if not
        return cache.computeIfAbsent(i, n -> fib(n - 2).add(fib(n - 1)));
    }

    /** Update the word counts only for specific words using computeIfAbsent default method of Map Interface */
    public static Map<String, Integer> countWords(String passage, String... strings) {
        Map<String, Integer> wordCounts = new HashMap<>();

        // Put the words we care about in the map with a count of zero
        Arrays.stream(strings).forEach(s -> wordCounts.put(s, 0));

        // Read the passage, updating the counts only for the words we care about
        Arrays.stream(passage.split(" ")).forEach(word ->
                wordCounts.computeIfPresent(word, (k, v) -> v + 1));

        return wordCounts;
    }

    /** Using the merge default method of Map Interface */
    public Map<String, Integer> fullWordCounts(String passage) {
        Map<String, Integer> wordCounts = new HashMap<>();
        // Remove case sensitivity and punctuation
        String testString = passage.toLowerCase().replaceAll("\\W", " ");
        Arrays.stream(testString.split("\\s+")).forEach(word ->
                // Add or update the count for a given word
                wordCounts.merge(word, 1, Integer::sum));

        return wordCounts;
    }

    /** Using a Supplier in the info method */
    public static void logDataList() {

        data.add("Modern Java Recipes");
        data.add("Making Java Groovy");
        data.add("Gradle Recipes for Android");

        logger.info("The data is " + data.toString()); // Argument always constructed
        logger.info(() -> "The data is " + data.toString()); // Argument only constructed if log level shows info messages

    }

    /** The Company interface with a default method */
    public interface Company {
        default String getName() {
            return "Initech";
        }

        // other methods
    }

    /** The Employee interface with a default method */
    public interface Employee {
        String getFirst();

        String getLast();

        void convertCaffeineToCodeForMoney();

        default String getName() {
            return String.format("%s %s", getFirst(), getLast());
        }
    }

    /** Fixed version of CompanyEmployee */
    class CompanyEmployee implements Company, Employee {

        private String first;
        private String last;

        @Override
        public String getFirst() {
            return first;
        }

        @Override
        public String getLast() {
            return last;
        }

        @Override
        public void convertCaffeineToCodeForMoney() {
            System.out.println("Coding...");
        }

        @Override
        public String getName() { // Implement getName
            return String.format("%s working for %s",
                    Employee.super.getName(), Company.super.getName()); // Access default implementations using super
        }
    }
}
