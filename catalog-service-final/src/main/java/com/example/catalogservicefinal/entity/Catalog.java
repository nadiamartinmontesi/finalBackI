package com.example.catalogservicefinal.entity;

import com.example.catalogservicefinal.dto.MovieDTO;
import com.example.catalogservicefinal.dto.SerieDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Catalog {

    @Id
    private String id;
    private String genre;
    private List<MovieDTO> movies;
    private List<SerieDTO> series;

    public Catalog() {
        //No-args constructor
    }

    public Catalog(String genre, List<MovieDTO> movieDTOS, List<SerieDTO> serieDTOS) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Catalog{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", movies=" + movies +
                ", series=" + series +
                '}';
    }
}
