package com.acevedo.playground.companiesloader;


import com.acevedo.playground.companiesloader.client.IpStackCountryLookout;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class IpStackTest {

    private final IpStackCountryLookout ipStackCountryLookout = new IpStackCountryLookout();

    private final WireMockServer wireMockServer = new WireMockServer();

    @BeforeEach
    void beforeEach(){
        wireMockServer.start();
        ipStackCountryLookout.setBaseUrl("http://localhost:8080");
    }
    @AfterEach
    void afterEach(){
        wireMockServer.stop();
    }

    @Test
    void response_ok() throws IOException {
        // given
        final String homepageUrl = "some-page-url";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("ipstack_response.json")).getFile());
        final String ipstackResponse = Files.readString(Path.of(file.getPath()));

        stubFor(get(urlPathMatching("/some-page-url*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ipstackResponse)));

        // when
        final String result = ipStackCountryLookout.getCountryForHomepage(homepageUrl);

        // then
        Assertions.assertEquals("United States", result);
    }
}
