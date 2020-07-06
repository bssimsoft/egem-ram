package com.egem.ram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class DpuStarter implements ApplicationListener<ApplicationReadyEvent> {

  private ApplicationEventPublisher applicationEventPublisher;

  @Value("${activate.dpu.starter}")
  private Boolean activateDpu;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    DpuStarterEvent dpuStarterEvent = new DpuStarterEvent(this);
    if (activateDpu) {
      applicationEventPublisher.publishEvent(dpuStarterEvent);
    }
  }

  @Autowired
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }
}
