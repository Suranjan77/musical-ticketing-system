package org.musical.ticketing.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

/**
 * @author suranjanpoudel
 */
public class DateTimeUtils {

  public static LocalDate[] getDaysOfMonth(YearMonth ym) {

    LocalDate firstDayOfMonth = ym.atDay(1);
    LocalDate nextMonthFirstDay = ym.atEndOfMonth().plusDays(1);

    return firstDayOfMonth.datesUntil(nextMonthFirstDay).toArray(LocalDate[]::new);
  }
}
