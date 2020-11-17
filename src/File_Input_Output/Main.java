package File_Input_Output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mohammed Amr
 * @created 15/11/2020 - 23:35
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Finding the 10 longest words in the web2 dictionary using the static lines method  */
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {

            lines.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** Note: By opening the stream in a try-with-resources block, the system will automatically close it, and the
        * dictionary file, when the try block completes. */

        /** Determining number of words of each length by applying Collectors.counting as a downstream collector */
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {

            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.println(len + ": " + num));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nUsing Files.lines\n");

        /** Determining number of words of each length, in descending order */
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n",
                            e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nUsing BufferedReader.lines\n");

        /** Determining number of words of each length, in descending order using BufferedReader.lines */
        // Use BufferReader lines method if your source of data is not a File
        try (Stream<String> lines = new BufferedReader(
                new FileReader("/usr/share/dict/words")).lines()) {

            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n",
                            e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nUsing Files.list(path)\n");

        /** Using Files.list(path) */
        try (Stream<Path> list = Files.list(Paths.get("/My Development/Java Workspace/Modern Java Recipes/src"))) {
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nUsing Files.walk(path)\n");

        /** Walking the Filesystem using Files.walk method */
        try (Stream<Path> paths = Files.walk(Paths.get("/My Development/Java Workspace/Modern Java Recipes/src"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
