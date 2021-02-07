package com.acevedo.playground.companiesloader.withspringcontext;

import com.acevedo.playground.companiesloader.service.CompaniesLoaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestApplicationConfiguration.class)
class CompaniesLoaderApplicationTests {

	@Autowired
	private CompaniesLoaderService companiesLoaderService;

	@Test
	void contextLoads() {//NOSONAR
	}

}
