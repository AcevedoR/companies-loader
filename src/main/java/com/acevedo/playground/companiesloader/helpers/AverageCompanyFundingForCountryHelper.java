package com.acevedo.playground.companiesloader.helpers;

import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.dto.CompanyDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AverageCompanyFundingForCountryHelper {

    public static final String DEFAULT_COUNTRY = "DEFAULT_COUNTRY";

    public static void populateResult(List<AverageCompanyFundingForCountry> result, CompanyDto company) {
        Assert.notNull(result, "result list should not be null");
        Assert.notNull(result, "company should not be null");

        final Optional<AverageCompanyFundingForCountry> existingRow = result.stream()
                .filter(x -> x.getCountry().equals(company.getCountry()))
                .findFirst();

        existingRow
                .ifPresentOrElse(
                        row -> row.addCompanyToResult(company),
                        () -> result.add(AverageCompanyFundingForCountry.of(company))
                );
    }
}
