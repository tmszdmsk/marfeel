package com.tadamski.marfeel.crawler.crawler.storage;

import java.net.URI;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends CrudRepository<Result, URI> {
}
