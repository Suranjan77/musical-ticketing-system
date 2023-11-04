package org.musical.ticketing.view.models;

import java.time.LocalDate;

/**
 *
 * @author suranjanpoudel
 * @param isToday
 * @param title
 * @param isDayName
 * @param date
 * @param isDisabled
 */
public record CellData(boolean isToday, String title, boolean isDayName, LocalDate date, boolean isDisabled, Long customerId, Long musicalId) {

}
