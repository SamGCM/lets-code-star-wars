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
    private UUID rebelTrader1;
    private UUID rebelTrader2;
    private Inventory itemForTradeRebel1;
    private Inventory itemForTradeRebel2;

    public ResponseTrade(RequestTrade trade) {
        this.rebelTrader1 = trade.getRebelTrader1();
        this.rebelTrader2 = trade.getRebelTrader2();
        this.itemForTradeRebel1 = trade.getItemForTradeRebel1();
        this.itemForTradeRebel2 = trade.getItemForTradeRebel2();
    }

    public static List<ResponseTrade> toResponse(List<RequestTrade> trade){
        return  trade.stream().map(ResponseTrade::new).collect(Collectors.toList());
    }
}
