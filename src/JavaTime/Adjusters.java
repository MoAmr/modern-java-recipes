package JavaTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Mohammed Amr
 * @created 20/11/2020 - 23:50
 * @project Modern Java Recipes
 */

// Utility class with adjusters
public class Adjusters { // Does not implement TemporalAdjuster

    public static Temporal adjustInto(Temporal input) { // Static method, so no instantiation required
        LocalDate date = LocalDate.from(input); // Useful way to convert any Temporal to a LocalDate
        int day;
        if (date.getDayOfMonth() < 15) {
            day = 15;
        } else {
            day = date.with(TemporalAdjusters.lastDayOfMonth())
                    .getDayOfMonth();
        }

        date = date.withDayOfMonth(day);
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        return input.with(date);
    }
}
