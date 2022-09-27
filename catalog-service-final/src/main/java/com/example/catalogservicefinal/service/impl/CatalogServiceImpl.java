package com.example.catalogservicefinal.service.impl;

import com.example.catalogservicefinal.dto.MovieDTO;
import com.example.catalogservicefinal.dto.SerieDTO;
import com.example.catalogservicefinal.entity.Catalog;
import com.example.catalogservicefinal.repository.CatalogRepository;
import com.example.catalogservicefinal.service.CatalogService;
import com.example.catalogservicefinal.service.MovieFeignClient;
import com.example.catalogservicefinal.service.SerieFeignClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl {

    @Value("${queue.movie.name}")
    private String movieQueue;
    @Value("${queue.serie.name}")
    private String serieQueue;

    private final Logger LOG = LoggerFactory.getLogger(CatalogService.class);

    private final MovieFeignClient movieFeignClient;
    private final SerieFeignClient serieFeignClient;
    private final CatalogRepository catalogRepository;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public CatalogServiceImpl(MovieFeignClient movieFeignClient, RabbitTemplate rabbitTemplate, SerieFeignClient serieFeignClient, CatalogRepository catalogRepository){
        this.movieFeignClient = movieFeignClient;
        this.rabbitTemplate = rabbitTemplate;
        this.serieFeignClient = serieFeignClient;
        this.catalogRepository = catalogRepository;
    }

    //metodo del parcial...
    //public List<MovieDTO> getCatalogByGenre(String genre) {
    //    LOG.info("Llamando al movie-service...");
    //    List<MovieDTO> moviesList = movieFeignClient.getListByGenre(genre).getBody();
    //    return moviesList;
    //}

    public Catalog getCatalogByGenre(String genre){
        LOG.info("Buscando catálogo del género " + genre);
        List<MovieDTO> movies = movieFeignClient.getListByGenre(genre).getBody();
        List<SerieDTO> series = serieFeignClient.getListByGenre(genre).getBody();
        Catalog catalog = new Catalog(genre, movies, series);
        return catalog;
    }

    public Catalog saveCatalogByGenre(String genre){
        LOG.info("Buscando catálogo del género " + genre);
        List<MovieDTO> movies = movieFeignClient.getListByGenre(genre).getBody();
        List<SerieDTO> series = serieFeignClient.getListByGenre(genre).getBody();
        Catalog catalog = new Catalog(genre, movies, series);
        LOG.info("Persistiendo datos...");
        catalogRepository.save(catalog);
        return catalog;
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public void saveMovieRabbit(MovieDTO movieDTO){
        LOG.info("Guardando película vía Rabbit");
        catalogRepository.movieSave(movieDTO);
    }

    @RabbitListener(queues = "${queue.serie.name}")
    public void saveSerieRabbit(SerieDTO serieDTO) {
        LOG.info("Guardando serie vía Rabbit");
        catalogRepository.serieSave(serieDTO);
    }

    @CircuitBreaker(name = "movie", fallbackMethod = "moviesFallbackMethod")
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre, Boolean throwError) {
        LOG.info("Buscando la pelicula via movie-service...");
        return movieFeignClient.getMovieByGenreWithThrowError(genre, throwError);
    }

    private ResponseEntity<List<MovieDTO>> moviesFallbackMethod(CallNotPermittedException exception) {
        LOG.info("CircuitBreaker activado...");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
