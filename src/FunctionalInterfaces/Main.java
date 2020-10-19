package FunctionalInterfaces;

import java.util.Arrays;
import java.util.List;

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
