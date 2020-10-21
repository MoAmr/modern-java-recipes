package JavaUtilFunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.logging.Logger;


/**
 * @author Mohammed Amr
 * @created 20/10/2020 - 07:23
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Printing the elements of a collection */
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        // Anonymous inner class implementation
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // Expression lambda
        strings.forEach(s -> System.out.println(s));
        // Method reference
        strings.forEach(System.out::println);

        /** Using Math.random() as a Supplier */
        Logger logger = Logger.getLogger("...");

        // Anonymous inner class implementation
        DoubleSupplier randomSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return Math.random();
            }
        };

        // Expression lambda
        randomSupplier = () -> Math.random();
        // Method Reference
        randomSupplier = Math::random;

        logger.info((Supplier<String>) randomSupplier);
    }
}
