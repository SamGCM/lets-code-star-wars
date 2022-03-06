package com.example.starwarsapi.starwarsapi.dto;

import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Location;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ResponseRebel {
    private UUID id;
    private String name;
    private int age;
    private String genre;
    private Location location;
    private Inventory inventory;

    public ResponseRebel (Rebel rebel) {
        this.id = rebel.getId();
        this.name = rebel.getName();
        this.age = rebel.getAge();
        this.genre = rebel.getGenre();
        this.location = rebel.getLocation();
        this.inventory = rebel.getInventory();
    }


    public static List<ResponseRebel> toResponse(List<Rebel> rebels){
        return  rebels.stream().map(ResponseRebel::new).collect(Collectors.toList());
    }
}
