package com.egem.ram.config;

import com.egem.ram.serviceapi.LogService;
import java.time.Instant;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class Interceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
  private LogService logService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    long startTime = Instant.now().toEpochMilli();
    if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
        && request.getMethod().equals(HttpMethod.GET.name())) {
      logService.log(request, null);
    }
    logger.info("Request URL::" + request.getRequestURL().toString() +
        ":: Start Time=" + Instant.now());
    request.setAttribute("startTime", startTime);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    long startTime = (Long) request.getAttribute("startTime");
    logger.info("Request URL::" + request.getRequestURL().toString() +
        ":: Time Taken=" + (Instant.now().toEpochMilli() - startTime));
  }

  @Autowired
  public void setLogService(LogService logService) {
    this.logService = logService;
  }
}
