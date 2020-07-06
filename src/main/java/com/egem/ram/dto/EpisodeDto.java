package com.egem.ram.dto;

import java.util.List;

public class EpisodeDto {

  private String episodeName;
  private List<Long> characterIds;

  public String getEpisodeName() {
    return episodeName;
  }

  public void setEpisodeName(String episodeName) {
    this.episodeName = episodeName;
  }

  public List<Long> getCharacterIds() {
    return characterIds;
  }

  public void setCharacterIds(List<Long> characterIds) {
    this.characterIds = characterIds;
  }
}
