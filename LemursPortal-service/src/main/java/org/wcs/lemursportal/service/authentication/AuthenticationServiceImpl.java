package org.wcs.lemursportal.service.authentication;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
		userInfo.setLastAccessDate(Calendar.getInstance().getTime());
		//update user to save lastAccessDate
		userInfoRepository.update(userInfo);
		return userInfo;
	}
}
