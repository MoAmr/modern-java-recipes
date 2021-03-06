package JavaTime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        /**  Parsing and formatting a LocalDate */
        System.out.println("\nParsing and formatting a LocalDate\n");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String text = now.format(DateTimeFormatter.ISO_DATE_TIME); // Format from LocalDateTime to string
        LocalDateTime dateTime = LocalDateTime.parse(text); // Parse from string to LocalDateTime
        System.out.println(dateTime);

        /** Formatting dates */
        System.out.println("\n Formatting dates\n");

        LocalDate date = LocalDate.of(2020, Month.NOVEMBER, 26);

        System.out.println("Full   : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        System.out.println("Long   : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));

        System.out.println("Medium : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));

        System.out.println("Short  : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

        System.out.println("France : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.FRANCE)));

        System.out.println("India  : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(new Locale("hin", "IN"))));

        System.out.println("Brazil : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(new Locale("pt", "BR"))));

        System.out.println("Japan  : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.JAPAN)));

        Locale loc = new Locale.Builder()
                .setLanguage("sr")
                .setScript("Latn")
                .setRegion("RS")
                .build();
        System.out.println("Serbian : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(loc)));

        /** Defining your own format pattern */
        ZonedDateTime moonLanding = ZonedDateTime.of(
                LocalDate.of(1969, Month.JULY, 20),
                LocalTime.of(20, 18),
                ZoneId.of("UTC")
        );

        System.out.println(moonLanding.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("uuuu/MMMM/dd hh:mm:ss a zzz GG");
        System.out.println(moonLanding.format(formatter));

        formatter = DateTimeFormatter.ofPattern("uuuu/MMMM/dd hh:mm:ss a VV xxxxx");
        System.out.println(moonLanding.format(formatter));

        /** Move the clocks forward */
        ZonedDateTime zdt = ZonedDateTime.of(2020, 11, 26, 2, 30, 0, 0,
                ZoneId.of("America/New_York"));
        System.out.println(zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));

        /** Using the between method of ChronoUnit enum - Days to Election Day */
        LocalDate electionDay = LocalDate.of(2020, Month.DECEMBER, 1);
        LocalDate today = LocalDate.now();

        System.out.printf("%d day(s) to go...%n",
                ChronoUnit.DAYS.between(today, electionDay));

        // Since the between method is invoked on the DAYS enum value, this will return the number of days.

        /** Using Period to get days, months, and years */
        Period until = today.until(electionDay); // Equivalent to Period.between(today, electionDay)
        int years = until.getYears();
        int months = until.getMonths();
        int days = until.getDays();
        System.out.printf("%d year(s), %d month(s), and %d day(s)%n",
                years, months, days);

        /** Using Duration.between for timing */
        LocalDateTime oldDay = LocalDateTime.of(2020, Month.NOVEMBER, 27, 01, 45, 0);
        Instant convertedOldDayToInstant = oldDay.toInstant(ZoneOffset.ofHoursMinutes(01, 45));
        System.out.println("Calling getTiming Method");
        Instant end = Instant.now();
        System.out.println(getTiming(convertedOldDayToInstant, end) + " seconds");
    }

    /** Getting region names given an offset */
    public static List<String> getRegionNamesForOffset(ZoneOffset offset) {
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId -> now.atZone(zoneId).getOffset().equals(offset))
                .map(ZoneId::toString)
                .sorted()
                .collect(Collectors.toList());
    }

    /**  Get region names for a given offset */
    public static List<String> getRegionNamesForZoneId(ZoneId zoneId) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zdt = now.atZone(zoneId);
        ZoneOffset offset = zdt.getOffset();

        return getRegionNamesForOffset(offset);
    }

    /** Getting region names given an hour and minute offset */
    public static List<String> getRegionNamesForOffset(int hours, int minutes) {
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(hours, minutes);
        return getRegionNamesForOffset(offset);
    }

    /** Timing a method */
    public static double getTiming(Instant start, Instant end) {
        return Duration.between(start, end).toMillis() / 1000.0;
    }

    /** Method to calculate days until Talk Like A Pirate Day */
    private long dayUntilPirateDay(TemporalAccessor temporal) {
        int day = temporal.get(ChronoField.DAY_OF_MONTH);
        int month = temporal.get(ChronoField.MONTH_OF_YEAR);
        int year = temporal.get(ChronoField.YEAR);
        LocalDate date = LocalDate.of(year, month, day);
        LocalDate tlapd = LocalDate.of(year, Month.SEPTEMBER, 19);
        if (date.isAfter(tlapd)) {
            tlapd = tlapd.plusYears(1);
        }
        return ChronoUnit.DAYS.between(date, tlapd);
    }

    /** Converting java.util.Date to java.time.LocalDate via Instant */
    public LocalDate convertFromUtilDateUsingInstant(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

    /** Testing the PayDay adjuster for August 2020 */
    // In August 2020, the 15th occurred on a Saturday and the 31st was on a Monday.
    @Test
    public void payDay() throws Exception {
        TemporalAdjuster adjuster = new PaydayAdjuster();
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2020, Month.AUGUST, day))
                .forEach(date ->
                        assertEquals(14, date.with(adjuster).getDayOfMonth()));

        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2020, Month.AUGUST, day))
                .forEach(date ->
                        assertEquals(31, date.with(adjuster).getDayOfMonth()));
    }

    /**  Using a method reference for the temporal adjuster */
    @Test
    public void payDayWithMethodRef() throws Exception {

        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2020, Month.AUGUST, day))
                .forEach(date ->
                        assertEquals(14,
                                date.with(Adjusters::adjustInto).getDayOfMonth()));

        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2020, Month.AUGUST, day))
                .forEach(date ->
                        assertEquals(31,
                                date.with(Adjusters::adjustInto).getDayOfMonth())); // Method reference to adjustInto

    }

    /** Using the methods from TemporalQueries */
    @Test
    public void queries() throws Exception {
        assertEquals(ChronoUnit.DAYS,
                LocalDate.now().query(TemporalQueries.precision()));

        assertEquals(ChronoUnit.NANOS,
                LocalTime.now().query(TemporalQueries.precision()));

        assertEquals(ZoneId.systemDefault(),
                ZonedDateTime.now().query(TemporalQueries.zone()));

        assertEquals(ZoneId.systemDefault(),
                ZonedDateTime.now().query(TemporalQueries.zoneId()));

    }

    /** Using a TemporalQuery via a method reference */
    @Test
    public void pirateDay() throws Exception {
        IntStream.range(10, 19)
                .mapToObj(n -> LocalDate.of(2020, Month.SEPTEMBER, n))
                .forEach(date ->
                        assertTrue(date.query(this::dayUntilPirateDay) <= 9));

        IntStream.rangeClosed(20, 30)
                .mapToObj(n -> LocalDate.of(2020, Month.SEPTEMBER, n))
                .forEach(date -> {
                    Long days = date.query(this::dayUntilPirateDay);
                });
    }

    /** Getting the current region names */
    @Test
    public void getRegionNamesForSystemDefault() throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId zoneId = now.getZone();
        List<String> names = getRegionNamesForZoneId(zoneId);

        assertTrue(names.contains(zoneId.getId()));
    }

    /** Testing region names for a given offset */
    @Test
    public void getRegionNamesForGMT() throws Exception {

        List<String> names = getRegionNamesForOffset(0, 0);

        assertTrue(names.contains("GMT"));
        assertTrue(names.contains("Etc/GMT"));
        assertTrue(names.contains("Etc/UTC"));
        assertTrue(names.contains("UTC"));
        assertTrue(names.contains("Etc/Zulu"));
    }

    @Test
    public void getRegionNamesForNepal() throws Exception {
        List<String> names = getRegionNamesForOffset(5, 45);
        assertTrue(names.contains("Asia/Kathmandu"));
        assertTrue(names.contains("Asia/Katmandu"));
    }

    @Test
    public void getRegionNamesForChicago() throws Exception {
        ZoneId chicago = ZoneId.of("America/Chicago");
        List<String> names = getRegionNamesForZoneId(chicago);

        assertTrue(names.contains("America/Chicago"));
        assertTrue(names.contains("US/Central"));
        assertTrue(names.contains("Canada/Central"));
        assertTrue(names.contains("Etc/GMT+5") || names.contains("Etc/GMT+6"));

    }
}
