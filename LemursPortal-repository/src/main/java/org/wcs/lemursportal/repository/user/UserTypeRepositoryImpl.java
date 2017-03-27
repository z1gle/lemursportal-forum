/**
 * 
 */
package org.wcs.lemursportal.repository.user;

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
		sessionFactory.getCurrentSession().persist(userType);
	}

}
