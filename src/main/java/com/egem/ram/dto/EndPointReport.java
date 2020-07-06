package com.egem.ram.dto;

public class EndPointReport {

  private Integer requestCount;
  private String endPointName;

  public Integer getRequestCount() {
    return requestCount;
  }

  public void setRequestCount(Integer requestCount) {
    this.requestCount = requestCount;
  }

  public String getEndPointName() {
    return endPointName;
  }

  public void setEndPointName(String endPointName) {
    this.endPointName = endPointName;
  }
}
