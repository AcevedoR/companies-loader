package com.acevedo.playground.companiesloader.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.acevedo.playground.companiesloader.helpers.CurrencyHelper.convertAmountToUSD;
import static org.springframework.util.StringUtils.hasText;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private String name;

    @JsonAlias("total_money_raised")
    private String totalMoneyRaised;

    @JsonAlias("funding_rounds")
    private List<Funding> fundingRounds = new ArrayList<>();

    @JsonAlias("homepage_url")
    private String homepageUrl;

    public Long parseTotalMoneyRaised() {
        if(fundingRounds == null || fundingRounds.stream().anyMatch(x -> x == null || x.getAmount() == null || !hasText(x.getCurrencyCode()))) {
            return 0L;// handling missing/invalid data
        }
        return fundingRounds.stream()
                .map(x -> convertAmountToUSD(x.getAmount(), x.getCurrencyCode()))
                .reduce(0L, Long::sum);
    }
}
