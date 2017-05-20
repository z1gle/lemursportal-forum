/**
 * 
 */
package org.wcs.lemursportal.repository.user;

import java.util.List;

import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.user.UserInfo;
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
	List<UserInfo> findUsers(UserRole userRole);
	
}
