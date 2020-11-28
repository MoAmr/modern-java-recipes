package parallelism_and_concurrency;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mohammed Amr
 * @created 29/11/2020 - 00:01
 * @project Modern Java Recipes
 */

public class Main {

    public static void main(String[] args) {

    }

    /** Creating sequential streams (parts of a JUnit test) */
    @Test
    public void sequentialStreamOf() throws Exception {
        assertFalse(Stream.of(3, 1, 4, 1, 5, 9).isParallel());
    }

    @Test
    public void sequentialIterateStream() throws Exception {
        assertFalse(Stream.iterate(1, n -> n + 1).isParallel());
    }

    @Test
    public void sequentialGenerateStream() throws Exception {
        assertFalse(Stream.generate(Math::random).isParallel());
    }

    @Test
    public void sequentialCollectionStream() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.stream().isParallel());
    }

    /** Using the parallelStream method */
    @Test
    public void parallelStreamMethodOnCollection() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.parallelStream().isParallel());
    }

    /**  Using the parallel method on a stream */
    @Test
    public void parallelMethodOnStream() throws Exception {
        assertTrue(Stream.of(3, 1, 4, 1, 5, 9)
        .parallel()
        .isParallel());
    }

    /** Converting a parallel stream to sequential*/
    @Test
    public void parallelStreamThenSequential() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.parallelStream()
                .sequential()
                .isParallel());
    }


}
