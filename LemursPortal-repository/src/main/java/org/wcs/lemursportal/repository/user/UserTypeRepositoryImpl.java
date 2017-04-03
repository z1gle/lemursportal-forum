/**
 * 
 */
package org.wcs.lemursportal.repository.user;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.user.UserType;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.user.UserTypeRepository#insert(org.wcs.lemursportal.data.user.UserType)
	 */
	@Override
	public void insert(UserType userType) {
		sessionFactory.getCurrentSession().saveOrUpdate(userType);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<UserType> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserType");
		List<UserType> results = query.getResultList();
		return results;
	}

}
