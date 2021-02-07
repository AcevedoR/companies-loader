package com.acevedo.playground.companiesloader.service;

import com.acevedo.playground.companiesloader.client.CountryLookout;
import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.dto.CompanyDto;
import com.acevedo.playground.companiesloader.helpers.AverageCompanyFundingForCountryHelper;
import com.acevedo.playground.companiesloader.model.Company;
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
    private CountryLookout countryLookout;

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
                log.debug(node.toString());
                final Company company = mapper.treeToValue(node, Company.class);
                log.debug(company.toString());

                // TODO: pagination ?
                final String country = countryLookout.getCountryForHomepage(company.getHomepageUrl());

                final CompanyDto companyDto = CompanyDto.of(company, country);
                if(companyDto.getMoneyRaised() != null && companyDto.getMoneyRaised() != 0L) {// data sometimes miss this info, in this case we will skip the company
                    AverageCompanyFundingForCountryHelper
                            .populateResult(result, companyDto);
                }
            }
        }

        return result;
    }


    /**
     * to test Outofmemory error
     * @param filePath
     */
    public void loadAllFile(String filePath) throws IOException {
        final File file = new File(filePath);
        Files.readString(file.toPath());
    }
}
