/**
 * 
 */
package org.wcs.lemursportal.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author mikajy.hery
 *
 */
@Configuration
@PropertySource({ "/application.properties" })
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
}
