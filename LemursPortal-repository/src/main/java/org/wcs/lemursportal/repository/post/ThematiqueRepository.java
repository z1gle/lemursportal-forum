package org.wcs.lemursportal.repository.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopThematique;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface ThematiqueRepository {
	/**
	 * 
	 * @param size
	 * @return
	 */
	Page<TopThematique> findTopThematique(Pageable pageable);
	/**
	 * 
	 * @return
	 */
	List<TopThematique> findAllThematique();
	
	/**
	 * 
	 * @param questionId
	 * @return
	 */
	Thematique findByQuestionId(Integer questionId);
	
	/**
	 * 
	 * @param thematiqueId
	 * @return
	 */
	Thematique findByIdAndFetchManagers(Integer thematiqueId);
}
