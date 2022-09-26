package com.example.catalogservicefinal.controller;

import com.example.catalogservicefinal.dto.CatalogDTO;
import com.example.catalogservicefinal.dto.MovieDTO;
import com.example.catalogservicefinal.dto.SerieDTO;
import com.example.catalogservicefinal.entity.Catalog;
import com.example.catalogservicefinal.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    //metodo del parcial...
    //@GetMapping("/{genre}")
    //public ResponseEntity<List<MovieDTO>> getCatalogByGenre(@PathVariable("genre") String genre) {
    //    List<MovieDTO> catalogByGenre = catalogService.getCatalogByGenre(genre);
    //    return Objects.isNull(catalogByGenre)
    //            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
    //            : new ResponseEntity<>(catalogByGenre, HttpStatus.OK);
    //}

    @GetMapping("/{genre}")
    public ResponseEntity<CatalogDTO> getCatalogByGenre(@PathVariable String genre) {
        CatalogDTO catalogByGenre = catalogService.getCatalogByGenre(genre);
        return Objects.isNull(catalogByGenre)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(catalogByGenre, HttpStatus.OK);
    }

    @PostMapping("/save/{genre}")
    public ResponseEntity<Catalog> saveCatalogByGenre(@PathVariable String genre){
        Catalog savingCatalog = catalogService.saveCatalogByGenre(genre);
        return Objects.isNull(savingCatalog)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(savingCatalog, HttpStatus.OK);
    }

    @PostMapping("/saveMovie")
    public ResponseEntity<String> saveMovie(@RequestBody MovieDTO movieDTO){
        catalogService.saveMovie(movieDTO);
        return ResponseEntity.ok("La pelicula se envió a la cola.");
    }

    @PostMapping("/saveSerie")
    public ResponseEntity<String> saveSerie(@RequestBody SerieDTO serieDTO){
        catalogService.saveSerie(serieDTO);
        return ResponseEntity.ok("La serie se envió a la cola.");
    }

    @GetMapping("/withErrors/{genre}")
    ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre, @RequestParam("throwError") Boolean throwError) {
        return catalogService.findMovieByGenre(genre, throwError);
    }

}
