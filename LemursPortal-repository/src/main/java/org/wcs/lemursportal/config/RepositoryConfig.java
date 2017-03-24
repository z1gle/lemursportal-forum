package org.wcs.lemursportal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Mikajy <mikajy401@gmail.com>
 * 
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "org.wcs.lemursportal" })
@PropertySource("classpath:database.properties")
public class RepositoryConfig {

	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;//on injecte la datasource pour qu'on puisse utiliser un autre datasource pour les test
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("org.wcs.lemursportal.data");
		Properties hibeProperties = new Properties();
		hibeProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibeProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibeProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		sessionFactory.setHibernateProperties(hibeProperties);

		return sessionFactory;
	}
	
    @Bean
    public HibernateTransactionManager transactionManager(){
    	HibernateTransactionManager txManager = new HibernateTransactionManager();
    	txManager.setSessionFactory(sessionFactory().getObject());
    	return txManager;
    }
}
