package com.acevedo.playground.companiesloader.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private String name;

    @JsonAlias("total_money_raised")
    private String totalMoneyRaised;

    @JsonAlias("homepage_url")
    private String homepageUrl;
}
