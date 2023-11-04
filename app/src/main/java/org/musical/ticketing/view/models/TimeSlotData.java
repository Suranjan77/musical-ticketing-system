package org.musical.ticketing.view.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author suranjanpoudel
 */
public record TimeSlotData(int id, LocalTime startTime, LocalTime endTime) {

    public String formattedText() {
        var timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return new StringBuilder(startTime.format(timeFormatter))
                .append("  --  ")
                .append(endTime.format(timeFormatter))
                .toString();
    }
}
