package org.wcs.lemursportal.repository.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.Formation;

/**
 * @author z
 *
 */
@Repository
@Transactional(readOnly=true)
public class FormationRepositoryImpl implements FormationRepository {
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
	@Autowired 
	private FormationCrudRepository formationCrudRepository;
	
	@Override
	public Page<Formation> getLastestFormations(Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Formation> select = builder.createQuery(Formation.class);
		Root<Formation> from = select.from(Formation.class);
		select.select(from);
		select.orderBy(builder.desc(from.get("creationDate")));
		TypedQuery<Formation> typedQuery = em.createQuery(select);
		if(pageable != null){
			typedQuery.setFirstResult(pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
		}
		List<Formation> posts = typedQuery.getResultList();
		return new PageImpl<Formation>(posts);
	}

	public void add(Formation formation) {
		formationCrudRepository.save(formation);
	}
}
