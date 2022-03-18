package com.example.starwarsapi.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter @Getter @AllArgsConstructor
public class Rebel {
    private UUID id;
    private String name;
    private int age;
    private String genre;
    private Location location;
    private Inventory inventory;
    private List<UUID> voteForTraitor;
}
