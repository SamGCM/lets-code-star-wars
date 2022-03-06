package com.example.starwarsapi.starwarsapi.dto;

import com.example.starwarsapi.starwarsapi.model.Location;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ResponseLocation {
    private int lon;
    private int lat;
    private String galaxy;

    public ResponseLocation(Location location) {
        this.galaxy = location.getGalaxy();
        this.lat = location.getLat();
        this.lon  = location.getLon();
    }


    public static List<ResponseLocation> toResponse(List<Location> location){
        return  location.stream().map(ResponseLocation::new).collect(Collectors.toList());
    }
}
