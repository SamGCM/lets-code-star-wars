package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.dto.RequestRebel;
import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Location;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Rebel getRebel(UUID rebelId) throws Exception {
        return StarwarsapiApplication.listRebels.detailsRebel(rebelId);
    }

    public Location reportLocation(UUID id) throws Exception {
        Rebel rebel = StarwarsapiApplication.listRebels.detailsRebel(id);
        String galaxy = rebel.getLocation().getGalaxy();
        rebel.setLocation(new Location(galaxy));
        return rebel.getLocation();
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
