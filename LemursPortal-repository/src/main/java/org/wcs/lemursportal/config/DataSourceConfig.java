/**
 * 
 */
package org.wcs.lemursportal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author mikajy.hery
 *
 */
@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfig {
	@Autowired
	private Environment env;

	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
	    dataSource.setUrl(env.getProperty("dataSource.url"));
	    dataSource.setUsername(env.getProperty("dataSource.username"));
	    dataSource.setPassword(env.getProperty("dataSource.password"));
	    return dataSource;
	    
//		HikariConfig config = new HikariConfig();
//		config.setMaximumPoolSize(Integer.parseInt(env.getProperty("dataSource.max.pool.size")));
//		config.setDataSourceClassName(env.getProperty("dataSource.driverClassName"));
//		config.addDataSourceProperty("url", env.getProperty("dataSource.url"));
//		config.addDataSourceProperty("user",env.getProperty("dataSource.username"));
//		config.addDataSourceProperty("password",env.getProperty("dataSource.password"));
//		config.addDataSourceProperty("serverName",env.getProperty("dataSource.server"));
//		config.addDataSourceProperty("portNumber",env.getProperty("dataSource.port"));
//
//		return new HikariDataSource(config);
	}
}
