package com.egem.ram.serviceapi;

import com.egem.ram.dto.ThreadResponseDto;

import java.util.List;

public interface ThreadCreatorService {
    List<ThreadResponseDto> getThreadResponse(List<String> charNames) throws InterruptedException;
}
