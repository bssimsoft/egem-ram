package com.egem.ram.controller;

import com.egem.ram.dto.LocationDto;
import com.egem.ram.dto.PageDto;
import com.egem.ram.dto.SuccessResponse;
import com.egem.ram.serviceapi.LocationService;
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
 * Lokasyon objesi üzerinde işlemlerin yapıldığı controller katmanıdır.
 */
@RestController
@RequestMapping(value = "rest/location")
public class LocationController {

  private LocationService locationService;

  /**
   * Lokasyon id bilgisi ile bir episode objesi getiren methoddur.
   * @param characterId
   * @return
   */
  @GetMapping("/{locationId}")
  public ResponseEntity<LocationDto> read(@PathVariable Long characterId) {
    return new ResponseEntity<>(locationService.read(characterId), HttpStatus.OK);
  }

  /**
   * Bir lokasyon objesi oluşturan methoddur.
   * @param location
   * @return
   */
  @PostMapping("/create")
  public ResponseEntity<SuccessResponse> create(@RequestBody LocationDto location) {
    locationService.create(location);
    return new ResponseEntity<>(
        new SuccessResponse("Lokasyon oluşturma", "Lokasyon başarılı bir şekilde oluşturuldu."),
        HttpStatus.OK);
  }

  /**
   * Lokasyon bilgilerini sayfalı bir şekilde dönen methoddur.
   * @param locationPageDto
   * @return
   */
  @PostMapping("/list")
  public ResponseEntity<Page<LocationDto>> create(@RequestBody PageDto locationPageDto) {
    return new ResponseEntity<>(locationService.list(locationPageDto), HttpStatus.OK);
  }

  @Autowired
  public void setLocationService(LocationService locationService) {
    this.locationService = locationService;
  }
}
