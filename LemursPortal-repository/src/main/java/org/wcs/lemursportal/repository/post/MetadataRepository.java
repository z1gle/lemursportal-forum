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
    
        List<String> findOneElementOfMetadata(Metadata metadata);
        Page<Metadata> findAll(Pageable pageable);
        Page<Metadata> findAll(Pageable pageable, Metadata metadata);
        Page<Metadata> findAll(Pageable pageable, Metadata metadata, int orderByYear);
        Page<Metadata> findAllNew(String type, int nbr);
        Page<Metadata> findAll(Pageable pageable, String type, Integer idThematique);
        Page<Metadata> findAll(Pageable pageable, String type, Integer idThematique, int orderByYear);
        Page<Metadata> findAllExact(Pageable pageable, Metadata metadata);
        Page<Metadata> findGlobal(Pageable pageable, String search);
//        Metadata findById(Integer id) throws Exception;
	Page<Document> findDocuments(Metadata metadata, Pageable pageable);
//	Page<Document> findTopDocuments(Pageable pageable);
        void insert(Metadata metadata);	
        void delete(Metadata metadata);	
        void update(Metadata metadata);	
        public Long conter(String type, Integer idThematique);
	
}
