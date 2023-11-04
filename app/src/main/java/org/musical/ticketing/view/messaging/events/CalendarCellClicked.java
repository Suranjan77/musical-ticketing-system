package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.view.models.CellData;
import org.musical.ticketing.view.messaging.Event;

/**
 *
 * @author suranjanpoudel
 */
public record CalendarCellClicked(CellData data) implements Event {

}
