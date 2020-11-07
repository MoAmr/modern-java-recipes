package ComparatorsAndCollectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Mohammed Amr
 * @created 04/11/2020 - 17:26
 * @project Modern Java Recipes
 */
public class Main {

    private static final String dictionary = "/usr/share/dict/words";

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

        /** Creating a LinkedList using Collectors Interface static methods */
        List<String> actors = Stream.of("Hank Azaria", "Janeane Garofalo", "William H. Macy",
                "Paul Reubens", "Ben Stiller", "Kel Mitchell", "Wes Studi")
                .collect(Collectors.toCollection(LinkedList::new));

        /** Creating an Array using Collectors Interface static methods */
        String[] wannabes = Stream.of("The Waffler", "Reverse Psychologist", "PMS Avenger")
                .toArray(String[]::new); // Array constructor reference as a Supplier

        /** Creating a Map using Collectors Interface static methods */
        Set<Actor> actorSet = new HashSet<>();
        actorSet.add(new Actor("Janeane Garofalo", "The Bowler"));
        actorSet.add(new Actor("Greg Kinnear", "Captain Amazing"));
        actorSet.add(new Actor("William H. Macy", "The Shoveler"));
        actorSet.add(new Actor("Paul Reubens", "The Spleen"));
        actorSet.add(new Actor("Ben Stiller", "Mr. Furious"));

        Map<String, String> actorMap = actorSet.stream()
                .collect(Collectors.toMap(Actor::getName, Actor::getRole)); // Functions to produce keys and values

        actorMap.forEach((k, v) -> System.out.printf("%s played %s%n", k, v));

        /** A collection of books */
        List<Book> books = Arrays.asList(
                new Book(1, "Modern Java Recipes", 49.99),
                new Book(2, "Java 8 in Action", 49.99),
                new Book(3, "Java SE8 for the Really Impatient", 39.99),
                new Book(4, "Functional Programming in Java", 27.64),
                new Book(5, "Making Java Groovy", 45.99),
                new Book(6, "Gradle Recipes for Android", 23.76)
        );

        /** Adding a collection of objects to a Map using toMap of Collectors */
        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b)); // Identity lambda: given an element, return it

        bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, Function.identity())); // Static identity method in Function does the same thing

        /** Reading the dictionary file into a Map */
        System.out.println("\nNumber of words of each length:");
        try (Stream<String> lines = Files.lines(Paths.get(dictionary))) { // try-with-resources block
            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(
                            String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.printf("%d: %d%n", len, num));

        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
