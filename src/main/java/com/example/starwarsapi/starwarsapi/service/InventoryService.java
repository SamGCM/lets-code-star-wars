package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.dto.RequestTrade;
import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class InventoryService {
    Random random = new Random();

//    GERA UM INVENTÁRIO COM NÚMERO ALEATÓRIOS DE ITENS, DE 0 A 10
    public Inventory createInventory (){
        Inventory inventory = new Inventory();
        inventory.setId(UUID.randomUUID());
        inventory.setAmmo(random.nextInt(10));
        inventory.setFood(random.nextInt(10));
        inventory.setWater(random.nextInt(10));
        inventory.setWeapon(random.nextInt(10));
        return inventory;
    }

    public RequestTrade tradeItems (RequestTrade requestTrade) throws Exception {
        Rebel rebelReceiving =  StarwarsapiApplication.listRebels.detailsRebel(requestTrade.getToId());
        Rebel rebelSending = StarwarsapiApplication.listRebels.detailsRebel(requestTrade.getFromId());
        Inventory requestInventory = requestTrade.getInventory();
        Inventory targetInventory = rebelReceiving.getInventory();
        Inventory fromInventory = rebelSending.getInventory();

        if(verifyIsTraitor(rebelReceiving, rebelSending)) {
            try {
                throw new Exception("Um dos rebeldes é um traidor! Não pode negociar.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(verifyBalance(requestInventory, targetInventory)){
            rebelSending.setInventory(removeItemsFromInventory(fromInventory, requestInventory));
            rebelReceiving.setInventory(includeItemsNewInventory(requestInventory, targetInventory));
        } else {
            try {
                throw new Exception("Um dos rebeldes não tem itens suficientes para negociar.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return requestTrade;
    }

    private boolean verifyIsTraitor(Rebel rebelReceiving, Rebel rebelSending ){
        if(rebelReceiving.getReportCount() >= 3){
            return true;
        }
        else if(rebelSending.getReportCount() >= 3){
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyBalance(Inventory match, Inventory target){
        int inventoryMatch = Inventory.getTotal(match);
        int inventoryTarget = Inventory.getTotal(target);

        return inventoryMatch <= inventoryTarget;
    }

    private Inventory includeItemsNewInventory(Inventory match, Inventory target){
        Inventory inventory = new Inventory(
                target.getId(),
                match.getWeapon() + target.getWeapon(),
                match.getAmmo() + target.getAmmo(),
                match.getWater() + target.getWater(),
                match.getFood() + target.getFood()
        );

        return inventory;
    }

    private Inventory removeItemsFromInventory(Inventory inventory, Inventory requestInventory) {
        Inventory inventoryForReturn = new Inventory(
                inventory.getId(),
                inventory.getWeapon() - requestInventory.getWeapon(),
                inventory.getAmmo() - requestInventory.getAmmo(),
                inventory.getWater() - requestInventory.getWater(),
                inventory.getFood() - requestInventory.getFood()
        );

        return inventoryForReturn;
    }
}
