package com.acevedo.playground.companiesloader;

import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class CompaniesLoaderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(CompaniesLoaderApplication.class, args);
        log.info("APPLICATION FINISHED");
    }
    // TODO switch profile: with api call and little file, and without and real file
    // TODO apistack impl and tests: test deserialization only
    @Autowired
    private CompaniesLoaderService companiesLoaderService;

    @Override
    public void run(String... args) throws Exception {
        log.info("EXECUTING : command line runner");
        final String filePath = args[0] != null ? args[0] : "./data/companies.json";
        final List<AverageCompanyFundingForCountry> results = companiesLoaderService.parseCompanies(filePath);
        log.info("Results: (count: {}) :", results.size());
        log.info("Country, companies count, average founding in USD :");
        results.forEach(result -> log.info("{}, {}, {}", result.getCountry(), result.getCompanyCount(), result.getAverageFunding()));
    }
}
