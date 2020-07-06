package com.egem.ram.controller;

import com.egem.ram.dto.CharacterDto;
import com.egem.ram.dto.PageDto;
import com.egem.ram.dto.SuccessResponse;
import com.egem.ram.serviceapi.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Charackter objesi üzerinde işlemlerin yapıldığı controller katmanıdır.
 */
@RestController
@RequestMapping("rest/character")
public class CharacterController {

  private CharacterService characterService;

  /**
   * Karakter id bilgisi ile bir karakter objesi getiren methoddur.
   * @param characterId
   * @return
   */
  @GetMapping("/{characterId}")
  public ResponseEntity<CharacterDto> read(@PathVariable Long characterId) {
    return new ResponseEntity<>(characterService.read(characterId), HttpStatus.OK);
  }

  /**
   * Bir karakter objesi oluşturan methoddur.
   * @param character
   * @return
   */
  @PostMapping("/create")
  public ResponseEntity<SuccessResponse> create(@RequestBody CharacterDto character) {
    characterService.create(character);
    return new ResponseEntity<>(
        new SuccessResponse("Karakter oluşturma", "Karakter başarılı bir şekilde oluşturuldu."),
        HttpStatus.OK);
  }

  /**
   * Karakterleri sayfalı bir şekilde dönen methoddur.
   * @param characterPageDto
   * @return
   */
  @PostMapping("/list")
  public ResponseEntity<Page<CharacterDto>> list(@RequestBody PageDto characterPageDto) {
    return new ResponseEntity<>(characterService.list(characterPageDto), HttpStatus.OK);
  }

  @Autowired
  public void setCharacterService(CharacterService characterService) {
    this.characterService = characterService;
  }

}
