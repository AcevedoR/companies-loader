package com.acevedo.playground.companiesloader.model;

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
public class Funding {

    @JsonAlias("raised_amount")
    private Long amount;

    @JsonAlias("raised_currency_code")
    private String currencyCode;
}
