package com.egem.ram.service;

import com.egem.ram.dao.HttpRequestRepository;
import com.egem.ram.domain.Character;
import com.egem.ram.domain.HttpRequest;
import com.egem.ram.dto.*;
import com.egem.ram.serviceapi.CharacterService;
import com.egem.ram.serviceapi.ReportService;
import com.egem.ram.serviceapi.ThreadCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private HttpRequestRepository httpRequestRepository;
    private CharacterService characterService;
    private ThreadCreatorService threadCreatorService;

    @Override
    public Page<HttpRequestResponse> list(PageDto logPageDto) {
        Pageable pageable = PageRequest.of(logPageDto.getPageNum(), logPageDto.getPageSize(),
                Sort.by(Sort.Order.asc(Objects.isNull(logPageDto.getSortField()) ? "requestDate"
                        : logPageDto.getSortField())));
        return getLogResponseList(httpRequestRepository.findAll(pageable), pageable);
    }

    @Override
    public List<EndPointReport> endPointReport() {
        EndPointReport episode = getEpisodeReport();
        EndPointReport character = getCharacterReport();
        EndPointReport location = getLocationReport();
        return new ArrayList<>(
                Arrays.asList(episode, character, location));
    }

    @Override
    public CharacterNameLetterCountResponse getCharacterLetterCountSingleThread() {
        List<Character> characters = characterService.findAll();
        List<String> characterNames = characters.stream().map(Character::getName).collect(Collectors.toList());
        Integer count = 0;
        for(String charName : characterNames){
            count += charName.length();
        }
        return new CharacterNameLetterCountResponse(count);
    }

    @Override
    public List<ThreadResponseDto> getCharacterLetterCountMultiThread() throws InterruptedException {
        List<Character> characters = characterService.findAll();
        List<String> characterNames = characters.stream().map(Character::getName).collect(Collectors.toList());
        List<ThreadResponseDto> threadResponse = threadCreatorService.getThreadResponse(characterNames);
        threadResponse.add(new ThreadResponseDto("allThreadTotal", getThreadResponCount(threadResponse.stream().map(ThreadResponseDto::getLetterCount).collect(Collectors.toList()))));
        return threadResponse;
    }

    private Integer getThreadResponCount(List<Integer> collect) {
        Integer total = 0;
        for(Integer count : collect){
            total += count;
        }
        return total;
    }

    private EndPointReport getLocationReport() {
        EndPointReport endPointReport = new EndPointReport();
        endPointReport.setEndPointName("location");
        endPointReport.setRequestCount(httpRequestRepository.countAllByUrlContains("location"));
        return endPointReport;
    }

    private EndPointReport getCharacterReport() {
        EndPointReport endPointReport = new EndPointReport();
        endPointReport.setEndPointName("character");
        endPointReport.setRequestCount(httpRequestRepository.countAllByUrlContains("character"));
        return endPointReport;
    }

    private EndPointReport getEpisodeReport() {
        EndPointReport endPointReport = new EndPointReport();
        endPointReport.setEndPointName("episode");
        endPointReport.setRequestCount(httpRequestRepository.countAllByUrlContains("episode"));
        return endPointReport;
    }

    private Page<HttpRequestResponse> getLogResponseList(Page<HttpRequest> all, Pageable pageable) {
        List<HttpRequestResponse> list = new ArrayList<>();
        for (HttpRequest hr : all) {
            HttpRequestResponse httpRequestResponse = new HttpRequestResponse();
            httpRequestResponse.setBody(hr.getBody());
            httpRequestResponse.setHttpRequestId(hr.getHttpRequestId());
            httpRequestResponse.setIp(hr.getIp());
            httpRequestResponse.setRequestDate(hr.getRequestDate());
            httpRequestResponse.setUrl(hr.getUrl());
            list.add(httpRequestResponse);
        }
        return new PageImpl<>(list, pageable, list.size());
    }

    @Autowired
    public void setHttpRequestRepository(HttpRequestRepository httpRequestRepository) {
        this.httpRequestRepository = httpRequestRepository;
    }

    @Autowired
    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Autowired
    public void setThreadCreatorService(ThreadCreatorService threadCreatorService) {
        this.threadCreatorService = threadCreatorService;
    }
}
