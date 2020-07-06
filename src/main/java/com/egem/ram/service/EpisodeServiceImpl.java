package com.egem.ram.service;

import com.egem.ram.dao.EpisodeRepository;
import com.egem.ram.domain.Character;
import com.egem.ram.domain.Episode;
import com.egem.ram.dto.CharacterDto;
import com.egem.ram.dto.EpisodeDto;
import com.egem.ram.dto.EpisodeResponse;
import com.egem.ram.dto.PageDto;
import com.egem.ram.serviceapi.CharacterService;
import com.egem.ram.serviceapi.EpisodeService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl implements EpisodeService {

  private EpisodeRepository episodeRepository;
  private CharacterService characterService;

  @Override
  public EpisodeResponse read(Long episodeId) {
    return getEpisodeResponse(episodeRepository.getOne(episodeId.longValue()));
  }

  private EpisodeResponse getEpisodeResponse(Episode one) {
    EpisodeResponse episodeResponse = new EpisodeResponse();
    episodeResponse.setEpisodeId(one.getEpisodeId());
    episodeResponse.setCharacterDtoList(getCharacterDtoList(one.getCharacters()));
    episodeResponse.setEpisodeName(one.getEpisodeName());
    return episodeResponse;
  }

  @Override
  @Transactional
  public void create(EpisodeDto episode) {
    Episode ep = new Episode();
    ep.setEpisodeName(episode.getEpisodeName());
    ep.setCharacters(getEpisodeCharacters(episode.getCharacterIds()));
    episodeRepository.save(ep);
  }

  private Set<Character> getEpisodeCharacters(List<Long> characterIds) {
    Set<Character> characters = new HashSet<>();
    for (Long id : characterIds) {
      characters.add(characterService.readEntity(id));
    }
    return characters;
  }

  @Override
  public Page<EpisodeResponse> list(PageDto episodePageDto) {
    Pageable pageable = PageRequest.of(episodePageDto.getPageNum(), episodePageDto.getPageSize(),
        Sort.by(Order.asc(Objects.isNull(episodePageDto.getSortField()) ? "episodeName"
            : episodePageDto.getSortField())));
    return getEpisodePageResponse(episodeRepository.findAll(pageable), pageable);
  }

  private Page<EpisodeResponse> getEpisodePageResponse(Page<Episode> all, Pageable pageable) {
    List<EpisodeResponse> episodeResponses = new ArrayList<>();
    for (Episode episode : all.getContent()) {
      EpisodeResponse episodeResponse = new EpisodeResponse();
      episodeResponse.setEpisodeName(episode.getEpisodeName());
      episodeResponse.setEpisodeId(episode.getEpisodeId());
      episodeResponse.setCharacterDtoList(getCharacterDtoList(episode.getCharacters()));
      episodeResponses.add(episodeResponse);
    }
    return new PageImpl<>(episodeResponses, pageable, episodeResponses.size());
  }

  private List<CharacterDto> getCharacterDtoList(Set<Character> characters) {
    List<CharacterDto> characterDtos = new ArrayList<>();
    for (Character c : characters) {
      CharacterDto characterDto = new CharacterDto();
      characterDto.setLocationId(c.getLocation().getLocationId());
      characterDto.setName(c.getName());
      characterDtos.add(characterDto);
    }
    return characterDtos;
  }

  @Autowired
  public void setCharacterService(CharacterService characterService) {
    this.characterService = characterService;
  }

  @Autowired
  public void setEpisodeRepository(EpisodeRepository episodeRepository) {
    this.episodeRepository = episodeRepository;
  }
}
