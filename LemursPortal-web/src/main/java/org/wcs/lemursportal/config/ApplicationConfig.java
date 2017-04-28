package org.wcs.lemursportal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Configuration 
@ComponentScan("org.wcs.lemursportal") 
@EnableWebMvc   
@EnableTransactionManagement
@EnableSpringDataWebSupport
@Import({ WebAppConfigurer.class, SecurityConfig.class, MailConfig.class, RepositoryConfig.class })
public class ApplicationConfig{

}
