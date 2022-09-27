package com.example.serieservicefinal.service.imp;

import com.example.serieservicefinal.entities.Serie;
import com.example.serieservicefinal.repository.SerieRepository;
import com.example.serieservicefinal.service.SerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {

    @Value("${queue.serie.name}")
    private String serieQueue;

    public static Logger LOG = LoggerFactory.getLogger(SerieService.class);
    private final SerieRepository serieRepository;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SerieServiceImpl(SerieRepository serieRepository, RabbitTemplate rabbitTemplate) {
        this.serieRepository = serieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Serie> getListByGenre(String genre) {
        LOG.info("Buscando series según género");
        return serieRepository.findAllByGenre(genre);
    }

    @Override
    public Serie save(Serie serie) {
        LOG.info("Guardando serie");
        return serieRepository.save(serie);
    }

    public void saveSerieRabbit(Serie serie){
        rabbitTemplate.convertAndSend(serieQueue, serie);
    }
}
