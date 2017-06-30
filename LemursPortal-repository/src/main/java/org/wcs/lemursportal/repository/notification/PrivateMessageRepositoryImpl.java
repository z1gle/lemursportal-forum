/**
 * 
 */
package org.wcs.lemursportal.repository.notification;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.notification.Notification;
import org.wcs.lemursportal.model.notification.PrivateMessage;

/**
 * @author mikajy.hery
 *
 */
@Repository
@Transactional
public class PrivateMessageRepositoryImpl implements PrivateMessageRepository{
	
	@PersistenceContext
	protected EntityManager em;

	@Transactional(readOnly=false)
	@Override
	public void save(PrivateMessage message) {
		em.persist(message);
	}

	@Transactional(readOnly=false)
	@Override
	public void update(PrivateMessage message) {
		em.merge(message);
	}

	@Override
	public PrivateMessage findById(Integer id) {
//		PrivateMessage message = em.find(PrivateMessage.class, id);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PrivateMessage> select = builder.createQuery(PrivateMessage.class);
		Root<PrivateMessage> from = select.from(PrivateMessage.class);
		from.fetch("sender");
		select.select(from);
		select.where(builder.and(
				builder.equal(from.get("id"), id)			
		));
		TypedQuery<PrivateMessage> typedQuery = em.createQuery(select);
		PrivateMessage message = typedQuery.getSingleResult();
		return message;
	}

	@Override
	public Page<PrivateMessage> findByDestinataire(Integer destinataireId, Pageable pageable) {
		Long total = countByDestinataire(destinataireId);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PrivateMessage> select = builder.createQuery(PrivateMessage.class);
		Root<PrivateMessage> from = select.from(PrivateMessage.class);
		from.fetch("sender");
		select.select(from);
		select.where(builder.and(
				builder.equal(from.get("destinataireId"), destinataireId),
				builder.notEqual(from.get("readByDestinataire"), true)				
		));
		select.orderBy(builder.desc(from.get("date")));
		TypedQuery<PrivateMessage> typedQuery = em.createQuery(select);
		if(pageable != null){
			typedQuery.setFirstResult(pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
		}
		List<PrivateMessage> messages = typedQuery.getResultList();
		return new PageImpl<>(messages, pageable, total);
	}

	@Override
	public Long countByDestinataire(Integer destinataireId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> select = builder.createQuery(Long.class);
		Root<PrivateMessage> from = select.from(PrivateMessage.class);
		select.select(builder.countDistinct(from));
		select.where(builder.and(
				builder.equal(from.get("destinataireId"), destinataireId),
				builder.notEqual(from.get("readByDestinataire"), true)				
		));
		Long count = em.createQuery(select).getSingleResult();
		return count;
	}

}
