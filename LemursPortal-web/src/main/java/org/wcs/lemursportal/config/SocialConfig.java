package org.wcs.lemursportal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.github.gabrielruiu.springsocial.yahoo.connect.YahooConnectionFactory;

/**
 * 
 * @author z
 *
 */
@Configuration
@EnableSocial
@PropertySource("classpath:social.properties")
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;
    
    /**
     * Configures the connection factories for social provider.
     * @param cfConfig
     * @param env
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
    	
    	cfConfig.addConnectionFactory(new TwitterConnectionFactory(
                env.getProperty("twitter.consumer.key"),
                env.getProperty("twitter.consumer.secret")
        ));
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret")
        ));
        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
                env.getProperty("google.app.id"),
                env.getProperty("google.app.secret")
        ));
        cfConfig.addConnectionFactory(new YahooConnectionFactory(
                env.getProperty("yahoo.app.id"),
                env.getProperty("yahoo.app.secret")
        ));
    }

    /**
     * The UserIdSource determines the account ID of the user. The example application
     * uses the username as the account ID.
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        return new InMemoryUsersConnectionRepository(
//                connectionFactoryLocator
//        );
        
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
    }

    /**
     * This bean manages the connection flow between the account provider and
     * the example application.
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
