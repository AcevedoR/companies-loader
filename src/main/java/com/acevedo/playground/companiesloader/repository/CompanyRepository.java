package com.acevedo.playground.companiesloader.repository;

import com.acevedo.playground.companiesloader.model.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {

}
