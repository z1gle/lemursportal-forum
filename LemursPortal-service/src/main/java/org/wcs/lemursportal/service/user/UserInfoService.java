/**
 * 
 */
package org.wcs.lemursportal.service.user;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.wcs.lemursportal.dto.user.UserRegistrationForm;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.service.exception.UserAlreadyExistAuthenticationException;


/**
 * @author mikajy.hery
 *
 */
public interface UserInfoService {
	
	/**
	 * 
	 * @param userId
	 * @param roles
	 */
	@PreAuthorize("hasRole('ADMIN')")
	void updateUserRoles(Integer userId, Set<UserType> roles);
	/**
	 * 
	 * @param email
	 * @return
	 */
	@PostAuthorize ("returnObject.email == authentication.name")
	UserInfo getByEmail(String email);
	/**
	 * 
	 * @param id
	 * @return
	 */

	@PostAuthorize ("returnObject.email == authentication.name or hasRole('ADMIN')")
	UserInfo getById(Integer id);
	
	
	UserInfo getExpertById(Integer id);
	
	
	/**
	 * 
	 * @param user
	 */
	void save(UserInfo user);
	/**
	 * 
	 * @param user
	 */
	//@PreAuthorize ("#user.email == authentication.name")
	void update(UserInfo user);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATEUR')")
	Page<UserInfo> findByPagination(org.springframework.data.domain.Example<UserInfo> example, Pageable pageable);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATEUR')")
	Page<UserInfo> findByPagination(Pageable pageable);
	
	UserInfo registerNewUser(UserRegistrationForm userRegistrationForm)throws UserAlreadyExistAuthenticationException;

}
