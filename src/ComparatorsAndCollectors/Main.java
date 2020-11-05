package ComparatorsAndCollectors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Mohammed Amr
 * @created 04/11/2020 - 17:26
 * @project Modern Java Recipes
 */
public class Main {

    private List<String> sampleStrings = Arrays.asList("this", "is", "a", "list", "of", "strings");

    private static List<Golfer> golfers = Arrays.asList(
            new Golfer("Jack", "Nicklaus", 68),
            new Golfer("Tiger", "Woods", 70),
            new Golfer("Tom", "Watson", 70),
            new Golfer("Ty", "Webb", 68),
            new Golfer("Bubba", "Watson", 70)
    );

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

    /** Sorting golfers */
    public static List<Golfer> sortByScoreThenLastThenFirst() {
        return golfers.stream()
                .sorted(Comparator.comparingInt(Golfer::getScore)
                .thenComparing(Golfer::getLast)
                .thenComparing(Golfer::getFirst))
                .collect(toList());
    }

    public static void main(String[] args) {

        // Sorted golfers
        List<Golfer> golferList = sortByScoreThenLastThenFirst();
        golferList.forEach(golfer -> System.out.println(golfer));

        /** Creating a List using Collectors Interface static methods */
        List<String> superHeroes = Stream.of("Mr. Furious", "The Blue Raja", "The Shoveler",
                "The Bowler", "Invisible Boy", "The Spleen", "The Sphinx")
                .collect(Collectors.toList());

        /** Creating a Set using Collectors Interface static methods */
        Set<String> villains = Stream.of("Casanova Frankenstein", "The Disco Boys",
                "The Not-So-Goodie Mob", "The Suits", "The Suzies",
                "The Furriers", "The Furriers") // Duplicate name, removed when converting to a Set
                .collect(Collectors.toSet());
    }
}
