/**
 * 
 */
package org.wcs.lemursportal.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * @author mikajy.hery
 *
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {
	
	@Autowired
	Environment env;
	
	@Bean(name = "mailSender")
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.smtp.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mail.smtp.port")));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
		prop.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		prop.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		prop.put("mail.debug", env.getProperty("mail.debug"));
		return mailSender;
	}
	
//	@Bean("mailSender")
//    public JavaMailSender getMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
// 
//        // Using gmail.
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("testmailmahery@gmail.com");
//        mailSender.setPassword("maheryapm2016");
// 
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        javaMailProperties.put("mail.debug", "true");
// 
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }
 
    /*
     * FreeMarker configuration.
     */
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:fmtemplates/");
        return bean;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        //c.setLocation(new ClassPathResource("mail.properties"));
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }
}
