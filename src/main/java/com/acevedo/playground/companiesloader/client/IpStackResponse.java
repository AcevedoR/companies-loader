package com.acevedo.playground.companiesloader.client;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IpStackResponse {

    private String ip;

    @JsonAlias("country_name")
    private String countryName;
}
