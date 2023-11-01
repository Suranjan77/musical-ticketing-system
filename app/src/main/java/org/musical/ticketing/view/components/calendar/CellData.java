package org.musical.ticketing.view.components.calendar;

import java.time.LocalDate;
import java.util.function.Consumer;

/**
 *
 * @author suranjanpoudel
 * @param isToday
 * @param title
 * @param isDayName
 * @param date
 * @param isDisabled
 */
public record CellData(boolean isToday, String title, boolean isDayName, LocalDate date, boolean isDisabled, Consumer<?> onClick) {

}
