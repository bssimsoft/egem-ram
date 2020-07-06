package com.egem.ram.config;

import com.egem.ram.serviceapi.LogService;
import java.lang.reflect.Type;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

@ControllerAdvice
public class RequestBodyLogger extends RequestBodyAdviceAdapter {

  private HttpServletRequest httpServletRequest;

  private LogService logService;

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
      MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    logService.log(httpServletRequest, body);
    return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
  }

  @Autowired
  public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  @Autowired
  public void setLogService(LogService logService) {
    this.logService = logService;
  }
}
