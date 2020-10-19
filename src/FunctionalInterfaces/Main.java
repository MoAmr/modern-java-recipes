package FunctionalInterfaces;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mohammed Amr
 * @created 19/10/2020 - 07:07
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Using Default Methods */
        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9);
        // Use the default method removeIf from Collection
        boolean removed = nums.removeIf(n -> n <= 0);
        System.out.println("Elements were " + (removed ? "" : "NOT") + " removed");
        // Use the default method forEach from Iterator
        nums.forEach(System.out::println);

        /** One example of a convenient static method in an interface is the comparing method in java.util.Comparator,
         *  along with its primitive variants, comparingInt, comparingLong, and comparingDouble. The Comparator
         *  interface also has static methods naturalOrder and reverseOrder. */

        /** Sorting strings */
        List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore",
                "Dalton", "Brosnan", "Craig");

        List<String> sorted = bonds.stream()
                // Natural order (lexicographical)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        // [Craig, Moore, Dalton, Brosnan, Connery, Lazenby]

        sorted = bonds.stream()
                // Reverse lexicographical
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        // [Moore, Lazenby, Dalton, Craig, Connery, Brosnan]

        sorted = bonds.stream()
                // Sort by lowercase name
                .sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]

        sorted = bonds.stream()
                // Sort by name length
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        // [Moore, Craig, Dalton, Connery, Lazenby, Brosnan]

        sorted = bonds.stream()
        // Sort by length, then equal lengths lexicographically
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        // [Craig, Moore, Dalton, Brosnan, Connery, Lazenby]
    }

    /** MyInterface is a functional interface with static and default methods */
    @FunctionalInterface
    public interface MyInterface {

        // Single abstract method
        int myMethod();
        // If added, this would no longer be a functional interface
        // int myOtherMethod();

        default String sayHello() {
            return "Hello, World!";
        }

        static void myStaticMethod() {
            System.out.println("I'm a static method in an interface");
        }
    }

    /** Extending a functional interface—no longer functional */
    public interface MyChildInterface extends MyInterface {

        /** The MyChildInterface is not a functional interface,
         * because it has two abstract meth‐ ods: myMethod,
         * which it inherits from MyInterface; and anotherMethod, which it declares.*/

        /** Without the @FunctionalInterface annotation,
         *  this compiles, because it’s a standard interface.
         *  It cannot, however, be the target of a lambda expression. */

        // Additional abstract method
        int anotherMethod();
    }

    /** An Employee interface with a default method */
    public interface Employee {

        String getFirst();

        String getLast();

        void convertCaffeineToCodeForMoney();

        // Default method with an implementation

        default String getName () {
            return String.format("%s %s", getFirst(), getLast());
        }
    }
}
