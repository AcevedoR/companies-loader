package com.acevedo.playground.companiesloader.withspringcontext;

import com.acevedo.playground.companiesloader.client.IpStackCountryLookout;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.acevedo.playground.companiesloader"
        ,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class))
@EnableAutoConfiguration
public class TestApplicationConfiguration {
    @Bean
    public IpStackCountryLookout testIpStackCountryLookout(){
        return new IpStackCountryLookout();
    }
}