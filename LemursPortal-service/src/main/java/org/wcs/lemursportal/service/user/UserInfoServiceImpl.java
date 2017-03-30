/**
 * 
 */
package org.wcs.lemursportal.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.repository.user.UserRepository;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.user.UserInfoService#save(org.wcs.lemursportal.model.user.UserInfo)
	 */
	@Override
	public void save(UserInfo user) {
		if(user.getEnabled() == null){
			LOGGER.info("Utilisateur actif par defaut");
			user.setEnabled(true);//Par defaut c'est true
		}
		if(user.getRoles() == null || user.getRoles().isEmpty()){
			LOGGER.info("Roles - Simple utilisateur par defaut");
			user.setRoles(new java.util.HashSet<UserType>());
			user.getRoles().add(UserRole.SIMPLE_USER.getUserType());
		}
		//On verifie que le login n'existe pas encore en bdd
		UserInfo userInfo = userRepository.findUserByLogin(user.getLogin());
		if(userInfo != null){
			throw new RegistrationException(RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION);
		}
		String cryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(cryptedPassword);
		userRepository.insert(user);
	}

}
