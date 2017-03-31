package org.wcs.lemursportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.wcs.lemursportal.service.authentication.AuthenticationService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 * 
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("org.wcs.lemursportal")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(SecurityConfig.class);

	private static final String ROLE_HIERARCHY = "ROLE_ADMIN > ROLE_USER";

	@Autowired
	AuthenticationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.expressionHandler(defaultWebSecurityExpressionHandler())
			.antMatchers("/", "/favicon.ico", "/resources/**", "/signup", "/registration").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/secure/**").hasAnyRole("ADMIN", "USER")
			.anyRequest().authenticated()
			.and().formLogin()
						.loginPage("/login").permitAll()
						.usernameParameter("login").passwordParameter("password")
						.failureUrl("/login?error")
						.loginProcessingUrl("/authenticate").permitAll()
			.and().logout()
						.logoutUrl("/logout").permitAll()
    			        .logoutSuccessUrl("/")
    		.and().rememberMe()
    					.rememberMeServices(rememberMeServices())
    					.key("remember-me-key");
    		//.and().csrf();
		http.csrf().disable();
	}
	
	@Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", authenticationService);
    }
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
//		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
		auth.userDetailsService(authenticationService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy(ROLE_HIERARCHY);
		return roleHierarchy;
	}

	@Bean
	public RoleVoter roleVoter() {
		return new RoleHierarchyVoter(roleHierarchy());
	}

	@Bean
	public SecurityExpressionHandler<FilterInvocation> defaultWebSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
		return defaultWebSecurityExpressionHandler;
	}
	
	@Bean("AuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

}
