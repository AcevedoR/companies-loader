package com.acevedo.playground.companiesloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
                .country(country)
                .build();
    }
}
