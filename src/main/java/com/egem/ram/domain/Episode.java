package com.egem.ram.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Episode {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long episodeId;

    @Column(name = "EpisodeName")
    private String episodeName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Episode_Character",
            joinColumns = {@JoinColumn(name = "episodeId")},
            inverseJoinColumns = {@JoinColumn(name = "characterId")})
    private Set<Character> characters = new HashSet<>();

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }
}
