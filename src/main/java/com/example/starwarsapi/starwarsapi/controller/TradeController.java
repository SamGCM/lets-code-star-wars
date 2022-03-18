package com.example.starwarsapi.starwarsapi.controller;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.dto.RequestTrade;
import com.example.starwarsapi.starwarsapi.dto.ResponseTrade;
import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;
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
    ) throws NotFoundException {
        InventoryService service = new InventoryService();

        return ResponseEntity.ok(new ResponseTrade(service.tradeItems(requestTrade)));
    }
}
