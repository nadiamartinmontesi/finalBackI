package com.example.catalogservicefinal.repository;

import com.example.catalogservicefinal.dto.MovieDTO;
import com.example.catalogservicefinal.dto.SerieDTO;
import com.example.catalogservicefinal.entity.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {
    Catalog getCatalogByGenre(String genre);
    void serieSave(SerieDTO serieDTO);
    void movieSave(MovieDTO movieDTO);
}
