package JavaTime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

        /** Applying a time zone to a LocalDateTime */
        System.out.println("\nApplying a time zone to a LocalDateTime\n");

//        LocalDateTime dateTime = LocalDateTime.of(2020, Month.NOVEMBER, 18, 23, 03, 10);
        LocalDateTime currentDateTime = LocalDateTime.now();
        ZonedDateTime cai = currentDateTime.atZone(ZoneId.of("Africa/Cairo"));
        System.out.println(cai);

        ZonedDateTime nyc = cai.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(nyc);

        ZonedDateTime london = cai.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println(london);

        /** Some methods in the Month enum */
        System.out.println("\nSome methods in the Month enum\n");
        System.out.println("Days in Feb in a leap year: " +
                Month.FEBRUARY.length(true)); // Argument is boolean leapYear
        System.out.println("Days of year for first day of Aug (leap year): " +
                Month.AUGUST.firstDayOfYear(true)); // Argument is boolean leapYear
        System.out.println("Month.of(1): " + Month.of(1));
        System.out.println("Adding two months: " + Month.JANUARY.plus(2));
        System.out.println("Subtracting a month: " + Month.MARCH.minus(1));

    }

    /** Using plus methods on LocalDate */
    @Test
    public void localDatePlus() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.of(2020, Month.NOVEMBER, 19);

        LocalDate end = start.plusDays(3);
        assertEquals("2020-11-22", end.format(formatter));

        end = start.plusWeeks(5);
        assertEquals("2020-12-24", end.format(formatter));

        end = start.plusMonths(7);
        assertEquals("2021-06-19", end.format(formatter));

        end = start.plusYears(2);
        assertEquals("2022-11-19", end.format(formatter));

    }

    /** Using plus methods on LocalTime */
    @Test
    public void localTimePlus() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime start = LocalTime.of(11, 30, 0, 0);

        LocalTime end = start.plusNanos(1_000_000);
        assertEquals("11:30:00.001", end.format(formatter));

        end = start.plusSeconds(20);
        assertEquals("11:30:20", end.format(formatter));

        end = start.plusMinutes(45);
        assertEquals("12:15:00", end.format(formatter));

        end = start.plusHours(5);
        assertEquals("16:30:00", end.format(formatter));

    }
}
