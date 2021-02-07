package com.acevedo.playground.companiesloader;

import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
@Slf4j
@Profile("!test")
public class CompaniesLoaderApplication implements CommandLineRunner {
    // TODO: pagination
    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(CompaniesLoaderApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Autowired
    private CompaniesLoaderService companiesLoaderService;

    @Override
    public void run(String... args) throws Exception {
        log.info("EXECUTING : command line runner");
        final List<AverageCompanyFundingForCountry> results = companiesLoaderService.parseCompanies("./data/companies.json");
        log.info("Results (count: {}) :", results.size());
        results.forEach(result -> log.info("{}", result));
    }
}
