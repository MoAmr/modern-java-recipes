package OptionalType;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author Mohammed Amr
 * @created 14/11/2020 - 01:03
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Are Optionals immutable? */
        AtomicInteger counter = new AtomicInteger();
        Optional<AtomicInteger> optional = Optional.ofNullable(counter);

        System.out.println(optional); // Optional[0]

        counter.incrementAndGet(); // Increment using counter directly
        System.out.println(optional); // Optional[1]

        optional.get().incrementAndGet(); // Retrieve contained value and increment
        System.out.println(optional); // Optional[2]

        optional = Optional.ofNullable(new AtomicInteger()); // Optional reference can be reassigned

        /** Retrieving the first even-length string from an optional */
        Optional<String> firstEven = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 == 0)
                .findFirst();
        System.out.println(firstEven.get());

        /** Retrieving the first even-length string with a protected get using isPresent method of Optional */
        Optional<String> firstEven1 = Stream.of("five", "even", "length", "string", "values") // Same as before
                .filter(s -> s.length()  % 2 == 0)
                .findFirst();
        // Only call get if isPresent returns true
        System.out.println(firstEven1.isPresent() ? firstEven1.get() : "No even length strings!");

        /** Retrieving the first odd-length string with a protected get using orElse method of Optional */
        Optional<String> firstOdd = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 != 0)
                .findFirst();
        System.out.println(firstOdd.orElse("No odd length strings!"));

    }

    /** Creating an Optional with “of ” */
    public static <T> Optional<T> createOptionalTheHardWay(T value) {
        return value == null ? Optional.empty() : Optional.of(value);
    }

    /** Creating an Optional with “ofNullable” */
    public static <T> Optional<T> createOptionalTheEasyWay(T value) {
        return Optional.ofNullable(value);
    }
}
