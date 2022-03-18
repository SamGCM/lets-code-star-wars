package com.example.starwarsapi.starwarsapi.dto;

import com.example.starwarsapi.starwarsapi.model.Location;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor
public class ResponseReportRebel {
    private UUID id;
    private String name;
    private String genre;
    private List<UUID> voteForTraitor;

    public ResponseReportRebel(Rebel rebel){
        this.id = rebel.getId();
        this.name = rebel.getName();
        this.genre = rebel.getGenre();
        this.voteForTraitor = rebel.getVoteForTraitor();
    }

    public static List<ResponseReportRebel> toResponse(List<Rebel> rebel){
        return  rebel.stream().map(ResponseReportRebel::new).collect(Collectors.toList());
    }
}
