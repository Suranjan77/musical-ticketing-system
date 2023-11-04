package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.view.components.TicketDetailsData;

public record PurchaseEvent(TicketDetailsData ticketDetailsData) {}
