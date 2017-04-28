package org.wcs.lemursportal.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Mikajy <mikajy401@gmail.com>
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.wcs.lemursportal")
@ComponentScan({ "org.wcs.lemursportal" })
@PropertySource("classpath:database.properties")
public class RepositoryConfig {

	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;//on injecte la datasource pour qu'on puisse utiliser un autre datasource pour les test
	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory(){
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource);
//		sessionFactory.setPackagesToScan("org.wcs.lemursportal.model");
//		Properties hibeProperties = new Properties();
//		hibeProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		hibeProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//		hibeProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		sessionFactory.setHibernateProperties(hibeProperties);
//
//		return sessionFactory;
//	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("org.wcs.lemursportal.model");
		emf.setPersistenceUnitName("lemursportalPUnit");
		//Hibernate vendor
	    JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	    emf.setJpaVendorAdapter(jpaVendorAdapter);
		Properties hibeProperties = new Properties();
		hibeProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibeProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibeProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		emf.setJpaProperties(hibeProperties);
		emf.afterPropertiesSet();
	    return emf.getObject();
	}
	
	
//    @Bean
//    public HibernateTransactionManager transactionManager(){
//    	HibernateTransactionManager txManager = new HibernateTransactionManager();
//    	txManager.setSessionFactory(sessionFactory().getObject());
//    	return txManager;
//    }
    
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
       transactionManager.setDataSource(dataSource);
       return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
    }
}
