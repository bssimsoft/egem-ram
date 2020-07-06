package com.egem.ram.service;

import com.egem.ram.dao.CharacterRepository;
import com.egem.ram.domain.Character;
import com.egem.ram.dto.CharacterDto;
import com.egem.ram.dto.PageDto;
import com.egem.ram.serviceapi.CharacterService;
import com.egem.ram.serviceapi.LocationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class CharacterServiceImpl implements CharacterService {

  private LocationService locationService;
  private CharacterRepository characterRepository;

  @Override
  public CharacterDto read(Long characterId) {
    Character one = characterRepository.getOne(characterId.longValue());
    return getCharacterDto(one);
  }

  private CharacterDto getCharacterDto(Character character) {
    CharacterDto c = new CharacterDto();
    c.setLocationId(character.getLocation().getLocationId());
    c.setName(character.getName());
    return c;
  }

  @Override
  @Transactional
  public void create(CharacterDto character) {
    Character newCharacter = new Character();
    newCharacter.setLocation(locationService.readEntity(character.getLocationId()));
    newCharacter.setName(character.getName());
    characterRepository.save(newCharacter);

  }

  @Override
  public Page<CharacterDto> list(PageDto characterPageDto) {
    Pageable pageable = PageRequest
        .of(characterPageDto.getPageNum(), characterPageDto.getPageSize(),
            Sort.by(Order.asc(Objects.isNull(characterPageDto.getSortField()) ? "name"
                : characterPageDto.getSortField())));
    return buildCharacterList(characterRepository.findAll(pageable), pageable);
  }

  @Override
  public Character readEntity(Long id) {
    return characterRepository.getOne(id.longValue());
  }

    @Override
    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    private Page<CharacterDto> buildCharacterList(Page<Character> all, Pageable pageable) {
    List<CharacterDto> characterDtoList = new ArrayList<>();
    for (Character c : all.getContent()) {
      CharacterDto characterDto = new CharacterDto();
      characterDto.setName(c.getName());
      characterDto.setLocationId(c.getLocation().getLocationId());
      characterDtoList.add(characterDto);
    }
    return new PageImpl<>(characterDtoList, pageable, characterDtoList.size());
  }

  @Autowired
  public void setCharacterRepository(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  @Autowired
  public void setLocationService(LocationService locationService) {
    this.locationService = locationService;
  }
}
