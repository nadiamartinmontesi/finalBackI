package com.example.movieservicefinal.service;

import com.example.movieservicefinal.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getListByGenre(String genre);
    Movie save(Movie movie);
}
