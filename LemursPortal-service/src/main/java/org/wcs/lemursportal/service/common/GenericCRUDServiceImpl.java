package org.wcs.lemursportal.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericCRUDServiceImpl<T, ID extends Serializable> implements GenericCRUDService<T, ID> {
	
	
	protected abstract JpaRepository<T, ID> getJpaRepository();

	@Override
	public T saveOrUpdate(T entity) {
		return getJpaRepository().save(entity);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		Page<T> page = getJpaRepository().findAll(pageable);
		return page;
	}

	@Override
	public List<T> findAll() {
		List<T> all = getJpaRepository().findAll();
		return all;
	}

	@Override
	public void delete(ID id) {
		getJpaRepository().delete(id);
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.common.GenericCRUDService#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) {
		getJpaRepository().delete(entity);
	}

	@Override
	public T findById(ID id) {
		return getJpaRepository().findOne(id);
	}

}
