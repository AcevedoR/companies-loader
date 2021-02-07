package com.acevedo.playground.companiesloader;


import com.acevedo.playground.companiesloader.dto.AverageCompanyFundingForCountry;
import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import com.acevedo.playground.companiesloader.service.IpStackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompaniesLoaderServiceTest {

    @InjectMocks
    private CompaniesLoaderService companiesLoaderService;

    @Mock
    private IpStackService ipStackServiceMock;

    @BeforeEach
    void init(){
        when(ipStackServiceMock.getCountryForHomepage(any(String.class))).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String homepageUrl = invocation.getArgument(0);
                switch (homepageUrl) {
                    case "frenchUrl":
                        return "France";
                    case "spanishUrl":
                        return "Spain";
                    default:
                        return "Default_country";
                }
            }
        });
    }

    @Test
    void parse_ok() throws IOException {
        // given
        final String file = Objects.requireNonNull(getClass().getClassLoader().getResource("companies.json")).getFile();

        // when
        final List<AverageCompanyFundingForCountry> result = companiesLoaderService.parseCompanies(file);

        // then
        Assertions.assertEquals(
                Arrays.asList(
                        AverageCompanyFundingForCountry.builder()
                                .country("France").companyCount(2L).totalFunding(644000L).build(),
                        AverageCompanyFundingForCountry.builder()
                                .country("United States").companyCount(1L).totalFunding(8000000L).build()
                ),
                result
        );
    }
}
