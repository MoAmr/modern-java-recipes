package ComparatorsAndCollectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Mohammed Amr
 * @created 04/11/2020 - 17:26
 * @project Modern Java Recipes
 */
public class Main {

    private List<String> sampleStrings = Arrays.asList("this", "is", "a", "list", "of", "strings");

    /** Sorting strings lexicographically - START */
    public List<String> defaultSort() {
        Collections.sort(sampleStrings); // Default sort from Java 7 and below
        return sampleStrings;
    }

    public List<String> defaultSortUsingStreams() {
        return sampleStrings.stream()
                .sorted() // Default sort from Java 8 and above
                .collect(toList());
    }
    /** Sorting strings lexicographically - END */

    /** Sorting streams using length comparator - START */
    public List<String> lengthSortUsingSorted() {
        return sampleStrings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length()) // Using a lambda for the Comparator to sort by length
                .collect(toList());
    }

    public List<String> lengthSortUsingComparator() {
        return sampleStrings.stream()
                .sorted(Comparator.comparingInt(String::length)) // Using a Comparator using the comparingInt method
                .collect(toList());
    }
    /** Sorting streams using length comparator - END */

    /** Sorting Streams by length, then equal lengths lexicographically */
    public List<String> lengthSortThenAlphaSort() {
        return sampleStrings.stream()
                .sorted(Comparator.comparing(String::length) // Sort by length, then equal-length strings alphabetically
                .thenComparing(Comparator.naturalOrder()))
                .collect(toList());
    }

    public static void main(String[] args) {


    }
}
