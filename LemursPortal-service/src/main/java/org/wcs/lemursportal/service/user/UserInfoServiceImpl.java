/**
 * 
 */
package org.wcs.lemursportal.service.user;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	@Transactional(noRollbackFor=RegistrationException.class)
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
		UserInfo userLoginExample = new UserInfo();
		userLoginExample.setLogin(user.getLogin());
		boolean loginExist = userRepository.exists(Example.of(userLoginExample));
		if(loginExist){
			throw new RegistrationException(RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION);
		}
		String cryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(cryptedPassword);
		user.setDateInscription(Calendar.getInstance().getTime());
		userRepository.save(user);
	}

	@Override @Transactional(readOnly=true)
	public UserInfo getById(Integer id) {
		UserInfo user = userRepository.findOne(id);
		return user;
	}


	@Override
	public void updateUserRoles(Integer userId, Set<UserType> roles) {
		UserInfo user = getById(userId);
		user.setRoles(roles);
		userRepository.save(user);
	}

	/**
	 * Modifier un utilisateur.
	 */
	@Override
	public void update(UserInfo user) {
		UserInfo persistUser = getById(user.getId());
		//bien s'assurer qu'on ne modifie que les champs modifiable.
		persistUser.setBiographie(user.getBiographie());
		persistUser.setDateNaissance(user.getDateNaissance());
		persistUser.setEmail(user.getEmail());
		persistUser.setNom(user.getNom());
		persistUser.setPrenom(user.getPrenom());
		persistUser.setInstitution(user.getInstitution());
		persistUser.setPostOccupe(user.getPostOccupe());
		if(StringUtils.isNotBlank(user.getPhotoProfil())){
			persistUser.setPhotoProfil(user.getPhotoProfil());
		}
//		persistUser.setLogin(user.getLogin());
		userRepository.save(persistUser);
	}


	@Override
	public UserInfo getByLogin(String login) {
		return userRepository.findByLoginAndEnabled(login, Boolean.TRUE);
	}


	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.user.UserInfoService#findByPagination(org.springframework.data.domain.Example, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<UserInfo> findByPagination(Example<UserInfo> example,
			Pageable pageable) {
		throw new NotImplementedException();
	}


	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.user.UserInfoService#findByPagination(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<UserInfo> findByPagination(Pageable pageable) {
		final List<UserInfo> results = userRepository.findAll();
		return new PageImpl<>(results, pageable, results.size());
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.user.UserInfoService#getExpertById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserInfo getExpertById(Integer id) {
		UserInfo user = userRepository.findOne(id);
		return user;
	}

}
