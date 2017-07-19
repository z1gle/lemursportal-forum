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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.dto.user.SocialProvider;
import org.wcs.lemursportal.dto.user.UserRegistrationForm;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.repository.user.UserRepository;
import org.wcs.lemursportal.service.exception.UserAlreadyExistAuthenticationException;

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
	
//	@Autowired
//    @Qualifier(value = "localUserDetailService")
//    private UserDetailsService userDetailService;
	
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
		//On verifie que l'email n'existe pas encore en bdd
		UserInfo userEmailExample = new UserInfo();
		userEmailExample.setEmail(user.getEmail());
		boolean emailExist = userRepository.exists(Example.of(userEmailExample));
		if(emailExist){
			throw new RegistrationException(RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION);
		}
		String cryptedPassword = encodePassword(user.getPassword(), user.getProvider() == null || user.getProvider().equalsIgnoreCase("NONE"));
		user.setPassword(cryptedPassword);
		user.setDateInscription(Calendar.getInstance().getTime());
		userRepository.save(user);
	}
	
    private String encodePassword(String password, boolean isNormalRegistration) {
        String encodedPassword = null;

        if (isNormalRegistration) {
            LOGGER.debug("Registration normal. Encoding password.");
            encodedPassword = bCryptPasswordEncoder.encode(password);
        }

        return encodedPassword;
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
	@PostAuthorize("returnObject.email == authentication.name")
	public UserInfo getByEmail(String email) {
		return userRepository.findByEmailAndEnabled(email, Boolean.TRUE);
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

	@Override
    @Transactional(noRollbackFor = UserAlreadyExistAuthenticationException.class) 
    public UserInfo registerNewUser(final UserRegistrationForm userRegistrationForm) throws UserAlreadyExistAuthenticationException {
        UserInfo userExist = userRepository.findByEmail(userRegistrationForm.getUserId());
        if (userExist != null) {
            
            throw new UserAlreadyExistAuthenticationException("User with email id " + userRegistrationForm.getEmail() + " already exist");
        }

        UserInfo user = buildUser(userRegistrationForm);
        save(user);
//        userRepository.save(user);
//        userRepository.flush();

        return user;//(LocalUser) userDetailService.loadUserByUsername(userRegistrationForm.getUserId());
    }
	
	private UserInfo buildUser(final UserRegistrationForm formDTO) {
    	UserInfo user = new UserInfo();
        user.setEmail(formDTO.getEmail());
        user.setNom(formDTO.getNom());
        user.setPrenom(formDTO.getPrenom());
        user.setPassword(formDTO.getPassword());
        user.setPhotoProfil(formDTO.getImageUrl());
        user.setDateNaissance(formDTO.getDateNaissance());
        
        user.setRoles(new java.util.HashSet<UserType>());
		user.getRoles().add(UserRole.SIMPLE_USER.getUserType());
//        user.setActive(1);
		if(formDTO.getSocialProvider() == null) {
			user.setProvider(SocialProvider.NONE.toString());
		} else 
			user.setProvider(formDTO.getSocialProvider().toString());
        return user;
    }

}
