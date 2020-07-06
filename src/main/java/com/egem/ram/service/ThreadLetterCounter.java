package com.egem.ram.service;

import com.egem.ram.dto.ThreadResponseDto;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadLetterCounter extends Thread {

    private String threadName;
    private List<String>  characterNames;
    private CountDownLatch latch;
    private ThreadResponseDto threadResponseDto;

    void setGeneralObjects(List<String> charNames, String threadName, CountDownLatch latch, ThreadResponseDto threadResponseDto){
        this.characterNames = charNames;
        this.threadName = threadName;
        this.latch = latch;
        this.threadResponseDto = threadResponseDto;
    }

    public void run() {
        int count = 0;
        for(String name : characterNames){
            count += name.length();
        }
        threadResponseDto.setLetterCount(count);
        threadResponseDto.setThreadName(threadName);
        latch.countDown();
    }
}
