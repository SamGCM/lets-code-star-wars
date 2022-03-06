package com.example.starwarsapi.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter @Getter
public class Location {
    private int lat;
    private int lon;
    private String galaxy;

    public Location (String galaxy) {
        this.lat = generateCoordinates();
        this.lon = generateCoordinates();
        this.galaxy = galaxy;
    }

    public String reportCoordinates(){
        int latitude = generateCoordinates();
        int longitude = generateCoordinates();

        return "Estou na " + this.galaxy + ", coordenadas " + latitude + ", " + longitude;
    }

    private Integer generateCoordinates(){
        Random gerador = new Random();
        int coordinates = 0;

        //imprime sequência de 10 números inteiros aleatórios
        for (int i = 0; i < 5; i++) {
            coordinates = gerador.nextInt();
        }

        return coordinates;
    }
}
