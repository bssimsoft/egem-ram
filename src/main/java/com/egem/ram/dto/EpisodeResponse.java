package com.egem.ram.dto;

import java.util.List;

public class EpisodeResponse {

  private Long episodeId;
  private String episodeName;
  private List<CharacterDto> characterDtoList;

  public Long getEpisodeId() {
    return episodeId;
  }

  public void setEpisodeId(Long episodeId) {
    this.episodeId = episodeId;
  }

  public List<CharacterDto> getCharacterDtoList() {
    return characterDtoList;
  }

  public void setCharacterDtoList(List<CharacterDto> characterDtoList) {
    this.characterDtoList = characterDtoList;
  }

  public String getEpisodeName() {
    return episodeName;
  }

  public void setEpisodeName(String episodeName) {
    this.episodeName = episodeName;
  }
}
