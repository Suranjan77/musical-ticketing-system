package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.view.messaging.Event;

public record MusicalClickedEvent(Long musicalId, Long customerId) implements Event {}
