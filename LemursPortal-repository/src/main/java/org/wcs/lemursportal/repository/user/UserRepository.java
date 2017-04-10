package org.wcs.lemursportal.repository.user;

import java.util.List;

import javax.persistence.NoResultException;

import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface UserRepository /*extends JpaRepository<UserInfo, Integer>*/{
	
	/**
	 * 
	 * @param login
	 * @return
	 */
	public boolean isLoginExist(String login);
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
	UserInfo findUserByLogin(String login) throws NoResultException;
	
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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	UserInfo findById(Integer id);
}
