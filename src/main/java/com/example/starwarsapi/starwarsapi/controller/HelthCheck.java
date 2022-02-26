package com.example.starwarsapi.starwarsapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelthCheck {
    @GetMapping("/health-check")
    @ResponseBody
    public String healthCheck(){
        return "Server running!";
    }
}
