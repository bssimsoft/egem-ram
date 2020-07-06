package com.egem.ram.config;

import org.springframework.context.ApplicationEvent;

public class DpuStarterEvent extends ApplicationEvent {

  /**
   * Create a new ApplicationEvent.
   *
   * @param source the object on which the event initially occurred (never {@code null})
   */
  public DpuStarterEvent(Object source) {
    super(source);
  }
}
