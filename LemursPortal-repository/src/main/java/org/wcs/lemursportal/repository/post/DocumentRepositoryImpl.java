/**
 * 
 */
package org.wcs.lemursportal.repository.post;

import java.util.Arrays;
import java.util.Date;
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
import org.wcs.lemursportal.model.post.DocumentType;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;





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
		Query query = em.createQuery("from  Document t where  t.type.id=:documentType and (t.deleted=:notDeleted or t.deleted is null) ", Object[].class);
		query.setParameter("documentType", docTypeId);
		query.setParameter("notDeleted", false);
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
		String s = "from  Document t where  (t.typeId=:imageId or t.typeId=:videoId ) and (t.deleted=:notDeleted or t.deleted is null) order by creationDate DESC";
		TypedQuery<Document> query = em.createQuery(s, Document.class);
		query.setParameter("imageId", 1);
		query.setParameter("videoId", 2);
		query.setParameter("notDeleted", false);
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		List<Document> results = query.getResultList();
		
		return new PageImpl<>(results);
	}

	@Override
	public int deleteDocument(int docId) {
		StringBuilder jpql = new StringBuilder("update Document d  set d.deleted=:deleted where d.id=:docId ");
		Query query = em.createQuery(jpql.toString());
		query.setParameter("deleted", true);
		query.setParameter("docId", docId);
		int nbUpdated = query.executeUpdate();
		return nbUpdated;
	}
	

}
