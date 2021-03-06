package org.wcs.lemursportal.repository.user;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	//@Query("select u from UserInfo as u where u.email=?1")
	UserInfo findByEmail(String email);

	//@Query("select u from UserInfo as u where u.email=?1 and u.enabled=?2")
	UserInfo findByEmailAndEnabled(String email, boolean enabled);

	@Query("select u from UserInfo as u where u.resetToken=?1 and u.expiryDate>?2")
	UserInfo findByResetTokenAndExpiryDate(String email, Date date);

}
