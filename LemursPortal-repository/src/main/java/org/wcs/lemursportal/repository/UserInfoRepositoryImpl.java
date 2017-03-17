package org.wcs.lemursportal.repository;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.data.authentication.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class UserInfoRepositoryImpl implements UserInfoRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.authentication.AuthenticationRepository#findUserByLogin(java.lang.String)
	 */
	@Override
	public UserInfo findUserByLogin(String login) {
		Query<UserInfo> query = sessionFactory.getCurrentSession().createQuery("select u from UserInfo u where u.login=:login", UserInfo.class);
		query.setParameter("login", login);
		UserInfo userInfo = query.getSingleResult();
		return userInfo;
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.UserInfoRepository#update(org.wcs.lemursportal.data.authentication.UserInfo)
	 */
	@Override
	public void update(UserInfo user) {
		sessionFactory.getCurrentSession().update(user);
	}

}
