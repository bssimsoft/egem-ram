package com.egem.ram.serviceapi;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

    void log(HttpServletRequest httpServletRequest, Object body);
}
