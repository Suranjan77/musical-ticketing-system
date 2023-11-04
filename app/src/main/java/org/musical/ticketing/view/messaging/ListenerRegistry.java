package org.musical.ticketing.view.messaging;

import java.util.concurrent.ConcurrentHashMap;

public class ListenerRegistry {
  private static final ConcurrentHashMap<Class<? extends Event>, EventListener> registry =
      new ConcurrentHashMap<>();

  public static void register(Class<? extends Event> eventClazz, EventListener listener) {
    registry.put(eventClazz, listener);
  }

  public static void notify(Object e) {
    var listener = registry.get(e.getClass());
    if (listener != null) {
      listener.handleEvent(e);
    }
  }
}
