/**
 * 
 */
package org.wcs.lemursportal.repository.user;

import java.util.List;

import org.wcs.lemursportal.model.user.UserType;

/**
 * @author mikajy.hery
 *
 */
public interface UserTypeRepository {
	
	void insert(UserType userType);
	/**
	 * 
	 * @return
	 */
	List<UserType> findAll();
}
