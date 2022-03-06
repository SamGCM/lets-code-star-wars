package com.example.starwarsapi.starwarsapi;

import com.example.starwarsapi.starwarsapi.model.ListRebels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarwarsapiApplication {
	public static ListRebels listRebels = new ListRebels();
	public static void main(String[] args) {
		SpringApplication.run(StarwarsapiApplication.class, args);
	}

}
