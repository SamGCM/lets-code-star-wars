package com.example.starwarsapi.starwarsapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorValidation {
    private String field;
    private String message;
}
