package com.example.starwarsapi.starwarsapi.controller;

import com.example.starwarsapi.starwarsapi.dto.RequestRebel;
import com.example.starwarsapi.starwarsapi.dto.ResponseLocation;
import com.example.starwarsapi.starwarsapi.dto.ResponseRebel;
import com.example.starwarsapi.starwarsapi.dto.ResponseReportRebel;
import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import com.example.starwarsapi.starwarsapi.service.DataService;
import com.example.starwarsapi.starwarsapi.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/rebelde")
public class RebelController {

    @Autowired
    RebelService rebelService;

    @GetMapping
    public List<ResponseRebel> rebels(){
        return ResponseRebel.toResponse(rebelService.getAllRebels());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseRebel> rebelsDetails(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(new ResponseRebel(rebelService.findRebel(id)));
    }


    @PostMapping
    public ResponseEntity<ResponseRebel> addRebel(
            @RequestBody @Valid RequestRebel requestRebel,
            UriComponentsBuilder uriComponentsBuilder) {
        Rebel rebel = rebelService.registerRebel(requestRebel);
        URI uri = uriComponentsBuilder.path("/rebelde/{id}").buildAndExpand(rebel.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseRebel(rebel));
    }

    @GetMapping("{id}/localizacao")
    public ResponseEntity<ResponseLocation> rebelLocation(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(new ResponseLocation(rebelService.reportLocation(id)));
    }

    @PatchMapping("/reportar-traidor/{id}")
    public ResponseEntity<ResponseRebel> reportTraitor(@PathVariable UUID id, @RequestBody Map<String,String> whoReportId) throws Exception {
        UUID whoReportUUID = UUID.fromString(whoReportId.get("whoReportId"));
        return ResponseEntity.ok( new ResponseRebel(rebelService.voteTraitor(id, whoReportUUID)));
    }

    @GetMapping("relatorio")
    public ResponseEntity<Object> generateFile() throws Exception {
        DataService dataService = new DataService();
        return dataService.generateFile();
    }

}
