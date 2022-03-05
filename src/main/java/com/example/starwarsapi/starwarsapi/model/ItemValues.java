package com.example.starwarsapi.starwarsapi.model;

import lombok.Getter;

@Getter
public enum ItemValues {
    WEAPON(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    private int value;
    ItemValues(int value){
        this.value = value;
    }

    //  ENUM COM VALORES DOS ITENS, PARA PEGAR O VALOR É SÓ USAR
    //  ItemValues.FOOD.getValue() E RETORNA O VALOR DA COMIDA

}
