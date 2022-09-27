package com.example.movieservicefinal.service.impl;

import com.example.movieservicefinal.entity.Movie;
import com.example.movieservicefinal.repository.MovieRepository;
import com.example.movieservicefinal.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Movie> getListByGenre(String genre) {
        LOG.info("Buscando películas según género");
        return movieRepository.findAllByGenre(genre);
    }

    @Override
    public Movie save(Movie movie) {
        LOG.info("Guardando película");
        return movieRepository.save(movie);
    }

    public List<Movie> findByGenre(String genre, Boolean throwError) {
        if (throwError)
            throw new RuntimeException();
        return movieRepository.findAllByGenre(genre);
    }

    public void saveMovieRabbit(Movie movie){
        rabbitTemplate.convertAndSend(movieQueue, movie);
    }

}
