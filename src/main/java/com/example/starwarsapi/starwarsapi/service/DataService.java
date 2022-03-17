package com.example.starwarsapi.starwarsapi.service;

import com.example.starwarsapi.starwarsapi.StarwarsapiApplication;
import com.example.starwarsapi.starwarsapi.model.Inventory;
import com.example.starwarsapi.starwarsapi.model.Rebel;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class DataService {

    List<String[]> datas = new ArrayList<>();
    List<Rebel> list = StarwarsapiApplication.listRebels.searchRebels();

    public ResponseEntity<Object> generateFile() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        Inventory resourceAverage = resourceAverage();
        Writer writer = null;
        try{
            String[] header = {"Relatorio", "Dados"};
            datas.add(new String[]{"Porcentagem de traidores", String.valueOf(getPercentageTraitor()) + "%"});
            datas.add(new String[]{"Porcentagem de rebeldes", String.valueOf(100.0 - getPercentageTraitor()) + "%"});
            datas.add(new String[]{"Media de itens por rebelde",
                    resourceAverage.getFood() + " comidas por rebelde," +
                            resourceAverage.getWeapon() + " armas por rebelde," +
                            resourceAverage.getAmmo() + " municoes por rebelde," +
                            resourceAverage.getWater() + " aguas por rebelde,"
            });
            datas.add(new String[]{"Pontos perdidos devido a traidores", String.valueOf(lostPoints())});

            writer = Files.newBufferedWriter(Paths.get("relatorio.csv"));
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeNext(header);
            csvWriter.writeAll(datas);

            writer.flush();

            String filename = ".\\relatorio.csv";

            File file = new File(filename);
            InputStreamResource resource =  new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.set("headerName", "headerValue");

            HttpEntity entity = new HttpEntity(headers);
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            ResponseEntity<Object> response = ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/txt"))
                    .body(resource);
            return response;
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }

    private float getPercentageTraitor(){
        float percentage = 0;
        int traitorsCount = 0;
        int total = list.size();

        for (Rebel rebel : list ) {
            if(rebel.getReportCount() >= 3){
                traitorsCount++;
            }
        }

        if(traitorsCount != 0) {
            percentage = (float) ((traitorsCount * 100) / total);
        }
        return  percentage;
    }

    private int lostPoints(){
        int points = 0;

        for (Rebel rebel : list ) {
            if (rebel.getReportCount() >= 3){
                points = points + Inventory.getTotal(rebel.getInventory());
            }
        }
        return points;
    }

    private Inventory resourceAverage(){
        int traitorsCount = 0;
        int weapon = 0;
        int water = 0;
        int ammo = 0;
        int food = 0;

        for (Rebel rebel : list ) {
            if(rebel.getReportCount() < 3){
                weapon = weapon + rebel.getInventory().getWeapon();
                water = water + rebel.getInventory().getWater();
                ammo = ammo + rebel.getInventory().getAmmo();
                food = food + rebel.getInventory().getFood();
            } else if (rebel.getReportCount() >= 3){
                traitorsCount++;
            }
        }

        return new Inventory(
            UUID.randomUUID(),
            weapon / (list.size() - traitorsCount),
            water / (list.size() - traitorsCount),
            ammo / (list.size() - traitorsCount),
            food / (list.size() - traitorsCount)
        );
    }


}
