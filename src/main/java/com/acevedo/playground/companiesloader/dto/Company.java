package com.acevedo.playground.companiesloader.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private String name;

    @JsonAlias("total_money_raised")
    private String totalMoneyRaised;

    @JsonAlias("homepage_url")
    private String homepageUrl;

    public Long parseTotalMoneyRaised() {
        return null;
    }
}
