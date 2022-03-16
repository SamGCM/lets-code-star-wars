package com.example.starwarsapi.starwarsapi.model;

import com.example.starwarsapi.starwarsapi.dto.RequestRebel;

import java.util.*;

public class ListRebels {
    private static List<Rebel> rebels = new ArrayList<>();

    public void addRebel(Rebel rebel){
        ListRebels.rebels.add(rebel);
    }

    public List<Rebel> searchRebels(){
        return ListRebels.rebels;
    }

    public Rebel detailsRebel(UUID id) throws Exception {
        Optional<Rebel> resultRebel =
                ListRebels.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();
        if(resultRebel.isPresent()){
            return resultRebel.get();
        } else {
            throw new Exception("Rebelde não encontrado.");
        }
    }

//    public Rebel updateRebel(UUID id, RequestRebel requestRebel) throws Exception {
//        ListRebels.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id))
//                .forEach(rebel -> {
//                    rebel.setInventory(requestRebel.);
//                });
//        return detailsRebel(id);
//    }
}
