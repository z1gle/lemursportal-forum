package org.wcs.lemursportal.service.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.wcs.lemursportal.model.user.UserInfo;

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
    public void autoLogin(String login, String password, HttpServletRequest request);
}
