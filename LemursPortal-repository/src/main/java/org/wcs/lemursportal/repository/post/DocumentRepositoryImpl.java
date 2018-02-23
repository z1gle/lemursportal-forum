/**
 * 
 */
package org.wcs.lemursportal.repository.post;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Post;





/**
 * @author mikajy.hery
 *
 */

@Repository
@Transactional(readOnly=true)
public class DocumentRepositoryImpl implements DocumentRepository{
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
	@Autowired private DocumentCrudRepository documentCrudRepository;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public Page<Document> findDocumentsbyType(int docTypeId, Pageable pageable) {
		Query query = em.createQuery("from  Document t where  t.type.id=:documentType", Object[].class);
		query.setParameter("documentType", docTypeId);
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		List<Document> results = query.getResultList();
		System.out.println("firy ny isany : "+results.size());
		return new PageImpl<>(results);
		
	}

	@Override
	public List<Document> findAllDocuments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Document> findTopDocuments(Pageable pageable) {
		List<String> typeDoc = Arrays.asList("1","2");
		String s = "from  Document t where  t.type.id=:documentType order by creationDate DESC";
		TypedQuery<Document> query = em.createQuery(s, Document.class);
		query.setParameter("documentType", 1);
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		List<Document> results = query.getResultList();
		
		return new PageImpl<>(results);
	}
	

}
