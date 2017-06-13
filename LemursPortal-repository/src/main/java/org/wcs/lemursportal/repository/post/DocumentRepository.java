package org.wcs.lemursportal.repository.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Document;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface DocumentRepository {

	/**
	 * 
	 * @return
	 */
	List<Document> findAllDocuments();
	Page<Document> findDocumentsbyType(int docTypeId, Pageable pageable);
}
