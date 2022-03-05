package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.model.Inventory;

import java.util.Random;
import java.util.UUID;

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
}
