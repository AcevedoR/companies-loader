package com.acevedo.playground.companiesloader.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.acevedo.playground.companiesloader.helpers.AverageCompanyFundingForCountryHelper.DEFAULT_COUNTRY;

@Service
@Slf4j
@Profile("dev")
public class MockCountryLookout implements CountryLookout {

    @Override
    public String getCountryForHomepage(String homepageUrl) {
        // TODO implement the real version instead of this mock
        if (homepageUrl == null) {
            return DEFAULT_COUNTRY;
        }
        final List<String> countries = Arrays.asList("France", "Spain", "Italy", "Germany", "Belgium");
        return countries.get(
                new Random().nextInt(countries.size())
        );
    }
}
