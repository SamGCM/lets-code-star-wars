package com.example.starwarsapi.starwarsapi.dto;

import com.example.starwarsapi.starwarsapi.model.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter @Setter
public class RequestTrade {
    private UUID rebelTrader1;
    private UUID rebelTrader2;
    private Inventory itemForTradeRebel1;
    private Inventory itemForTradeRebel2;

    Random random = new Random();

    public RequestTrade (UUID traderId1, UUID traderId2, Inventory traderItens1, Inventory traderItens2) {
        this.rebelTrader1 = traderId1;
        this.rebelTrader2 = traderId2;
        this.itemForTradeRebel1 = traderItens1;
        this.itemForTradeRebel2 = traderItens2;
    }
}
