package com.example.serieservicefinal.service;

import com.example.serieservicefinal.entities.Serie;

import java.util.List;

public interface SerieService {
    List<Serie> getListByGenre(String genre);
    Serie insertSerie(Serie movie);
}
