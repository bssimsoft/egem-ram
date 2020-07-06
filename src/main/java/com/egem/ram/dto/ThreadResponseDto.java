package com.egem.ram.dto;

public class ThreadResponseDto {

    private String threadName;
    private Integer letterCount;

    public ThreadResponseDto() {
    }

    public ThreadResponseDto(String threadName, Integer letterCount) {
        this.threadName = threadName;
        this.letterCount = letterCount;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Integer getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(Integer letterCount) {
        this.letterCount = letterCount;
    }
}
