package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.domain.ShowTime;
import org.musical.ticketing.view.messaging.Event;

/**
 *
 * @author suranjanpoudel
 */
public record TimeSlotSelected(ShowTime showTime) implements Event{

}
