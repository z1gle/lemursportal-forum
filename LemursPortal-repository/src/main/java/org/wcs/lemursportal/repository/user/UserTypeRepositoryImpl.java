/**
 * 
 */
package org.wcs.lemursportal.repository.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository {
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
//	@Autowired
//	private EntityManagerFactory entityManagerFactory;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.user.UserTypeRepository#insert(org.wcs.lemursportal.data.user.UserType)
	 */
	@Override
	public void insert(UserType userType) {
//		EntityManager em = entityManagerFactory.createEntityManager();
		em.persist(userType);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<UserType> findAll() {
//		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery("from UserType");
		List<UserType> results = query.getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<UserInfo> findUsers(UserRole userRole) {
		Query query = em.createQuery("Select t.users from  UserType t where  t.id=:usertype", Object[].class);
		query.setParameter("usertype", userRole.getId());
		List<UserInfo> results = query.getResultList();
		return results;
	}

}
