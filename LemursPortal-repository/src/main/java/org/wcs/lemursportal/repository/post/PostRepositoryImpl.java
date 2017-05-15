package org.wcs.lemursportal.repository.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.TopQuestion;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Repository
@Transactional(readOnly=true)
public class PostRepositoryImpl implements PostRepository {
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getLastOrphanPosts(java.lang.Integer)
	 */
	@Override
	public Page<Post> getLastestPosts(Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Post> select = builder.createQuery(Post.class);
		Root<Post> from = select.from(Post.class);
		select.select(from);
		select.where(from.get("parent").isNull());
		select.orderBy(builder.desc(from.get("creationDate")));
		TypedQuery<Post> typedQuery = em.createQuery(select);
		if(pageable != null){
			typedQuery.setFirstResult(pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
		}
		List<Post> posts = typedQuery.getResultList();
		return new PageImpl<Post>(posts);
	}


	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getMostViewedPost(org.springframework.data.domain.Pageable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<TopQuestion> getMostViewedPosts(Pageable pageable) {
		StringBuilder jpql = new StringBuilder("select p, count(v.id) as nbVue from PostView as v");
		jpql.append(" inner join v.post as p ");
		jpql.append(" inner join fetch p.owner u ");
		jpql.append(" group by p.id, u.id");
		jpql.append(" order by nbVue desc");
		Query query = em.createQuery(jpql.toString());
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		List<Object[]> results = query.getResultList();
		List<TopQuestion> mostViewedPost = new ArrayList<>();
		for(Object[] array: results){
			Post p = (Post)array[0];
			//p.getOwner().getNom();//juste pour s'assurer qu'on a bien l'objet owner
			Long nbVue = (Long)array[1];
			TopQuestion topQuestion = new TopQuestion();
			topQuestion.setQuestion(p);
			topQuestion.setNbVue(nbVue);
			mostViewedPost.add(topQuestion);
		}
		return new PageImpl<TopQuestion>(mostViewedPost);
	}

	public void insert(Post p){
		em.persist(p);
	}
}
