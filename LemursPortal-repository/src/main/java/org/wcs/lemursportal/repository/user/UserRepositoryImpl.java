package org.wcs.lemursportal.repository.user;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.authentication.AuthenticationRepository#findUserByLogin(java.lang.String)
	 */
	@Override
	public UserInfo findUserByLogin(String login) {
		Query<UserInfo> query = sessionFactory.getCurrentSession().createQuery("select u from UserInfo u where u.login=:login and u.enabled=:enabled", UserInfo.class);
		query.setParameter("login", login);
		query.setParameter("enabled", Boolean.TRUE);
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

	@Override
	public void insert(UserInfo user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	public List<UserInfo> findAll() {
		Query<UserInfo> query = sessionFactory.getCurrentSession().createQuery("select u from UserInfo u where u.enabled=:enabled", UserInfo.class);
		query.setParameter("enabled", Boolean.TRUE);
		List<UserInfo> users = query.getResultList();
		return users;
	}

}
