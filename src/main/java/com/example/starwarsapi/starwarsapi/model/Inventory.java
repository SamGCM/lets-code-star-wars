package com.example.starwarsapi.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Inventory {
    private UUID id;
    private int weapon;
    private int ammo;
    private int water;
    private int food;

    public static Integer getTotal(Inventory inventory){
        int ammoTotal = inventory.ammo * ItemValues.AMMO.getValue();
        int foodTotal = inventory.food * ItemValues.FOOD.getValue();
        int waterTotal = inventory.water * ItemValues.WATER.getValue();
        int weaponTotal = inventory.weapon * ItemValues.WEAPON.getValue();

        return ammoTotal + foodTotal + waterTotal + weaponTotal;
    }

}
