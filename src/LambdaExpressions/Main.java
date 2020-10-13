package LambdaExpressions;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * @author Mohammed Amr
 * @created 13/10/2020 - 22:36
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Assigning a lambda expression to a variable */
        Runnable r = () -> System.out.println("Inside Thread constructor using lambda");
        new Thread(r).start();

        /** Implements FilenameFilter using an anonymous inner class to return only Java source files. */
        File directory = new File("./resources");

//        String[] names = directory.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        });
//
//        System.out.println(Arrays.asList(names));

        // Note: In this case, the accept method returns true if the filename ends with .txt and false otherwise.

        /** Lambda expression implementing FilenameFilter */

        // Note: This time the arguments are contained within parentheses, but do not have types declared.
        // At compile time the compiler knows that the list method takes an argument of type FilenameFilter,
        // and therefore knows the signature of its single abstract method (accept).

//        String[] names = directory.list((dir, name) -> name.endsWith(".txt"));
//        System.out.println(Arrays.asList(names));

        /** A block lambda */

        // Note: This is known as a block lambda. In this case the body still consists of a single line,
        // but the braces now allow for multiple statements. The return keyword is now required.

        String[] names = directory.list((File dir, String name) -> {
            return name.endsWith(".txt");
        });

        System.out.println(Arrays.asList(names));
    }
}
