package File_Input_Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
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
        
    }
}
