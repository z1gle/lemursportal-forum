package org.wcs.lemursportal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Configuration 
@ComponentScan("org.wcs.lemursportal") 
@EnableWebMvc   
@Import({ WebAppConfigurer.class, SecurityConfig.class })
public class ApplicationConfig{

}
