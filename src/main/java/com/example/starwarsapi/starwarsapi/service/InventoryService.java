package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.config.ErrorHandle;
import com.example.starwarsapi.starwarsapi.dto.RequestTrade;
import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;
import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class InventoryService {
    Random random = new Random();
    ErrorHandle errorHandler = new ErrorHandle();


//    GERA UM INVENTÁRIO COM NÚMERO ALEATÓRIOS DE ITENS, DE 0 A 10
    public Inventory createInventory (){
        Inventory inventory = new Inventory();
        inventory.setId(UUID.randomUUID());
        inventory.setAmmo(random.nextInt(10));
        inventory.setFood(random.nextInt(10));
        inventory.setWater(random.nextInt(10));
        inventory.setWeapon(random.nextInt(10));
        return inventory;
    }

    public String tradeItems(RequestTrade requestTrade) throws NotFoundException {
        Optional<Rebel> Trader1 =  StarwarsapiApplication.listRebels.detailsRebel(requestTrade.getRebelTrader1());
        Optional<Rebel> Trader2 = StarwarsapiApplication.listRebels.detailsRebel(requestTrade.getRebelTrader2());
        Inventory itensForTrade1 = requestTrade.getItemForTradeRebel1();
        Inventory itensForTrade2 = requestTrade.getItemForTradeRebel2();

        if(verifyIsTraitor(Trader1.get(), Trader2.get())) {
            log.error("Um dos rebeldes é um traidor! Não pode negociar.");
            NotFoundException notFoundException = new NotFoundException("Um dos rebeldes é um traidor! Não pode negociar.");
            errorHandler.handlerNotFound(notFoundException);
        }

        if(verifyBalance(itensForTrade1, itensForTrade2) && verifyHaveAllItems(Trader1.get(), itensForTrade1) && verifyHaveAllItems(Trader2.get(), itensForTrade2)){

            Trader1.get().setInventory(removeItemsFromInventory(Trader1.get().getInventory(), itensForTrade1));
            Trader2.get().setInventory(removeItemsFromInventory(Trader2.get().getInventory(), itensForTrade2));

            Trader1.get().setInventory(includeItemsNewInventory(Trader1.get().getInventory(), itensForTrade2));
            Trader2.get().setInventory(includeItemsNewInventory(Trader2.get().getInventory(), itensForTrade1));

        } else {
            log.error("Um dos rebels não tem itens suficientes para negociar.");
            NotFoundException notFoundException = new NotFoundException("Um dos rebels não tem itens suficientes para negociar.");
            errorHandler.handlerNotFound(notFoundException);
        }
        log.info("Negociação realizada com sucesso");
        return "Negociação realizada com sucesso";
    }

    private boolean verifyIsTraitor(Rebel trader1, Rebel trader2 ){
        if(trader1.getReportCount() >= 3){
            return true;
        }
        else if(trader2.getReportCount() >= 3){
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyBalance(Inventory itemsForTrade1, Inventory itemsForTrade2){
        int totalPointsTrader1 = Inventory.getTotal(itemsForTrade1);
        int totalPointsTrader2 = Inventory.getTotal(itemsForTrade2);

        return totalPointsTrader1 == totalPointsTrader2;
    }

    private boolean verifyHaveAllItems(Rebel trader, Inventory itemsForTrade){
        return trader.getInventory().getAmmo() >= itemsForTrade.getAmmo() &&
                trader.getInventory().getFood() >= itemsForTrade.getFood() &&
                trader.getInventory().getWeapon() >= itemsForTrade.getWeapon() &&
                trader.getInventory().getWater() >= itemsForTrade.getWater();
    }

    private Inventory includeItemsNewInventory(Inventory traderInventory, Inventory exchangedItems){

        return new Inventory(
                traderInventory.getId(),
                traderInventory.getWeapon() + exchangedItems.getWeapon(),
                traderInventory.getAmmo() + exchangedItems.getAmmo(),
                traderInventory.getWater() + exchangedItems.getWater(),
                traderInventory.getFood() + exchangedItems.getFood()
        );
    }

    private Inventory removeItemsFromInventory(Inventory traderInventory, Inventory itemsForExchange) {

        return new Inventory(
                traderInventory.getId(),
                traderInventory.getWeapon() - itemsForExchange.getWeapon(),
                traderInventory.getAmmo() - itemsForExchange.getAmmo(),
                traderInventory.getWater() - itemsForExchange.getWater(),
                traderInventory.getFood() - itemsForExchange.getFood()
        );
    }
}
