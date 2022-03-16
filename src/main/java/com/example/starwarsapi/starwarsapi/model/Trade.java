package com.example.starwarsapi.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class Trade {
    private UUID tradeId;
    private UUID fromId;
    private UUID toId;
    private Inventory inventory;
    private int total;
}
