package Streams;

/**
 * @author Mohammed Amr
 * @created 30/10/2020 - 02:24
 * @project Modern Java Recipes
 */
public class PalindromeEvaluator {

    /** Checking for palindromes in Java 7 or earlier */
    public boolean isPalindromeJava7OrEarlier(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    /** Checking for palindromes using Java 8 streams */
    public boolean isPalindrome(String s) {
        String forward = s.toLowerCase().codePoints() // Returns an IntStream
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }
}
