package org.wcs.lemursportal.repository.user;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserRepositoryImpl implements UserRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.authentication.AuthenticationRepository#findUserByLogin(java.lang.String)
	 */
	@Override
	public UserInfo findUserByLogin(String login) {
		UserInfo userInfo;
		/*CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<UserInfo> criteria = criteriaBuilder.createQuery(UserInfo.class);
		Root<UserInfo> root = criteria.from(UserInfo.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.and(
				criteriaBuilder.equal(root.get("login"), login), 
				criteriaBuilder.equal(root.get("enabled"), Boolean.TRUE)));
		userInfo = sessionFactory.createEntityManager().createQuery(criteria).getSingleResult();
		*/
		try{
			Query<UserInfo> query = sessionFactory.getCurrentSession()
						.createQuery("from UserInfo as u where u.login=:login and u.enabled=:enabled", UserInfo.class);
			query.setParameter("login", login);
			query.setParameter("enabled", Boolean.TRUE);
			userInfo = query.getSingleResult();
			LOGGER.info(String.format("One user found for login '%s'", login));
		}catch(NoResultException e){
			userInfo = null;
			LOGGER.debug(String.format("No user found for login '%s'", login));
		}/*catch(NonUniqueResultException e){
			userInfo = null;
		}*/
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

	@Override
	public boolean isLoginExist(String login) {
		
		return false;
	}

	@Override
	public UserInfo findById(Integer id) {
		UserInfo userInfo = sessionFactory.getCurrentSession().load(UserInfo.class, id);
		return userInfo;
	}

}
