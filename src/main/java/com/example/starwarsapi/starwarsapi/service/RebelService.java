package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.config.ErrorHandle;
import com.example.starwarsapi.starwarsapi.dto.RequestRebel;
import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;
import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Location;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class RebelService {

    Random random = new Random();

    public Rebel registerRebel(RequestRebel requestRebel) {
        InventoryService inventoryService = new InventoryService();
        Inventory inventory = inventoryService.createInventory();
        Location location = new Location(requestRebel.getGalaxy());
        List<UUID> voteForTraitor = new ArrayList<>();

        Rebel rebel = new Rebel(
                UUID.randomUUID(),
                requestRebel.getName(),
                requestRebel.getAge(),
                requestRebel.getGenre(),
                location,
                inventory,
                voteForTraitor
        );

        StarwarsapiApplication.listRebels.addRebel(rebel);
        return rebel;
    }

    public List<Rebel> getAllRebels(){
        return StarwarsapiApplication.listRebels.searchRebels();
    }

    public Rebel findRebel(UUID id) throws NotFoundException {
        Optional<Rebel> result = StarwarsapiApplication.listRebels.detailsRebel(id);
        return result.get();
    }

    public Location reportLocation(UUID id) throws NotFoundException {
        Optional<Rebel> rebel = StarwarsapiApplication.listRebels.detailsRebel(id);
        String galaxy = rebel.get().getLocation().getGalaxy();
        rebel.get().setLocation(new Location(galaxy));
        return rebel.get().getLocation();
    }

    public Rebel reportRebel(UUID id) throws NotFoundException {
        Optional<Rebel> rebel = StarwarsapiApplication.listRebels.detailsRebel(id);
        int actualCount = rebel.get().getReportCount();
        rebel.get().setReportCount(actualCount + 1);

        return rebel.get();
    }

    public Rebel voteTraitor(UUID traitorId, UUID whoReportId) throws Exception {
        Rebel rebelTraitor = getRebel(traitorId);
        rebelTraitor.getVoteForTraitor().add(whoReportId);
        return rebelTraitor;
    }

    public Boolean isTraitor(UUID id) throws Exception {
        Rebel rebel = getRebel(id);
        return rebel.getVoteForTraitor().size() >= 3;
    }

}
