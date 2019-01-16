package org.wcs.lemursportal.repository.post;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Photo;

/**
 * @zacharie
 *
 */
@Repository
@Transactional
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    public List<Photo> findByIdMetadata(Integer idMetadata);
    public Page<Photo> findByIdMetadata(Integer idMetadata, Pageable pageable);
}
