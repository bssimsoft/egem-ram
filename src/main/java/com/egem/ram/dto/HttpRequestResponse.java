package com.egem.ram.dto;

import java.sql.Timestamp;

public class HttpRequestResponse {

  private Long httpRequestId;
  private String ip;
  private String body;
  private String url;
  private Timestamp requestDate;

  public Long getHttpRequestId() {
    return httpRequestId;
  }

  public void setHttpRequestId(Long httpRequestId) {
    this.httpRequestId = httpRequestId;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Timestamp getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(Timestamp requestDate) {
    this.requestDate = requestDate;
  }
}
