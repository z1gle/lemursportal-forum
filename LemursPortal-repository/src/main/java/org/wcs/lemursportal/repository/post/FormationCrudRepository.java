package org.wcs.lemursportal.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.Formation;
import org.wcs.lemursportal.model.post.Post;

/**
 * @author z
 *
 */
@Repository
@Transactional
public interface FormationCrudRepository extends JpaRepository<Formation, Long> {

}
