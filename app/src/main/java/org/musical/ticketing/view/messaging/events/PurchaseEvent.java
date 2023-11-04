package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.domain.ShowTime;
import org.musical.ticketing.view.messaging.Event;
import org.musical.ticketing.view.models.TicketAccountingData;

public record PurchaseEvent(ShowTime showTime, Long customerId, TicketAccountingData accountData) implements Event {

}
