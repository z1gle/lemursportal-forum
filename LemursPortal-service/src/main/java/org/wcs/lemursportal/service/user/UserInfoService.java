/**
 * 
 */
package org.wcs.lemursportal.service.user;

import org.wcs.lemursportal.helper.pagination.PaginationRequest;
import org.wcs.lemursportal.helper.pagination.PaginationResponse;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author mikajy.hery
 *
 */
public interface UserInfoService {
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
