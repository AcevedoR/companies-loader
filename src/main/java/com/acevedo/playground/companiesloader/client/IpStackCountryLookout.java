package com.acevedo.playground.companiesloader.client;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class IpStackCountryLookout implements CountryLookout {
    @Override
    public String getCountryForHomepage(String homepageUrl) {
       throw new UnsupportedOperationException("Not yet implemented");
    }
}
