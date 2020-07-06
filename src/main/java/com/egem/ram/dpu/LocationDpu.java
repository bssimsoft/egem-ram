package com.egem.ram.dpu;

import com.egem.ram.config.DpuStarterEvent;
import com.egem.ram.dao.LocationRepository;
import com.egem.ram.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class LocationDpu implements ApplicationListener<DpuStarterEvent> {

  private LocationRepository locationRepository;

  @Override
  public void onApplicationEvent(DpuStarterEvent event) {
    createData();
  }

  private void createData() {
    for (int i = 0; i < 100; i++) {
      Location location = new Location();
      location.setName("test" + i);
      locationRepository.save(location);
    }
  }

  @Autowired
  public void setLocationRepository(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }
}
