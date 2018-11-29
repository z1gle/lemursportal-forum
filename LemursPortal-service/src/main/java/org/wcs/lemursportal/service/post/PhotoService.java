package org.wcs.lemursportal.service.post;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.service.common.GenericCRUDService;

public interface PhotoService extends GenericCRUDService<Photo, Integer> {

    public void savePhoto(Photo photo);
    
    public void deletePhoto(Photo photo) throws IOException;
    
    public void SaveWithBreakpoints(Photo photo) throws IOException, RuntimeException;
    
    public List<Photo> findAllByMetadata(Photo photo, Pageable pageable);
    
    public List<Photo> findAllByMetadata(Photo photo);
}
