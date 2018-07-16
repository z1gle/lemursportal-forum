package org.wcs.lemursportal.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.VueTopicIdMetadata;

/**
 * @zacharie
 *
 */
@Repository
@Transactional
public interface VueTopicIdMetadataCrudRepository extends
        JpaRepository<VueTopicIdMetadata, Integer> {

}
