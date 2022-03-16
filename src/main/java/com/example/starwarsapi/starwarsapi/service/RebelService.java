package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.dto.RequestRebel;
import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Location;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class RebelService {

    Random random = new Random();

    public Rebel registerRebel(RequestRebel requestRebel) {
        InventoryService inventoryService = new InventoryService();
        Inventory inventory = inventoryService.createInventory();
        Location location = new Location(requestRebel.getGalaxy());

        Rebel rebel = new Rebel(
                UUID.randomUUID(),
                requestRebel.getName(),
                requestRebel.getAge(),
                requestRebel.getGenre(),
                location,
                inventory,
                0
        );

        StarwarsapiApplication.listRebels.addRebel(rebel);
        return rebel;
    }

    public List<Rebel> getAllRebels(){
        return StarwarsapiApplication.listRebels.searchRebels();
    }

    public Location reportLocation(UUID id) throws Exception {
        Rebel rebel = StarwarsapiApplication.listRebels.detailsRebel(id);
        String galaxy = rebel.getLocation().getGalaxy();
        rebel.setLocation(new Location(galaxy));
        return rebel.getLocation();
    }

    public Rebel reportRebel(UUID id) throws Exception {
        Rebel rebel = StarwarsapiApplication.listRebels.detailsRebel(id);
        int actualCount = rebel.getReportCount();
        rebel.setReportCount(actualCount + 1);

        return rebel;
    }

}
