package Streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Mohammed Amr
 * @created 25/10/2020 - 22:10
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

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
                .collect(Collectors.toList());

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
                .collect(Collectors.toList());

        System.out.println(ints);
        // prints [10, 11, 12, 13, 14]

        List<Long> longs = LongStream.rangeClosed(10, 15)
                // Necessary for Collectors to convert primitives to List<T>
                .boxed()
                .collect(Collectors.toList());

        System.out.println(longs);
        // prints [10, 11, 12, 13, 14, 15]

        /** Note: rangeClosed includes the end value, and range doesn’t. */

        /** Using the boxed method on Stream to convert the IntStream to a Stream<Integer> */
        List<Integer> ints1 = IntStream.of(3, 1, 4, 1, 5, 9)
                // Converts int to Integer
                .boxed()
                .collect(Collectors.toList());

        /** One alternative is to use the mapToObj method to convert each element from a primitive
         *  to an instance of the wrapper class, */
        List<Integer> ints2 = IntStream.of(3, 1, 4, 1, 5, 9)
                // The argument to mapToObj in this example uses the Integer constructor.
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

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

    }

    /**
     * Reference implementation of Stream.of
     */
    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }

}
