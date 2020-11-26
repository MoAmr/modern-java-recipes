package JavaTime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;

/**
 * @author Mohammed Amr
 * @created 27/11/2020 - 00:23
 * @project Modern Java Recipes
 */
public class FunnyOffsets {

    public static void main(String[] args) {

        Instant instant = Instant.now();
        ZonedDateTime current = instant.atZone(ZoneId.systemDefault());
        System.out.printf("Current time is %s%n%n", current);

        System.out.printf("%10s %20s %13s%n", "Offset", "ZoneId", "Time");
        ZoneId.getAvailableZoneIds().stream()
                // Map the string region IDs to zone IDs
                .map(ZoneId::of)
                .filter(zoneId -> {
                    ZoneOffset offset = instant.atZone(zoneId).getOffset(); // Calculate the offset
                    return offset.getTotalSeconds() % (60 * 60) != 0; // Only use zone IDs whose offsets are not divisible by 3,600
                })
                .sorted(Comparator.comparingInt(zoneId ->
                        instant.atZone(zoneId).getOffset().getTotalSeconds()))
                .forEach(zoneId -> {
                    ZonedDateTime zdt = current.withZoneSameInstant(zoneId);
                    System.out.printf("%10s %25s %10s%n",
                            zdt.getOffset(), zoneId,
                            zdt.format(DateTimeFormatter.ofLocalizedTime(
                                    FormatStyle.SHORT)));
                });
    }
}
