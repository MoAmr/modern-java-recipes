package Streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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

    }

    /**
     * Reference implementation of Stream.of
     */
    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }

}
