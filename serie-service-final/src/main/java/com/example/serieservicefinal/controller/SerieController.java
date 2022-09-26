package com.example.serieservicefinal.controller;

import com.example.serieservicefinal.entities.Serie;
import com.example.serieservicefinal.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre) {
        List<Serie> series = serieService.getListByGenre(genre);
        return series.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(series, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Serie> insertSerie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(serieService.insertSerie(serie));
    }
}
