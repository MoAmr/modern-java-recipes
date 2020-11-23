package JavaTime;

/**
 * @author Mohammed Amr
 * @created 23/11/2020 - 00:50
 * @project Modern Java Recipes
 */

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;

/** Converting java.util classes to java.time classes */
public class ConvertDate {

    public LocalDate convertFromSqlDateToLD(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }

    public java.sql.Date convertFromLDToSqlDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public LocalDateTime convertFromTimestampToLDT(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public Timestamp convertFromLDTToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    /** Converting a java.util.Date to a java.time.LocalDate */
    public LocalDate convertUtilDateToLocalDate(java.util.Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    /** Converting from java.util.Calendar to java.time.ZonedDateTime */
    public ZonedDateTime convertFromCalendar(Calendar cal) {
        return ZonedDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
    }

    /** Using getter methods from Calendar to LocalDateTime */
    public LocalDateTime cinvertFromCalendarUsingGetters(Calendar cal) {
        return LocalDateTime.of(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND));
    }
}
