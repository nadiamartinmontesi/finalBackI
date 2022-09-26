package com.example.catalogservicefinal.service;

import com.example.catalogservicefinal.dto.SerieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "serie-service-final")
public interface SerieFeignClient {

    @GetMapping("/series/{genre}")
    ResponseEntity<List<SerieDTO>> getListByGenre(@PathVariable("genre") String genre);
}
