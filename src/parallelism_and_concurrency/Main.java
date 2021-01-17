package parallelism_and_concurrency;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mohammed Amr
 * @created 29/11/2020 - 00:01
 * @project Modern Java Recipes
 */

public class Main {

    public static void main(String[] args) {

        /** Submitting a Callable and returning the Future */
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                return "Hello, Wolrd!";
            }
        });
        System.out.println("Processing...");
        getIfNotCancelled(future);

        /** Using a lambda expression and checking if the Future is done */
        future = service.submit(() -> { // Lambda expression for the Callable
            Thread.sleep(10);
            return "Hello, World!";
        });

        System.out.println("More processing...");

        while (!future.isDone()) { // Wait until Future is finished
            System.out.println("Waiting...");
        }

        getIfNotCancelled(future);

        /** Cancelling the Future */
        future = service.submit(() -> {
            Thread.sleep(10);
            return "Hello, World!";
        });

        future.cancel(true);

        System.out.println("Even more processing...");

        getIfNotCancelled(future);

        CompletableFuture.supplyAsync(() -> sleepThenReturnString())
                .thenApply(Integer::parseInt)
                .thenApply(x -> 2 * x)
                .thenAccept(System.out::println)
                .join();

        System.out.println("Running...");

    }

    /** Coordinating tasks using a CompletableFuture */
    private static String sleepThenReturnString() {

        try {
            Thread.sleep(100); // Introduce an artificial delay
        } catch(InterruptedException ignored) {

        }

        return "42";
    }


    /** Summing generic sequential streams */
    public long sequentialStreamSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(10)
                .reduce(0L, Long::sum);
    }

    /** Summing generic parallel streams */
    public long paralleStreamSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(10)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /** Using LongStream */
    public long sequentialLongStreamSum() {
        return LongStream.rangeClosed(1, 10)
                .sum();
    }

    public long parallelLongStream() {
        return LongStream.rangeClosed(1, 10)
                .parallel()
                .sum();
    }

    /** Retrieving a value from a Future */
    public static void getIfNotCancelled(Future<String> future) {

        try {
            if (!future.isCancelled()) { // Check status of Future
                System.out.println(future.get()); // Blocking call to retrieve its value
            } else {
                System.out.println("Cancelled");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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

    /** Composing two Futures together */
    @Test
    public void compose() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                .thenCompose(n -> CompletableFuture.supplyAsync(() -> n + y));

        assertTrue(5 == completableFuture.get());
    }


}
