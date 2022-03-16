package com.example.starwarsapi.starwarsapi.dto;

import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import com.example.starwarsapi.starwarsapi.model.Trade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor
public class ResponseTrade {
    private UUID tradeId;
    private UUID fromId;
    private UUID toId;
    private Inventory inventory;
    private int total;

    public ResponseTrade(RequestTrade trade) {
        this.tradeId = trade.getTradeId();
        this.fromId = trade.getFromId();
        this.toId = trade.getToId();
        this.inventory = trade.getInventory();
        this.total = trade.getTotal();
    }

    public static List<ResponseTrade> toResponse(List<RequestTrade> trade){
        return  trade.stream().map(ResponseTrade::new).collect(Collectors.toList());
    }
}
