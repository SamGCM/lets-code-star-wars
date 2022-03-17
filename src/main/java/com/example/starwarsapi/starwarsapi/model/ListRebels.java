package com.example.starwarsapi.starwarsapi.model;

import com.example.starwarsapi.starwarsapi.dto.RequestRebel;
import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;

import java.util.*;

public class ListRebels {
    private static List<Rebel> rebels = new ArrayList<>();

    public void addRebel(Rebel rebel){
        ListRebels.rebels.add(rebel);
    }

    public List<Rebel> searchRebels(){
        return ListRebels.rebels;
    }

    public Optional<Rebel> detailsRebel(UUID id) {
        return ListRebels.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();
    }
}
