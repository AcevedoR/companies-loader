package com.acevedo.playground.companiesloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AverageCompanyFundingForCountry {
    String country;
    Long companyCount;
    Long totalFunding;

    public Long getAverageFunding(){
        return this.totalFunding / this.companyCount;
    }

    public static AverageCompanyFundingForCountry of(CompanyDto company){
        return builder()
                .country(company.getCountry())
                .companyCount(1L)
                .totalFunding(company.getMoneyRaised())
                .build();
    }

    public void addCompanyToResult(CompanyDto company) {
        this.totalFunding += company.getMoneyRaised();
        this.companyCount++;
    }
}
