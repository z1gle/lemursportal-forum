package org.wcs.lemursportal.service.post;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.Slider;

public interface SliderService {

    public void save(Slider slider) throws IOException;
    
    public void loadPhoto(Slider slider);
    
    public Slider findById(Integer id);
    
    public List<Slider> findAll();
    
    public List<Slider> findAll(Slider slider);
    
    public Page<Slider> findAll(Pageable pageable);
    
    public Page<Slider> findAll(Slider slider, Pageable pageable);
    
    public List<Slider> findByActivated(Integer activated);
    
    public Page<Slider> findByActivated(Integer activated, Pageable pageable);
    
    public void delete(Slider slider) throws IOException;
}
