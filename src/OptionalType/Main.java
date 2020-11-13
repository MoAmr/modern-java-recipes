package OptionalType;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
