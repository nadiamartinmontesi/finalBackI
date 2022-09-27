package com.example.catalogservicefinal.controller;

import com.example.catalogservicefinal.dto.MovieDTO;
import com.example.catalogservicefinal.entity.Catalog;
import com.example.catalogservicefinal.service.impl.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogServiceImpl catalogServiceImpl;

    @Autowired
    public CatalogController(CatalogServiceImpl catalogServiceImpl) {
        this.catalogServiceImpl = catalogServiceImpl;
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
    public ResponseEntity<Catalog> getCatalogByGenre(@PathVariable String genre) {
        Catalog catalogByGenre = catalogServiceImpl.getCatalogByGenre(genre);
        return Objects.isNull(catalogByGenre)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(catalogByGenre, HttpStatus.OK);
    }

    @PostMapping("/save/{genre}")
    public ResponseEntity<Catalog> saveCatalogByGenre(@PathVariable String genre){
        Catalog savingCatalog = catalogServiceImpl.saveCatalogByGenre(genre);
        return Objects.isNull(savingCatalog)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(savingCatalog, HttpStatus.OK);
    }

    @GetMapping("/withErrors/{genre}")
    ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre, @RequestParam("throwError") Boolean throwError) {
        return catalogServiceImpl.findMovieByGenre(genre, throwError);
    }

}
