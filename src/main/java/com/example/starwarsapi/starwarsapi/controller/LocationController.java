package com.example.starwarsapi.starwarsapi.controller;

import com.example.starwarsapi.starwarsapi.model.Location;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LocationController {
    //CLASS APENAS PARA DEMONSTRAÇÃO DO GET DA LOCALIZAÇÃO
    @GetMapping("/coordenadas")
    @ResponseBody @ResponseStatus
    public String coordinates(){
        Location location = new Location("Via Láctea");

        return location.reportCoordinates();
    }
}
