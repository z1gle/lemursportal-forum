package org.wcs.lemursportal.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Thematique;

/**
 * @author Mikajy <mikajy401@gmail.com>
 * 
 * Utilisé Spring-data jpa repository
 * http://docs.spring.io/spring-data/jpa/docs/1.3.4.RELEASE/reference/html/repositories.html
 */
@Transactional
@Repository
public interface ThematiqueCrudRepository extends JpaRepository<Thematique, Integer> {

}
