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

    }

    /**
     * Reference implementation of Stream.of
     */
    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }

    // A simple Book class
    static class Book {

        private Integer id;
        private String title;

        public Book(Integer id, String title) {
            this.id = id;
            this.title = title;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return Objects.equals(id, book.id) &&
                    Objects.equals(title, book.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title);
        }
    }

}
