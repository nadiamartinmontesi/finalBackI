package com.example.gatewayservicefinal.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @CircuitBreaker(name = "movieService")
    @GetMapping("/movies")
    public ResponseEntity<String> moviesFallback() {
        return new ResponseEntity<>("El movie-service no esta disponible....",
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CircuitBreaker(name = "catalogService")
    @GetMapping("/catalogs")
    public ResponseEntity<String> catalogsFallback() {
        return new ResponseEntity<>("El catalog-service no esta disponible....",
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
