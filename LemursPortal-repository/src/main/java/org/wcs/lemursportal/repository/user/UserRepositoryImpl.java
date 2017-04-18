package org.wcs.lemursportal.repository.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
//	@Autowired
//	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.authentication.AuthenticationRepository#findUserByLogin(java.lang.String)
	 */
	@Override
	public UserInfo findUserByLogin(String login) throws NoResultException {
//		EntityManager em = entityManagerFactory.createEntityManager();
		UserInfo userInfo = null;
		Query query = em.createQuery("from UserInfo as u where u.login=:login and u.enabled=:enabled", UserInfo.class);
		query.setParameter("login", login);
		query.setParameter("enabled", Boolean.TRUE);
		userInfo = (UserInfo)query.getSingleResult();
		return userInfo;
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.UserInfoRepository#update(org.wcs.lemursportal.data.authentication.UserInfo)
	 */
	@Override
	public void update(UserInfo user) {
//		EntityManager em = entityManagerFactory.createEntityManager();
		em.merge(user);
	}

	@Override
	public void insert(UserInfo user) {
//		EntityManager em = entityManagerFactory.createEntityManager();
		em.persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findAll() {
//		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery("from UserInfo as u where u.enabled=:enabled");
		query.setParameter("enabled", Boolean.TRUE);
		List<UserInfo> users = query.getResultList();
		return users;
	}

	@Override
	public boolean isLoginExist(String login) {
//		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<UserInfo> root = criteriaQuery.from(UserInfo.class);
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.equal(root.get("login") , login));
		Long count = em.createQuery(criteriaQuery).getSingleResult();
		return count != null && count > 0L;
	}

	@Override
	public UserInfo findById(Integer id) {
//		EntityManager em = entityManagerFactory.createEntityManager();
		UserInfo userInfo = em.find(UserInfo.class, id);
		return userInfo;
	}

}
