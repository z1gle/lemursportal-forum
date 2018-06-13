package org.wcs.lemursportal.repository.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface MetadataRepository {

	/**
	 * 
     * @param metadata
     * @param pageable
	 * @return
	 */
    
//	List<Document> findAllDocuments();
	Page<Document> findDocuments(Metadata metadata, Pageable pageable);
//	Page<Document> findTopDocuments(Pageable pageable);
        void insert(Metadata metadata);	
	
}
