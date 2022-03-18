package com.example.starwarsapi.starwarsapi.model;

import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
@Slf4j
public class ListRebels {
    private static List<Rebel> rebels = new ArrayList<>();

    public void addRebel(Rebel rebel){
        ListRebels.rebels.add(rebel);
    }

    public List<Rebel> searchRebels(){
        return ListRebels.rebels;
    }

    public Optional<Rebel> detailsRebel(UUID id) throws NotFoundException {
        Optional<Rebel> result = ListRebels.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();
        if(result.isPresent()) {
            return result;
        } else {
            log.error("Rebelde nao foi encontrado "+ id);
            throw new NotFoundException("Rebelde nao encontrado " + id);
        }
    }
}
