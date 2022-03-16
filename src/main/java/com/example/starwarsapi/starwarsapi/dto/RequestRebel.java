package com.example.starwarsapi.starwarsapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class RequestRebel {
    @NotNull(message = "O campo 'name' não pode ser null.")
    @NotEmpty(message = "O campo 'name' não pode estar vazio")
    @Length(min = 2)
    String name;


    int age;

    @NotNull(message = "O campo 'genre' não pode ser null.")
    @NotEmpty(message = "O campo 'genre' não pode estar vazio")
    @Length(min = 5)
    String genre;

    @NotNull(message = "O campo 'galaxy' não pode ser null.")
    @NotEmpty(message = "O campo 'galaxy' não pode estar vazio")
    @Length(min = 2)
    String galaxy;
}
