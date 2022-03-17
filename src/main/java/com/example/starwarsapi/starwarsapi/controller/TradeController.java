package com.example.starwarsapi.starwarsapi.controller;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.dto.RequestTrade;
import com.example.starwarsapi.starwarsapi.dto.ResponseTrade;
import com.example.starwarsapi.starwarsapi.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/negociar")
public class TradeController {

    @PatchMapping
    public ResponseEntity<ResponseTrade> trocar(
            @RequestBody RequestTrade requestTrade
    ) throws Exception {
        InventoryService service = new InventoryService();
        service.tradeItems(requestTrade);
        return ResponseEntity.ok(new ResponseTrade(requestTrade));
    }
}
