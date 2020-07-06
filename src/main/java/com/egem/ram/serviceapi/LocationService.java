package com.egem.ram.serviceapi;

import com.egem.ram.domain.Location;
import com.egem.ram.dto.LocationDto;
import com.egem.ram.dto.PageDto;
import org.springframework.data.domain.Page;

public interface LocationService {

  LocationDto read(Long locationId);

  void create(LocationDto location);

  Page<LocationDto> list(PageDto locationPageDto);

  Location readEntity(Long locationId);
}
