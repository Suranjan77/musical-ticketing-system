package org.musical.ticketing.view.messaging.events;

import org.musical.ticketing.view.messaging.Event;

public record MusicalSearchEvent(String query) implements Event {}
