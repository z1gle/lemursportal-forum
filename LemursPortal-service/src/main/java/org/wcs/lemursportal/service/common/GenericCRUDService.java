package org.wcs.lemursportal.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface GenericCRUDService<T, ID extends Serializable> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	T findById(ID id);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	T saveOrUpdate(T entity);
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	Page<T> findAll(Pageable pageable);
	
	/**
	 * 
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	void delete(ID id);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	void delete(T entity);
}
