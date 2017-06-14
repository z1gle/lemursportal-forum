/**
 * 
 */
package org.wcs.lemursportal.repository.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.DocumentType;



/**
 * @author mikajy.hery
 *
 */
public interface DocumentTypeRepository {
	
	void insert(DocumentType documentType);
	/**
	 * 
	 * @return
	 */
	List<DocumentType> findAll();
	
	
}
