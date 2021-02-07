package com.acevedo.playground.companiesloader.service;

import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.dto.Company;
import com.acevedo.playground.companiesloader.dto.CompanyDto;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompaniesLoaderService {

    @Autowired
    private IpStackService ipStackService;

    public List<AverageCompanyFundingForCountry> parseCompanies(String filePath) throws IOException {
        List<AverageCompanyFundingForCountry> result = new ArrayList<>();

        JsonFactory f = new MappingJsonFactory();
        ObjectMapper mapper = new ObjectMapper();
        try (JsonParser parser = f.createParser(new File(filePath))) {
            JsonToken cursor = parser.nextToken();
            if (cursor != JsonToken.START_ARRAY) {
                throw new RuntimeException("json input should start with a json array");
            }
            while (parser.nextToken() == JsonToken.START_OBJECT) {
                ObjectNode node = mapper.readTree(parser);
                log.info(node.toString());
                final Company company = mapper.treeToValue(node, Company.class);
                log.info(company.toString());

                final String country = ipStackService.getCountryForHomepage(company.getHomepageUrl());

                AverageCompanyFundingForCountryHelper
                        .populateResult(result, CompanyDto.of(company, country));
            }
        }

        return result;
    }


    /**
     * to test out of memory error
     * @param filePath
     */
    public void loadAllFile(String filePath) throws IOException {
        final File file = new File(filePath);
        Files.readString(file.toPath());
    }
}
