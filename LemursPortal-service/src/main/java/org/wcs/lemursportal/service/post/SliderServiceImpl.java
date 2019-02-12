package org.wcs.lemursportal.service.post;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.Slider;
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.repository.post.SliderRepository;

/**
 * @author Zacharie <r.haritiana.z@gmail.com>
 *
 */
@Service
@Transactional
public class SliderServiceImpl implements SliderService {

    @Autowired
    SliderRepository sliderRepository;

    @Autowired
    PhotoService photoService;

    @Override
    public void save(Slider slider) throws IOException {
        if (slider.getPhoto() != null) {
            photoService.SaveWithBreakpoints(slider.getPhoto());
            slider.setIdPhoto(slider.getPhoto().getId());
        }
        sliderRepository.save(slider);
    }

    @Override
    public void loadPhoto(Slider slider) {
        slider.setPhoto(photoService.findById(slider.getIdPhoto()));
    }

    @Override
    public Slider findById(Integer id) {
        return sliderRepository.findOne(id);
    }

    @Override
    public List<Slider> findAll() {
        return sliderRepository.findAll();
    }

    @Override
    public List<Slider> findAll(Slider slider) {
        return sliderRepository.findAll(Example.of(slider));
    }

    @Override
    public Page<Slider> findAll(Pageable pageable) {
        return sliderRepository.findAll(pageable);
    }

    @Override
    public Page<Slider> findAll(Slider slider, Pageable pageable) {
        return sliderRepository.findAll(Example.of(slider), pageable);
    }

    @Override
    public List<Slider> findByActivated(Integer activated) {
        return sliderRepository.findByActivated(activated);
    }

    @Override
    public Page<Slider> findByActivated(Integer activated, Pageable pageable) {
        return sliderRepository.findByActivated(activated, pageable);
    }

    @Override
    @Transactional
    public void delete(Slider slider) throws IOException {
        loadPhoto(slider);
        Photo photo = slider.getPhoto();
        sliderRepository.delete(slider);
        photoService.delete(slider.getPhoto());
    }
}
