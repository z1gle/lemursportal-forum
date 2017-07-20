/**
 * 
 */
package org.wcs.lemursportal.repository.mail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.mail.MailQueue;

/**
 * @author mikajy.hery
 *
 */
@Repository
@Transactional
public class MailQueueRepositoryImpl implements MailQueueRepository {
	
	@PersistenceContext
	protected EntityManager em;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.mail.MailQueueRepository#save(org.wcs.lemursportal.model.mail.MailQueue)
	 */
	@Override
	public void save(MailQueue queue) {
		em.persist(queue);
	}
	
	@Override
	public void update(MailQueue queue) {
		em.merge(queue);
	}
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.mail.MailQueueRepository#findAllNotSent()
	 */
	@Override
	public List<MailQueue> findAllNotSent() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MailQueue> select = builder.createQuery(MailQueue.class);
		Root<MailQueue> from = select.from(MailQueue.class);
		select.select(from);
		select.where(builder.or(builder.equal(from.get("sent"), false), from.get("sent").isNull()));
		select.orderBy(builder.desc(from.get("creationDate")));
		TypedQuery<MailQueue> typedQuery = em.createQuery(select);
		List<MailQueue> mailQueues = typedQuery.getResultList();
		return mailQueues;
	}

}
