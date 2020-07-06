package com.egem.ram.dto;

public class ExceptionHandlerDto {

  private String message;
  private String localizedMessage;
  private Boolean warning;

  public Boolean getWarning() {
    return warning;
  }

  public void setWarning(Boolean warning) {
    this.warning = warning;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getLocalizedMessage() {
    return localizedMessage;
  }

  public void setLocalizedMessage(String localizedMessage) {
    this.localizedMessage = localizedMessage;
  }
}
