package org.musical.ticketing.view.messaging;

public interface EventListener {
  void handleEvent(Object event);

  void register();
}
