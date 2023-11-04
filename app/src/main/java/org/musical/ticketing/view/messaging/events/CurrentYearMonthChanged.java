package org.musical.ticketing.view.messaging.events;

import java.time.YearMonth;
import org.musical.ticketing.view.messaging.Event;

/**
 *
 * @author suranjanpoudel
 */
public record CurrentYearMonthChanged(YearMonth ym) implements Event{

}
