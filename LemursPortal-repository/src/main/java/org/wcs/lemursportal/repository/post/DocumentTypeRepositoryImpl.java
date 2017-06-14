/**
 * 
 */
package org.wcs.lemursportal.repository.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.DocumentType;




/**
 * @author mikajy.hery
 *
 */

@Repository
@Transactional(readOnly=true)
public class DocumentTypeRepositoryImpl implements DocumentTypeRepository{

	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
	@Override
	public void insert(DocumentType documentType) {
		em.persist(documentType);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<DocumentType> findAll() {
		Query query = em.createQuery("from DocumentType");
		List<DocumentType> results = query.getResultList();
		return results;
	}

}
