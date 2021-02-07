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
        return totalFunding / companyCount;
    }
}
