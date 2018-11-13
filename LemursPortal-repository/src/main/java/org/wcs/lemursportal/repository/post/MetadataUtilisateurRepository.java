package org.wcs.lemursportal.repository.post;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.MetadataUtilisateur;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface MetadataUtilisateurRepository {

	/**
	 * 
     * @param metadata
     * @param pageable
	 * @return
	 */
            
        Page<MetadataUtilisateur> findAll(Pageable pageable);
        Page<MetadataUtilisateur> findAll(Pageable pageable, MetadataUtilisateur metadata);
        Page<MetadataUtilisateur> findAllExact(Pageable pageable, MetadataUtilisateur metadata);
        Page<MetadataUtilisateur> findAll(Pageable pageable, String type, Integer idThematique);
        public Long conter(String type, Integer idThematique, String search);
        Page<MetadataUtilisateur> findAll(Pageable pageable, MetadataUtilisateur metadata, int orderByYear);
        Page<MetadataUtilisateur> findAll(Pageable pageable, String type, Integer idThematique, int orderByYear);
        Page<MetadataUtilisateur> findGlobal(Pageable pageable, String search);        
        Page<MetadataUtilisateur> findAllNew(String type, int nbr);                                
}
