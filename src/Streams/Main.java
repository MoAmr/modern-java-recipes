package Streams;

import java.util.Arrays;
import java.util.stream.Collectors;
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
    }

    /** Reference implementation of Stream.of */
    @SafeVarargs
    public static<T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }

}
