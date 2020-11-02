package Streams;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mohammed Amr
 * @created 25/10/2020 - 22:10
 * @project Modern Java Recipes
 */
public class Main {

    private static PalindromeEvaluator palindromeDemo = new PalindromeEvaluator();

    private Primes calculator = new Primes();

    public static void main(String[] args) throws Exception {

        /** Creating a stream using Stream.of */
        String names = Stream.of("Gomez", "Morticia", "Wednesday", "Pugsley")
                .collect(Collectors.joining(","));

        System.out.println(names);
        // prints Gomez,Morticia,Wednesday,Pugsley

        /**  Creating a stream using Arrays.stream */
        String[] munsters = {"Herman", "Lily", "Eddie", "Marilyn", "Grandpa"};
        names = Arrays.stream(munsters)
                .collect(Collectors.joining(","));
        System.out.println(names);
        // prints Herman,Lily,Eddie,Marilyn,Grandpa

        /** Creating a stream using Stream.iterate */
        List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                // Since the resulting stream is unbounded, the intermediate operation limit is needed.
                .limit(10)
                .collect(toList());

        System.out.println(nums);
        // prints [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L))
                // Since the resulting stream is unbounded, the intermediate operation limit is needed.
                .limit(10)
                .forEach(System.out::println);
        // prints 10 days starting from today

        /** Creating a stream of random doubles */
//        long count = Stream.generate(Math::random)
//                .limit(10)
//                .forEach(System.out::println);

        /** Creating a stream from a collection */
        List<String> bradyBunch = Arrays.asList("Greg", "Marcia", "Peter", "Jan",
                "Bobby", "Cindy");

        names = bradyBunch.stream()
                .collect(Collectors.joining(","));

        System.out.println(names);
        // prints Greg,Marcia,Peter,Jan,Bobby,Cindy

        /** The range and rangeClosed methods */
        List<Integer> ints = IntStream.range(10, 15)
                // Necessary for Collectors to convert primitives to List<T>
                .boxed()
                .collect(toList());

        System.out.println(ints);
        // prints [10, 11, 12, 13, 14]

        List<Long> longs = LongStream.rangeClosed(10, 15)
                // Necessary for Collectors to convert primitives to List<T>
                .boxed()
                .collect(toList());

        System.out.println(longs);
        // prints [10, 11, 12, 13, 14, 15]

        /** Note: rangeClosed includes the end value, and range doesn’t. */

        /** Using the boxed method on Stream to convert the IntStream to a Stream<Integer> */
        List<Integer> ints1 = IntStream.of(3, 1, 4, 1, 5, 9)
                // Converts int to Integer
                .boxed()
                .collect(toList());

        /** One alternative is to use the mapToObj method to convert each element from a primitive
         *  to an instance of the wrapper class, */
        List<Integer> ints2 = IntStream.of(3, 1, 4, 1, 5, 9)
                // The argument to mapToObj in this example uses the Integer constructor.
                .mapToObj(Integer::valueOf)
                .collect(toList());

        /** Using the three-argument version of collect */
        List<Integer> ints3 = IntStream.of(3, 1, 4, 1, 5, 9)
                .collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);
        System.out.println(ints3);

        // In this version of collect, the Supplier is the constructor for ArrayList<Integer>,
        // the accumulator is the add method, which represents how to add a single element to a list,
        // and the combiner (which is only used during parallel operations) is addAll, which combines two lists into one.

        /** Convert an IntStream to an int array */
        int[] intArray = IntStream.of(3, 1, 4, 1, 5, 9).toArray();

        /** Reduction operations on IntStream */
        String[] strings = "this is an array of strings".split(" ");
        long count = Arrays.stream(strings)
                // count is a Stream method, so no need to map to IntStream
                .map(String::length)
                .count();
        System.out.println("There are " + count + " strings");

        int totalLength = Arrays.stream(strings)
                .mapToInt(String::length)
                // sum is on the primitive streams only
                .sum();
        System.out.println("The total length is " + totalLength);

        OptionalDouble ave = Arrays.stream(strings)
                .mapToInt(String::length)
                // average is on the primitive streams only
                .average();
        System.out.println("The average length is " + ave);

        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                // max without Comparator only on primitive streams
                .max();

        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                // min without Comparator only on primitive streams
                .min();

        System.out.println("The max and min lengths are " + max + " and " + min);

        /** Summing numbers using reduce */
        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y).orElse(0);
        System.out.println("The value of sum = " + sum);
        // The value of sum is 55

        /**  Printing the values of x and y */
        int sum1 = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> {
                    System.out.printf("x=%d, y=%d%n", x, y);
                    return x + y;
                }).orElse(0);

        /** Doubling the values during the sum (WORKS) */
        int doubleSum = IntStream.rangeClosed(1, 10)
                // The word identity means that you should supply a value
                // to the binary operator that, when combined with any other value,
                // returns the other value. For addition, the identity is zero.
                // For multiplication, the identity is 1. For string concatenation,
                // the identity is the empty string.
                .reduce(0, (x, y) -> x + 2 * y);
        System.out.println("The value of doubleSum = "  + doubleSum);
        // The value of doubleSum is 110, as it should be

        /** Performing a reduce with a binary operator */
        int sum2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(0, Integer::sum);
        System.out.println(sum2);

        /** Finding the max using reduce */
        Integer max1 = Stream.of(3, 1, 4, 1, 5, 9)
                // The identity for max is the minimum integer
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("The max value is " + max);

        /** Concatenating strings from a stream using reduce */
        String s = Stream.of("this", "is", "a", "list")
                .reduce("", String::concat);
        System.out.println(s);
        // Prints thisisalist

        /** Collecting strings using a StringBuilder */
        String s1 = Stream.of("this", "is", "a", "list")
                .collect(() -> new StringBuilder(), // Result Supplier
                        (sb, str) -> sb.append(str), // Add a single value to the result
                        (sb1, sb2) -> sb1.append(sb2)) // Combine two results
                .toString();
        System.out.println(s1);

        /** Collecting strings, with method references */
        String s2 = Stream.of("this", "is", "a", "list")
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
        System.out.println(s2);

        /** Joining strings using Collectors */
        String s3 = Stream.of("this", "is", "a", "list")
                .collect(Collectors.joining()); // Simplest of all
        System.out.println(s3);

        /** The most general form of reduce
         * <U> U reduce(U identity,
         * BiFunction<U,? super T,U> accumulator,
         * BinaryOperator<U> combiner)
         */

        /** Accumulating Books into a Map */
        Book[] booksArr = {new Book(1, "The Subtle Art of Not Giving a Fuck"),
                new Book(2, "After We Collided"),
                new Book(3, "The Archimedes"),
                new Book(4, "Modern Java Recipes"),
                new Book(5, "Making Java Groovy"),
                new Book(6, "Gradle Recipes for Android")};

        List<Book> books = Arrays.asList(booksArr);

        HashMap<Integer, Book> bookMap = books.stream()
                .reduce(new HashMap<Integer, Book>(), // Identity value for putAll is Empty Map
                (map, book) -> {
                    map.put(book.getId(), book); // Accumulate a single book into Map using put
                    return map;
                },
                (map1, map2) -> {
                    map1.putAll(map2); // Combine multiple Maps using putAll
                    return map1;
                });

        bookMap.forEach((k, v) -> System.out.println(k + ": " + v));

        /** Summing BigDecimals with reduce */
        // This is the most typical way of using the reduce method,
        BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val)); // Using the add method in BigDecimal as a BinaryOperator
        System.out.println("The total is " + total);

        /** Sorting strings by length */
        List<String> strings1 = Arrays.asList("this", "is", "a", "list", "of", "strings");

        List<String> sorted = strings1.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(toList());
        System.out.println(sorted);

        /** Testing that strings are sorted properly */
        // The only thing required to make this work is for the stream to be sequential and ordered
        sorted.stream()
                .reduce((prev, curr) -> {
                    assertTrue(prev.length() <= curr.length()); // Check each pair is sorted properly
                    return curr; // curr becomes the next value of prev
                });

        /** Invoking testing doubling integers, filtering, and summing */
        //testingSumDoublesDivisibleBy3(100, 120);

        /** Invoking testing doubling integers, filtering, and summing and adding an identity map for printing */
        //testingSumDoublesDivisibleByNumber3(100, 120);

        /** Invoking using multiple peek method */
        int sumDoublesDivisibleBy3Val = sumDoublesDivisibleBy3UsingPeek(100, 120);
        System.out.println(sumDoublesDivisibleBy3Val);

        /** Invoking isPalindromeJava7OrEarlier method */
        System.out.println("Is the word Anna Palindrome? " + palindromeDemo.isPalindromeJava7OrEarlier("Anna"));
        System.out.println("Is the word Abc Palindrome? " + palindromeDemo.isPalindromeJava7OrEarlier("Abc"));

        /** Invoking isPalindrome using Java8 method */
        System.out.println("Is the word Anna Palindrome? " + palindromeDemo.isPalindrome("Anna"));
        System.out.println("Is the word Abc Palindrome? " + palindromeDemo.isPalindrome("Abc"));

        /** Counting elements in a stream */
        long count1 = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).count();
        System.out.printf("There are %d elements in the stream%n", count1);
        // Prints There are 9 elements in the stream

        /** Counting the elements using Collectors.counting */
        long count2 = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .collect(Collectors.counting());
        System.out.printf("There are %d elements in the stream%n", count2);

        /** Counting string partitioned by length */
        Map<Boolean, Long> numberLengthMap = strings1.stream()
                .collect(Collectors.partitioningBy(
                        n -> n.length() % 2 == 0, // Predicate
                        Collectors.counting())); // Downstream collector

        numberLengthMap.forEach((k, v) -> System.out.printf("%5s: %d%n", k, v));

        /** Using SummaryStatistics method in IntStream, DoubleStream, and LongStream
         * to get count, sum, min, max, and average of a stream of numerical values */
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();
        System.out.println(stats); // Print using the toString method
        System.out.println("count: " + stats.getCount());
        System.out.println("min: " + stats.getMin());
        System.out.println("max: " + stats.getMax());
        System.out.println("sum: " + stats.getSum());
        System.out.println("ave: " + stats.getAverage());

        /** Collect with a Supplier, accumulator, and combiner */

        Team[] teamsArr = { new Team(1, "IT", 500000d),
                new Team(2, "HR", 300000d),
                new Team(3, "Marketing", 700000d)};

        List<Team> teams = Arrays.asList(teamsArr);

        DoubleSummaryStatistics teamStats = teams.stream()
                .mapToDouble(Team::getSalary)
                .collect(DoubleSummaryStatistics::new,
                        DoubleSummaryStatistics::accept,
                        DoubleSummaryStatistics::combine);

        System.out.println(teamStats);
        System.out.println(teamStats.getCount() + " teams");
        System.out.println("min = $" + teamStats.getMin());
        System.out.println("max = $" + teamStats.getMax());
        System.out.println("sum = $" + teamStats.getSum());
        System.out.println("ave = $" + teamStats.getAverage());

        /** Computing the summary statistics of a Collection using Collect summarizingDouble */
       DoubleSummaryStatistics teamStats1 = teams.stream()
               .collect(Collectors.summarizingDouble(Team::getSalary));

       System.out.println(teamStats1);
        System.out.println(teamStats1.getCount() + " teams");
        System.out.println("min = $" + teamStats1.getMin());
        System.out.println("max = $" + teamStats1.getMax());
        System.out.println("sum = $" + teamStats1.getSum());
        System.out.println("ave = $" + teamStats1.getAverage());

        /** Finding the first even integer in a stream using findFirst method */
        Optional<Integer> firstEven = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEven); // Prints Optional[4]

        // Note: If the stream is empty, the return value is an empty Optional.

        /** Using findFirst on an empty stream */
        Optional<Integer> firstEvenGT10 = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(n -> n > 10)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEvenGT10); // Prints Optional.empty

        /** Using firstEven in parallel */
        Optional<Integer> firstEvenParallel = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .parallel()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEvenParallel); // Always prints Optional[4]

        /** Using findAny in parallel after a random delay */
        Optional<Integer> any = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered() // We don’t care about order
                .parallel() // Use the common fork-join pool in parallel
                .map(Main::delay) // Introduce a random delay
                .findAny(); // Return the first element, regardless of encounter order
        System.out.println("Any: " + any);
        // The output now could be any of the given numbers, depending on which thread gets there first.

        /** Using findAny on sequential and parallel streams */
        Optional<Integer> any1 = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .map(Main::delay)
                .findAny(); // Sequential stream (by default)
        System.out.println("Sequential Any: " + any1);

        Optional<Integer> anyParallel = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .parallel()
                .map(Main::delay)
                .findAny(); // Parallel stream
        System.out.println("Parallel any: " + anyParallel);



    }

    /**
     * Reference implementation of Stream.of
     */
    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }

    /** Doubling integers, filtering, and summing - method implementation */
    public static int sumDoublesDivisibleBy3(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();
    }

    /** Doubling integers, filtering, and summing and adding an identity map for printing - method implementation */
    public static int sumDoublesDivisibleByNumber3(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> { // Identity map that prints each element before returning it
                    System.out.println(n);
                    return n;
                })
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();
    }

    /** Using multiple peek methods */
    public static int sumDoublesDivisibleBy3UsingPeek(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .peek(n -> System.out.printf("original: %d%n", n)) // Print value before doubling
                .map(n -> n * 2)
                .peek(n -> System.out.printf("doubled: %d%n", n)) // Print value after doubling but before filtering
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.printf("filtered: %d%n", n)) // Print value after filtering but before summing
                .sum();
    }

    /** Using findAny in parallel after a random delay method */
    public static Integer delay(Integer n) {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch(InterruptedException ignored) {} // The only exception in Java that it is OK to catch and ignore.
        return n;
    }

    /** Testing sum doubles divisible by 3 */
    @Test
    public void testingSumDoublesDivisibleBy3() throws Exception {
        assertEquals(1554, sumDoublesDivisibleBy3(100, 120));
    }

    /** Testing sum doubles divisible by 3 while adding an identity map for printing */
    @Test
    public void testingSumDoublesDivisibleByNumber3() throws Exception {
        assertEquals(1554, sumDoublesDivisibleByNumber3(100, 120));
    }

    /** Testing the palindrome checker */
    @Test
    public void testIsPalindrome() throws Exception {
        assertTrue(
                Stream.of("Madam, in Eden, I'm Adam",
                        "Go hang a salami; I'm a lasagna hog",
                        "Flee to me, remote elf!",
                        "A Santa pets rats as Pat taps a star step at NASA")
                .allMatch(palindromeDemo::isPalindrome));

        assertFalse(palindromeDemo.isPalindrome("This is NOT a palindrome"));
    }

    /** Tests for the prime calculation */
    @Test
    // Use allMatch for simplicity
    public void testIsPrimeUsingAllMatch() throws Exception {
        assertTrue(IntStream.of(2, 3, 5, 7, 11, 13, 17, 19)
        .allMatch(calculator::isPrime));
    }

    @Test
    // Test with composites
    public void testIsPrimeWithComposites() throws Exception {
        assertFalse(Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20)
        .anyMatch(calculator::isPrime));
    }

    /** Testing empty streams */
    @Test
    public void emptyStreamsDanger() throws Exception {
        assertTrue(Stream.empty().allMatch(e -> false));
        assertTrue(Stream.empty().noneMatch(e -> true));
        assertFalse(Stream.empty().anyMatch(e -> true));
    }

}
