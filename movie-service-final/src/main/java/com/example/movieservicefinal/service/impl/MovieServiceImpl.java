package com.example.movieservicefinal.service.impl;

import com.example.movieservicefinal.entity.Movie;
import com.example.movieservicefinal.repository.MovieRepository;
import com.example.movieservicefinal.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Value("${queue.movie.name}")
    private String movieQueue;
    public static Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getListByGenre(String genre) {
        LOG.info("Buscando peliculas segun genero");
        return movieRepository.findAllByGenre(genre);
    }

    @Override
    public Movie save(Movie movie) {
        LOG.info("Guardando pelicula");
        return movieRepository.save(movie);
    }

    public List<Movie> findByGenre(String genre, Boolean throwError) {
        if (throwError)
            throw new RuntimeException();
        return movieRepository.findAllByGenre(genre);
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public void saveMovie(Movie movie) {
        LOG.info("Guardando pelicula vía Rabbit");
        movieRepository.save(movie);
    }

}
