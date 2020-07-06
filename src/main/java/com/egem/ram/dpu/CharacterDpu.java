package com.egem.ram.dpu;

import com.egem.ram.config.DpuStarterEvent;
import com.egem.ram.dao.CharacterRepository;
import com.egem.ram.dao.LocationRepository;
import com.egem.ram.domain.Character;
import com.egem.ram.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Order(3)
public class CharacterDpu implements ApplicationListener<DpuStarterEvent> {

    private LocationRepository locationRepository;
    private CharacterRepository characterRepository;

    @Override
    public void onApplicationEvent(DpuStarterEvent event) {
        createData();
    }

    private void createData() {

        List<Location> locationList = locationRepository.findAll();
        for (int i = 0; i < 10; i++) {
            Random rn = new Random();
            Character character = new Character();
            character.setName("Character" + i);
            character.setLocation(locationList.get(rn.nextInt(locationList.size() - 1)));
            characterRepository.save(character);
        }
    }

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Autowired
    public void setCharacterRepository(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
}
