package com.acevedo.playground.companiesloader;

import com.acevedo.playground.companiesloader.dto.CompanyDto;
import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class CompaniesLoaderApplication implements CommandLineRunner {
    // TODO: pagination
    // TODO: embedded database version
    // TODO: test with big file and limited java memory
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
        log.info(
                companiesLoaderService.loadCompanies().stream()
                        .map(CompanyDto::toString)
                        .collect(Collectors.joining())
        );
        companiesLoaderService.parseCompanies("./data/companies.json");
    }
}
