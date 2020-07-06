package com.egem.ram.service;

import com.egem.ram.dao.LocationRepository;
import com.egem.ram.domain.Location;
import com.egem.ram.dto.LocationDto;
import com.egem.ram.dto.PageDto;
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
public class LocationServiceImpl implements LocationService {

  private LocationRepository locationRepository;

  @Override
  public LocationDto read(Long locationId) {
    return getLocDto(locationRepository.getOne(locationId.longValue()));
  }

  private LocationDto getLocDto(Location one) {
    LocationDto locationDto = new LocationDto();
    locationDto.setName(one.getName());
    return locationDto;
  }

  @Override
  @Transactional
  public void create(LocationDto location) {
    Location lo = new Location();
    lo.setName(location.getName());
    locationRepository.save(lo);
  }

  @Override
  public Page<LocationDto> list(PageDto locationPageDto) {
    Pageable pageable = PageRequest.of(locationPageDto.getPageNum(), locationPageDto.getPageSize(),
        Sort.by(Order.asc(Objects.isNull(locationPageDto.getSortField()) ? "name"
            : locationPageDto.getSortField())));
    return getLocationDtoList(locationRepository.findAll(pageable), pageable);
  }

  @Override
  public Location readEntity(Long locationId) {
    return locationRepository.getOne(locationId.longValue());
  }

  private Page<LocationDto> getLocationDtoList(Page<Location> all, Pageable pageable) {
    List<LocationDto> locationDtos = new ArrayList<>();
    for(Location location : all.getContent()){
      LocationDto locationDto = new LocationDto();
      locationDto.setName(location.getName());
      locationDtos.add(locationDto);
    }
    return new PageImpl<>(locationDtos, pageable, locationDtos.size());
  }

  @Autowired
  public void setLocationRepository(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }
}
