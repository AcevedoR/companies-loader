package com.acevedo.playground.companiesloader;

import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.dto.CompanyDto;
import com.acevedo.playground.companiesloader.service.AverageCompanyFundingForCountryHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

class AverageCompanyFundingForCountryHelperTest {

    private static final String COMPANY_1 = "company1";

    @Test
    void empty_list_adding_new_country() {
        // given
        List<AverageCompanyFundingForCountry> result = new ArrayList<>();
        CompanyDto company = CompanyDto.builder()
                .name(COMPANY_1).moneyRaised(100L).country("France")
                .build();

        // when
        AverageCompanyFundingForCountryHelper.populateResult(result, company);

        // then
        Assertions.assertEquals(
                singletonList(
                        AverageCompanyFundingForCountry.builder()
                                .country("France").companyCount(1L).totalFunding(100L).build()
                ),
                result
        );
    }

    @Test
    void notEmpty_list_adding_new_country() {
        // given
        List<AverageCompanyFundingForCountry> result = singletonList(
                AverageCompanyFundingForCountry.builder()
                        .country("Spain").companyCount(2L).totalFunding(500L).build()
        );
        CompanyDto company = CompanyDto.builder()
                .name(COMPANY_1).moneyRaised(850L).country("Italy")
                .build();

        // when
        AverageCompanyFundingForCountryHelper.populateResult(result, company);

        // then
        Assertions.assertEquals(
                asList(
                        AverageCompanyFundingForCountry.builder()
                                .country("Spain").companyCount(2L).totalFunding(500L).build(),
                        AverageCompanyFundingForCountry.builder()
                                .country("Italy").companyCount(2L).totalFunding(850L).build()
                ),
                result
        );
    }

    @Test
    void notEmpty_list_merging_country() {
        // given
        List<AverageCompanyFundingForCountry> result = asList(
                AverageCompanyFundingForCountry.builder()
                        .country("Belgium").companyCount(2L).totalFunding(400L).build(),
                AverageCompanyFundingForCountry.builder()
                        .country("Germany").companyCount(3L).totalFunding(850L).build()
        );
        CompanyDto company = CompanyDto.builder()
                .name(COMPANY_1).moneyRaised(520L).country("Germany")
                .build();

        // when
        AverageCompanyFundingForCountryHelper.populateResult(result, company);

        // then
        Assertions.assertEquals(
                asList(
                        AverageCompanyFundingForCountry.builder()
                                .country("Belgium").companyCount(2L).totalFunding(400L).build(),
                        AverageCompanyFundingForCountry.builder()
                                .country("Germany").companyCount(4L).totalFunding(1370L).build()
                ),
                result
        );
    }
}
