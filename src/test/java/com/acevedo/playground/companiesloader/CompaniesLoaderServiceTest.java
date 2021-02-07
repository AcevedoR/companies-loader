package com.acevedo.playground.companiesloader;


import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.dto.CompanyDto;
import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.acevedo.playground.companiesloader.dto.CompanyDto.builder;
import static java.util.Collections.singletonList;

@ExtendWith(MockitoExtension.class)
class CompaniesLoaderServiceTest {

    private final CompaniesLoaderService companiesLoaderService = new CompaniesLoaderService();

//    @Test
    void load_ok(){
        // given

        // when
        final List<CompanyDto> result = companiesLoaderService.loadCompanies();

        // then
        Assertions.assertEquals(
                singletonList(builder().name("some company").country("SomeCountry").moneyRaised("150000").build()),
                result
        );
    }

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
