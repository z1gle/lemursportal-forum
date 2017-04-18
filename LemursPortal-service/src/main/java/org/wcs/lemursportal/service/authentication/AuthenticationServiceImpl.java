package org.wcs.lemursportal.service.authentication;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.user.UserRepository;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Service
@Transactional
public class AuthenticationServiceImpl extends
		AbstractUserDetailsService<UserInfo> implements AuthenticationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserDetailsService.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userInfoRepository; 

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.authentication.AbstractUserDetailsService#findUserByLogin(java.lang.String)
	 */
	@Override
	protected UserInfo findUserByLogin(String login) 
			throws UsernameNotFoundException {
		//k123 = "21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5"
		UserInfo userInfo = userInfoRepository.findUserByLogin(login);
		if(userInfo != null){
			userInfo.setLastAccessDate(Calendar.getInstance().getTime());
			//update user to save lastAccessDate
			userInfoRepository.update(userInfo);
		}
		return userInfo;
	}

//	@Override
//	public String findLoggedInUsername() {
//		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails)userDetails).getUsername();
//        }
//
//        return null;
//	}

	@Override
	public void autoLogin(String login, String password, HttpServletRequest request) {
		UserDetails userDetails = this.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        request.getSession();//créer une session si ce n'est pas déjà fait
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            LOGGER.debug(String.format("Auto login '%s' successfully!", login));
        } else{
        	LOGGER.debug(String.format("Auto login '%s' failed!", login));
        }
		
	}
}
