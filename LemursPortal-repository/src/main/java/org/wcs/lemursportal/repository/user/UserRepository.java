package org.wcs.lemursportal.repository.user;

import java.util.List;

import org.wcs.lemursportal.data.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface UserRepository {
	/**
	 * 
	 * @return
	 */
	List<UserInfo> findAll();
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
	/**
	 * 
	 * @param user
	 */
	void insert(UserInfo user);
}
