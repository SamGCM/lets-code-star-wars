package com.example.starwarsapi.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class Trade {
    private UUID rebelTrader1;
    private UUID rebelTrader2;
    private Inventory itemForTradeRebel1;
    private Inventory itemForTradeRebel2;
}
