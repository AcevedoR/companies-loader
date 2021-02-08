package com.acevedo.playground.companiesloader.dto;

import com.acevedo.playground.companiesloader.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.acevedo.playground.companiesloader.helpers.AverageCompanyFundingForCountryHelper.DEFAULT_COUNTRY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {

    private String name;

    private String country;

    private Long moneyRaised;

    public static CompanyDto of(Company company, String country) {
        return builder()
                .name(company.getName())
                .moneyRaised(company.parseTotalMoneyRaised())
                .country(country != null ? country : DEFAULT_COUNTRY)
                .build();
    }
}
