package com.example.starwarsapi.starwarsapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Rebel {

    @Autowired
    MockMvc mockMvc;

    @Test
    void registerRebelSuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/rebelde")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\":\"Mandaloriano\",\n" +
                                "\t\"age\":\"35\",\n" +
                                "\t\"genre\":\"Masculino\",\n" +
                                "\t\"galaxy\":\"Via láctea\",\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.name").isNotEmpty())
        .andExpect(jsonPath("$.age").isNotEmpty())
        .andExpect(jsonPath("$.genre").value(true));
    }
}
