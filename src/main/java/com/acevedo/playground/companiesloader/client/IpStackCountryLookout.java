package com.acevedo.playground.companiesloader.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.acevedo.playground.companiesloader.helpers.AverageCompanyFundingForCountryHelper.DEFAULT_COUNTRY;

@Service
@Slf4j
@Profile("prod")
public class IpStackCountryLookout implements CountryLookout {


    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ipstack.base-url}")
    private String baseUrl;

    @Value("${ipstack.api-key}")
    private String apiKey;

    @Override
    public String getCountryForHomepage(String homepageUrl) {
        if(homepageUrl==null){
            return DEFAULT_COUNTRY;
        }
        final URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/{urlToLookout}")
                .queryParam("access_key", apiKey)
                .buildAndExpand(homepageUrl.replace("http://",""))
                .toUri();
        final IpStackResponse response = restTemplate.getForEntity(uri, IpStackResponse.class)
                .getBody();

        log.info("ipstack for homepage: '{}', response: '{}'", homepageUrl, response);

        return response != null ? response.getCountryName() : DEFAULT_COUNTRY;
    }

    public void setBaseUrl(String s) {
        this.baseUrl = s;
    }
}
