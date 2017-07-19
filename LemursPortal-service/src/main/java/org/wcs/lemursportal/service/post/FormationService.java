package org.wcs.lemursportal.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.wcs.lemursportal.model.Formation;

/**
 * @author z
 *
 */
public interface FormationService {
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<Formation> getFormations(Pageable pageable);
	
	/**
	 * 
	 * @param formation
	 */
	//@PreAuthorize("hasRole('ADMIN')")
	public void save(Formation formation, String email);
	
	public Formation getFormation(Long formationId);
	
	public Formation getFormation(Long formationId, String email);

	public void update(Formation formation, String email);

	void deleteById(Long id, String email);
	
}
