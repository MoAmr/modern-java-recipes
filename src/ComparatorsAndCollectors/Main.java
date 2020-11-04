package ComparatorsAndCollectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mohammed Amr
 * @created 04/11/2020 - 17:26
 * @project Modern Java Recipes
 */
public class Main {

    private List<String> sampleStrings = Arrays.asList("this", "is", "a", "list", "of", "strings");

    /** Sorting strings lexicographically */
    public List<String> defaultSort() {
        Collections.sort(sampleStrings); // Default sort from Java 7 and below
        return sampleStrings;
    }

    public List<String> defaultSortUsingStreams() {
        return sampleStrings.stream()
                .sorted() // Default sort from Java 8 and above
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {


    }
}
