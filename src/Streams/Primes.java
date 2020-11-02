package Streams;

import java.util.stream.IntStream;

/**
 * @author Mohammed Amr
 * @created 03/11/2020 - 00:15
 * @project Modern Java Recipes
 */
public class Primes {

    public Primes() {}

    /** Prime number check using the noneMatch method of the Stream Interface */
    public boolean isPrime(int num) {

        int limit = (int) (Math.sqrt(num) + 1); // Upper limit for check
        return num == 2 || num > 1 && IntStream.range(2, limit)
                .noneMatch(divisor -> num % divisor == 0); // Using noneMatch
    }
}
