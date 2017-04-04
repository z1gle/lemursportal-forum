/**
 * 
 */
package org.wcs.lemursportal.service.user;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.helper.pagination.PaginationRequest;
import org.wcs.lemursportal.helper.pagination.PaginationResponse;
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
		UserInfo userInfo = userRepository.findUserByLogin(user.getLogin());
		if(userInfo != null){
			throw new RegistrationException(RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION);
		}
		String cryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(cryptedPassword);
		userRepository.insert(user);
	}

	
	@Override @Transactional(readOnly=true)
	public PaginationResponse<UserInfo> findByPagination(PaginationRequest<UserInfo> request) {
		//TODO - En attendant l'implementation d'une reherche avec pagination coté repository, on appel le findAll()
		final List<UserInfo> results = userRepository.findAll();
		return new PaginationResponse<UserInfo>(results, request.getPageNum(), request.getPageSize(), request.getPageSize());
	}


	@Override @Transactional(readOnly=true)
	public UserInfo getById(Integer id) {
		UserInfo user = userRepository.findById(id);
		return user;
	}


	@Override
	public void updateUserRoles(Integer userId, Set<UserType> roles) {
		UserInfo user = getById(userId);
		user.setRoles(roles);
		userRepository.update(user);
	}

	/**
	 * Modifier un utilisateur.
	 * S'assurer bien qu'on ne modifie que les champs modifiable.
	 */
	@Override
	public void update(UserInfo user) {
		UserInfo persistUser = getById(user.getId());
		persistUser.setBiographie(user.getBiographie());
		persistUser.setDateNaissance(user.getDateNaissance());
		persistUser.setEmail(user.getEmail());
		persistUser.setNom(user.getNom());
		persistUser.setPrenom(user.getPrenom());
//		persistUser.setLogin(user.getLogin());
		userRepository.merge(persistUser);
	}


	@Override
	public UserInfo getByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}

}
