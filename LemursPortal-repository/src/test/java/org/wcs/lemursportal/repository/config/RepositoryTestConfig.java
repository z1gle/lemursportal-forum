package org.wcs.lemursportal.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.wcs.lemursportal.config.RepositoryConfig;

@EnableTransactionManagement
@ActiveProfiles("tests")
@Configuration
@Import({RepositoryConfig.class, EmbeddedDataSourceConfig.class})
@PropertySource("classpath:database-test.properties")
@ComponentScan("org.wcs.lemursportal")
public class RepositoryTestConfig {
	
}
