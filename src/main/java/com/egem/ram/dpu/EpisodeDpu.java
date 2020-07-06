package com.egem.ram.dpu;

import com.egem.ram.config.DpuStarterEvent;
import com.egem.ram.dao.CharacterRepository;
import com.egem.ram.dao.EpisodeRepository;
import com.egem.ram.domain.Character;
import com.egem.ram.domain.Episode;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class EpisodeDpu implements ApplicationListener<DpuStarterEvent> {

  private CharacterRepository characterRepository;
  private EpisodeRepository episodeRepository;

  @Override
  public void onApplicationEvent(final DpuStarterEvent event) {
    createData();
  }

  private void createData() {
    for (int i = 0; i < 3; i++) {
      Episode episode = new Episode();
      episode.setEpisodeName("Episeode" + i);
      episode.setCharacters(episodeCharacters());
      episodeRepository.save(episode);
    }
  }

  private Set<Character> episodeCharacters() {
    Set<Character> characters = new HashSet<>();
    List<Character> characterList = characterRepository.findAll();
    Random rn = new Random();
    for (int i = 0; i < 2; i++) {
      characters.add(characterList.get(rn.nextInt(characterList.size() - 1)));
    }
    return characters;
  }

  @Autowired
  public void setCharacterRepository(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  @Autowired
  public void setEpisodeRepository(EpisodeRepository episodeRepository) {
    this.episodeRepository = episodeRepository;
  }
}
