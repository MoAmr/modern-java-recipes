package JavaTime;

import java.time.*;
import java.util.Set;

/**
 * @author Mohammed Amr
 * @created 17/11/2020 - 07:57
 * @project Modern Java Recipes
 */
public class Main {

    public static void main(String[] args) {

        /** Using the Basic Date-Time Classes factory methods */
        // The now factory method
        System.out.println("\nThe now factory method\n");
        System.out.println("Instant.now():       " + Instant.now());
        System.out.println("LocalDate.now:       " + LocalDate.now());
        System.out.println("LocalTime.now():     " + LocalTime.now());
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());
        System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());


        /** The of method for the date/time classes */
        System.out.println("\nThe of method for the date/time classes\n");
        System.out.println("First landing ont the Moon:");
        LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
        LocalTime moonLandingTime = LocalTime.of(20, 18);
        System.out.println("Date: " + moonLandingDate);
        System.out.println("Time: " + moonLandingTime);

        System.out.println("Neil Armstrong steps onto the surface: ");
        LocalTime walkTime = LocalTime.of(20, 2, 56, 150_000_000);
        LocalDateTime walk = LocalDateTime.of(moonLandingDate, walkTime);
        System.out.println(walk);

        /** Getting the complete list of available region IDs */
        System.out.println("\nGetting the complete list of available region IDs:\n");
        Set<String> regionNames = ZoneId.getAvailableZoneIds();
        regionNames.forEach(r -> System.out.println(r));
        System.out.println("\nThere are " + regionNames.size() + " region names");

    }
}
