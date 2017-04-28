package org.wcs.lemursportal.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Post;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Repository
@Transactional
public interface PostCrudRepository extends JpaRepository<Post, Integer> {

}
