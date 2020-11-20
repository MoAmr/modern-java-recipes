package JavaTime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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

    /** The plus and minus methods */
    @Test
    public void plus_minus() throws Exception {

        Period period = Period.of(2, 3, 4); // 2 years, 3 months, 4 days
        LocalDateTime start = LocalDateTime.of(2020, Month.NOVEMBER, 19, 00, 45);
        LocalDateTime end = start.plus(period);

        assertEquals("2023-02-23T00:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.plus(3, ChronoUnit.HALF_DAYS);
        assertEquals("2020-11-20T12:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.minus(period);
        assertEquals("2018-08-15T00:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.minus(2, ChronoUnit.CENTURIES);
        assertEquals("1820-11-19T00:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.plus(3, ChronoUnit.MILLENNIA);
        assertEquals("5020-11-19T00:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    }

    /** Using with methods on LocalDateTime */
    @Test
    public void with() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.NOVEMBER, 20, 22, 16);
        LocalDateTime end = start.withMinute(45);
        assertEquals("2020-11-20T22:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withHour(16);
        assertEquals("2020-11-20T16:16:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withDayOfMonth(28);
        assertEquals("2020-11-28T22:16:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withDayOfYear(300);
        assertEquals("2020-10-26T22:16:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withYear(2021);
        assertEquals("2021-11-20T22:16:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    /** Adjusting the month to an invalid value */
    @Test
    public void temporalField() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.NOVEMBER, 30, 22, 16);
        LocalDateTime end = start.with(ChronoField.MONTH_OF_YEAR, 2);
        assertEquals("2020-02-20T22:16:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    /** Using static methods in TemporalAdjusters */
    @Test
    public void adjusters() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.NOVEMBER, 20, 22, 16);
        LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
        assertEquals("2020-12-01T22:16", end.toString());

        end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        assertEquals("2020-11-26T22:16", end.toString());

        end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        assertEquals("2020-11-19T22:16", end.toString());
    }
}
