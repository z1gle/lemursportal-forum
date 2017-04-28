package org.wcs.lemursportal.repository.post;

import java.util.List;

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
	List<TopThematique> findTopThematique(Integer size);
}
