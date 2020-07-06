package com.egem.ram.controller;

import com.egem.ram.dto.*;
import com.egem.ram.serviceapi.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "rest/report")
public class LogReportController {

    private ReportService reportService;

    @PostMapping("/list")
    public ResponseEntity<Page<HttpRequestResponse>> create(@RequestBody PageDto logPageDto) {
        return new ResponseEntity<>(reportService.list(logPageDto), HttpStatus.OK);
    }

    @GetMapping("/endPointReport")
    public ResponseEntity<List<EndPointReport>> getEndPointReport() {
        return new ResponseEntity<>(reportService.endPointReport(), HttpStatus.OK);
    }

    @GetMapping("/charLetterCount")
    public ResponseEntity<CharacterNameLetterCountResponse> getLetterCount() {
        return new ResponseEntity<>(reportService.getCharacterLetterCountSingleThread(), HttpStatus.OK);
    }

    @GetMapping("/charLetterCountMultiThread")
    public ResponseEntity<List<ThreadResponseDto>> charLetterCountMultiThread() throws InterruptedException {
        return new ResponseEntity<>(reportService.getCharacterLetterCountMultiThread(), HttpStatus.OK);
    }

    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }
}
