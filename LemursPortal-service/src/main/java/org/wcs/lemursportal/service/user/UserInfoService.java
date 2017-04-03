/**
 * 
 */
package org.wcs.lemursportal.service.user;

import java.util.Set;

import org.wcs.lemursportal.helper.pagination.PaginationRequest;
import org.wcs.lemursportal.helper.pagination.PaginationResponse;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;

/**
 * @author mikajy.hery
 *
 */
public interface UserInfoService {
	
	void updateUserRoles(Integer userId, Set<UserType> roles);
	/**
	 * 
	 * @param id
	 * @return
	 */
	UserInfo getById(Integer id);
	/**
	 * 
	 * @param user
	 */
	void save(UserInfo user);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	PaginationResponse<UserInfo> findByPagination(PaginationRequest<UserInfo> request);
}
