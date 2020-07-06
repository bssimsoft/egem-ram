package com.egem.ram.serviceapi;

import com.egem.ram.domain.Character;
import com.egem.ram.dto.CharacterDto;
import com.egem.ram.dto.PageDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CharacterService {

  CharacterDto read(Long characterId);

  void create(CharacterDto character);

  Page<CharacterDto> list(PageDto characterPageDto);

  Character readEntity(Long id);

  List<Character> findAll();
}
