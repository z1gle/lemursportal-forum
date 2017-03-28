package org.wcs.lemursportal.service.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface AuthenticationService extends UserDetailsService {
	
	/**
	 * 
	 * @return
	 */
//	public String findLoggedInUsername();
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
    public void doLogin(String username, String password);
}
