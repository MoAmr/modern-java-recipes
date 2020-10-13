package LambdaExpressions;

/**
 * @author Mohammed Amr
 * @created 13/10/2020 - 22:36
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        // Assigning Lambda to a variable
        Runnable r = () -> System.out.println("Inside Thread constructor using lambda");
        new Thread(r).start();
        
    }
}
