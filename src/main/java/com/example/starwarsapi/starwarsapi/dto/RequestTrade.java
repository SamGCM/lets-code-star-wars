package com.example.starwarsapi.starwarsapi.dto;

import com.example.starwarsapi.starwarsapi.model.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter @Setter
public class RequestTrade {
    private UUID tradeId;
    private UUID fromId;
    private UUID toId;
    private Inventory inventory;
    private int total;

    Random random = new Random();

    public RequestTrade (UUID toId, UUID fromId, Inventory inventory) {
        this.tradeId = UUID.randomUUID();
        this.fromId = fromId;
        this.toId = toId;
        this.inventory = inventory;
        this.total = Inventory.getTotal(inventory);
    }
}
