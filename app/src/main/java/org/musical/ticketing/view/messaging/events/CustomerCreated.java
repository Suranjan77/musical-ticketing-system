package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.domain.Customer;
import org.musical.ticketing.view.messaging.Event;

public record CustomerCreated(Long customerId) implements Event {}
