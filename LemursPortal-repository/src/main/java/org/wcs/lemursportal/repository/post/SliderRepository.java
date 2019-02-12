package org.wcs.lemursportal.repository.post;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.Slider;

/**
 * @zacharie
 *
 */
@Repository
@Transactional
public interface SliderRepository extends JpaRepository<Slider, Integer> {
    
    public List<Slider> findByActivated(Integer activated);
    
    public Page<Slider> findByActivated(Integer activated, Pageable pageable);

}
