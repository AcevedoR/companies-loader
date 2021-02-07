package com.acevedo.playground.companiesloader;


import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class CompaniesLoaderServiceTest {

    private final CompaniesLoaderService companiesLoaderService = new CompaniesLoaderService();

    @Test
    void parse_ok() throws IOException {
        // given
        final String file = Objects.requireNonNull(getClass().getClassLoader().getResource("companies.json")).getFile();

        // when
        final List<AverageCompanyFundingForCountry> result = companiesLoaderService.parseCompanies(file);

        // then
        Assertions.assertEquals(
                Arrays.asList(
                        AverageCompanyFundingForCountry.builder()
                                .country("France").companyCount(2L).averageFunding(75000L).build(),
                        AverageCompanyFundingForCountry.builder()
                                .country("Spain").companyCount(1L).averageFunding(40000L).build()
                ),
                result
        );
    }
}
