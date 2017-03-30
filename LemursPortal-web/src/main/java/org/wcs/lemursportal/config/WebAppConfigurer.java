package org.wcs.lemursportal.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.wcs.lemursportal.web.formatter.DefaultDateFormatter;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Configuration
@EnableWebMvc // <mvc:annotation-driven />
@ComponentScan({ "org.wcs.lemursportal" })
@PropertySource({ "/application.properties" })
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		String[] resources = {"/properties/i18n/message"};
//		messageSource.setBasenames(resources);
		messageSource.setBasename(env.getProperty("application.i18n.message.path"));
		messageSource.setDefaultEncoding(env.getProperty("application.default.encoding"));
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale(env.getProperty("application.default.locale")));
		resolver.setCookieName(env.getProperty("application.locale.resolver.cookiename"));
		resolver.setCookieMaxAge(4800);
		return resolver;
	}
	
//	@Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setValidationMessageSource(messageSource());
//        return validator;
//    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		interceptor.setIgnoreInvalidLocale(true);
		registry.addInterceptor(interceptor);
	}
	
	@Bean(name="dateFormatter")
    public DefaultDateFormatter defaultDateFormatter(){
    	return new DefaultDateFormatter(messageSource());
    }

	@Override
	public void addFormatters(FormatterRegistry registry) {
		DefaultDateFormatter dateFormatter = defaultDateFormatter(); 
    	registry.addFormatter(dateFormatter);
		super.addFormatters(registry);
	}

}
