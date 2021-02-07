package com.acevedo.playground.companiesloader.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class IpStackService {
    public String getCountryForHomepage(String homepageUrl) {
        // TODO implement the real version instead of this mock
        if (homepageUrl == null) {
            return "DEFAULT_COUNTRY";
        }
        final List<String> countries = Arrays.asList("France", "Spain", "Italy", "Germany", "Belgium");
        return countries.get(
                new Random().nextInt(countries.size())
        );
    }
}
