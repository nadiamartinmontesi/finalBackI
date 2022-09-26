package com.example.catalogservicefinal.repository;

import com.example.catalogservicefinal.entity.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {
    Catalog insertCatalog(Catalog catalog);
}
