package com.egem.ram.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HttpRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long httpRequestId;

    @Column(name = "Ip")
    private String ip;

    @Column(name = "Body")
    private String body;

    @Column(name = "Url")
    private String url;

    @Column(name = "RequestDate")
    private Timestamp requestDate;

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

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
}
