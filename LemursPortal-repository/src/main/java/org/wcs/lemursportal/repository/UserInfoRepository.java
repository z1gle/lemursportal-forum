package org.wcs.lemursportal.repository;

import org.wcs.lemursportal.data.authentication.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface UserInfoRepository {
	/**
	 * 
	 * @param login
	 * @return
	 */
	UserInfo findUserByLogin(String login);
	
	/**
	 * 
	 * @param user
	 */
	void update(UserInfo user);
}
