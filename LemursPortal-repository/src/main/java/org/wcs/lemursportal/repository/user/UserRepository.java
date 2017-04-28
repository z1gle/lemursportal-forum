package org.wcs.lemursportal.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{
	
	//@Query("select u from UserInfo as u where u.login=?1")
	UserInfo findByLogin(String login);
	
	//@Query("select u from UserInfo as u where u.login=?1 and u.enabled=?2")
	UserInfo findByLoginAndEnabled(String login, boolean enabled);
}
