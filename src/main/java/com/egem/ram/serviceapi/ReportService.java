package com.egem.ram.serviceapi;

import com.egem.ram.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {

    Page<HttpRequestResponse> list(PageDto logPageDto);

    List<EndPointReport> endPointReport();

    CharacterNameLetterCountResponse getCharacterLetterCountSingleThread();

    List<ThreadResponseDto> getCharacterLetterCountMultiThread() throws InterruptedException;
}
