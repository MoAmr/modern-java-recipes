package MethodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mohammed Amr
 * @created 14/10/2020 - 07:31
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Using a method reference to access println */

        // Using a lambda expression
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(x -> System.out.println(x));

        // Using a method reference
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(System.out::println);

        // Assigning the method reference to a functional interface
        Consumer<Integer> printer = System.out::println;
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(printer);

        /** Using a method reference to a static method */
        // Static Method

        // The generate method on Stream takes a Supplier as an argument, which is a functional interface
        // whose single abstract method takes no arguments and produces a sin‐ gle result.

        // The random method in the Math class is compatible with that signature, because it also takes no arguments
        // and produces a single, uniformly distributed, pseudorandom double between 0 and 1.
        Stream.generate(Math::random)
                .limit(10)
                // Instance Method
                .forEach(System.out::println);

        /** Note: Stream produces a series of elements sequentially, does not store them any‐ where,
         *  and does not modify the original source. */

        // Invoking a multiple-argument instance method from a class reference
        List<String> strings =
                Arrays.asList("this", "is", "a", "list", "of", "strings");
        List<String> sorted = strings
                .stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());

        List<String> sorted1 = strings
                .stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        //  Invoking the length method on String using a method reference
        Stream.of("this", "is", "a", "stream", "of", "strings")
                // Instance method via class name
                .map(String::length)
                // Instance method via object reference
                .forEach(System.out::println);

    }
}
