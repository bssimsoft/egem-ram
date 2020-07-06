package com.egem.ram.dto;

public class CharacterNameLetterCountResponse {

    private Integer totalCount;

    public CharacterNameLetterCountResponse(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
