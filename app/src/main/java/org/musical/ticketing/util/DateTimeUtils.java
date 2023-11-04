package org.musical.ticketing.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author suranjanpoudel
 */
public class DateTimeUtils {

    public static LocalDate[] getDaysOfMonth(YearMonth ym) {

        LocalDate firstDayOfMonth = ym.atDay(1);
        LocalDate nextMonthFirstDay = ym.atEndOfMonth().plusDays(1);

        return firstDayOfMonth.datesUntil(nextMonthFirstDay).toArray(LocalDate[]::new);
    }

    public static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public static String formatPrintableDate(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.UK) + " " + date.getDayOfMonth() + ", " + date.getYear();
    }
}
