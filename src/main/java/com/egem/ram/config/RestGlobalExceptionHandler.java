package com.egem.ram.config;

import com.egem.ram.dto.ExceptionHandlerDto;
import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestGlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestGlobalExceptionHandler.class);

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<Object> handleConflict(Exception e, WebRequest request) {
    LOGGER.error("Global Exception Handler:", e);

    ExceptionHandlerDto exceptionHandlerDto = new ExceptionHandlerDto();
    exceptionHandlerDto.setMessage(e.getMessage());
    exceptionHandlerDto.setLocalizedMessage(e.getLocalizedMessage());

    if (e instanceof PropertyReferenceException) {
      return new ResponseEntity<>(exceptionHandlerDto, HttpStatus.EXPECTATION_FAILED);
    } else if (e instanceof ValidationException || e instanceof ServiceException) {
      assert e instanceof ServiceException;
      ServiceException se = (ServiceException) e;
      return new ResponseEntity<>(exceptionHandlerDto, HttpStatus.EXPECTATION_FAILED);
    } else if (e instanceof EntityNotFoundException) {
      return new ResponseEntity<>(exceptionHandlerDto, HttpStatus.EXPECTATION_FAILED);
    } else if (e instanceof NullPointerException) {
      return new ResponseEntity<>(exceptionHandlerDto, HttpStatus.EXPECTATION_FAILED);
    } else if (e instanceof HttpRequestMethodNotSupportedException) {
      return new ResponseEntity<>(exceptionHandlerDto, HttpStatus.EXPECTATION_FAILED);
    } else {
      Throwable result = new ServiceException("Beklenmeyen hata oluştu!.");
      exceptionHandlerDto.setMessage(result.getMessage());
      exceptionHandlerDto.setLocalizedMessage(result.getLocalizedMessage());
      return new ResponseEntity<>(exceptionHandlerDto, HttpStatus.EXPECTATION_FAILED);
    }
  }
}
