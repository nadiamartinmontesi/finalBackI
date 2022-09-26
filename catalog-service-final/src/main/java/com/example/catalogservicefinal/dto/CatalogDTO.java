package com.example.catalogservicefinal.dto;

import java.util.List;

public class CatalogDTO {

    private String genre;
    private List<MovieDTO> movies;
    private List<SerieDTO> series;

    public CatalogDTO() {
        //No-args constructor
    }

    public CatalogDTO(String genre, List<MovieDTO> movieDTOS, List<SerieDTO> serieDTOS) {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }

    public List<SerieDTO> getSeries() {
        return series;
    }

    public void setSeries(List<SerieDTO> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "CatalogDTO{" +
                "genre='" + genre + '\'' +
                ", movies=" + movies +
                ", series=" + series +
                '}';
    }
}
