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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
import org.wcs.lemursportal.service.authentication.AuthenticationService;
import org.wcs.lemursportal.service.user.LocalUserDetailService;
import org.wcs.lemursportal.service.user.SocialUserDetailService;
import org.wcs.lemursportal.repository.user.UserRepository;

/**
 * @author Mikajy <mikajy401@gmail.com>
 * 
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true/*, securedEnabled=true*/)
@ComponentScan("org.wcs.lemursportal")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(SecurityConfig.class);

	private static final String ROLE_HIERARCHY = "ROLE_ADMIN > ROLE_MODERATEUR > ROLE_EXPERT > ROLE_USER";

	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
    private UserRepository userRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.requiresChannel().anyRequest().requiresSecure();
		//On securise les pages qui manipules des mots de passe
		http.requiresChannel().antMatchers("/login**", "/**", "/formation**", "/authenticate**").requiresSecure();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation().none() //on veut garder la même session pour le basculement des pages http<->https
			.maximumSessions(1).sessionRegistry(sessionRegistry());
		http.authorizeRequests()
			.expressionHandler(defaultWebSecurityExpressionHandler())
			//.antMatchers("/", "/favicon.ico", "/resources/**", "/signup", "/registration").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/secure/**", "/secured/**", "/user/**", "/logout/**").authenticated()
			.anyRequest().permitAll()
			.and().formLogin()
						.loginPage("/login").permitAll()
						.usernameParameter("email").passwordParameter("password")
						.failureUrl("/login?error")
						.loginProcessingUrl("/authenticate").permitAll()
			.and().logout()
						.deleteCookies("JSESSIONID")
						.logoutUrl("/logout").permitAll()
    			        .logoutSuccessUrl("/")
    	    .and()
    	    			.authorizeRequests()
                        .antMatchers(
                                "/auth/**",
                                "/login",
                                "/forgot",
                                "/reset",
                                "/signup/**",
                                "/register/**"
                        ).permitAll()
    		.and().rememberMe()
    					.rememberMeServices(rememberMeServices())
    					.key("remember-me-key")
    		.and()
    					.apply(new SpringSocialConfigurer());;
//		http.csrf();
		http.csrf().disable();
		
	}
	
	
	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
	
	@Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", authenticationService);
    }
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/**
     * Configures the authentication manager bean which processes authentication
     * requests.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(bCryptPasswordEncoder());
    }
    
    /*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
//		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
		auth.userDetailsService(authenticationService).passwordEncoder(bCryptPasswordEncoder());
	}
	*/
    
    
    /**
     * This bean is used to load the user specific data when social sign in
     * is used.
     */
    @Autowired
    public SocialUserDetailsService socialUserDetailsService() {
        return new SocialUserDetailService(userDetailsService());
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
	
	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	/**
     * This bean is load the user specific data when form login is used.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new LocalUserDetailService(userRepository);
    }
}
