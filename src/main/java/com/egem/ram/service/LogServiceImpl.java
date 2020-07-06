package com.egem.ram.service;

import com.egem.ram.dao.HttpRequestRepository;
import com.egem.ram.domain.HttpRequest;
import com.egem.ram.dto.EndPointReport;
import com.egem.ram.dto.HttpRequestResponse;
import com.egem.ram.dto.PageDto;
import com.egem.ram.serviceapi.LogService;
import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

  private HttpRequestRepository httpRequestRepository;

  @Override
  public void log(HttpServletRequest httpServletRequest, Object body) {
    HttpRequest httpRequest = new HttpRequest();
    if (Objects.nonNull(body)) {
      Gson gson = new Gson();
      String json = gson.toJson(body);
      httpRequest.setBody(json);
    }
    httpRequest.setIp(httpServletRequest.getRemoteAddr());
    httpRequest.setRequestDate(new Timestamp(System.currentTimeMillis()));
    httpRequest.setUrl(httpServletRequest.getRequestURI());
    httpRequestRepository.save(httpRequest);
  }

  @Autowired
  public void setHttpRequestRepository(HttpRequestRepository httpRequestRepository) {
    this.httpRequestRepository = httpRequestRepository;
  }
}
