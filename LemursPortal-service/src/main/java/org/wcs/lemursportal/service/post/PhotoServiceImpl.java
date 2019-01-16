package org.wcs.lemursportal.service.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.ResponsiveBreakpoint;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.model.post.PhotoBreakpoint;
import org.wcs.lemursportal.repository.post.PhotoBreakpointRepository;
import org.wcs.lemursportal.repository.post.PhotoRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
public class PhotoServiceImpl extends GenericCRUDServiceImpl<Photo, Integer> implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoBreakpointRepository photoBreakpointRepository;

    @Override
    protected JpaRepository<Photo, Integer> getJpaRepository() {
        return photoRepository;
    }

    @Transactional
    @Override
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
        for (PhotoBreakpoint pb : photo.getBreakPoints()) {
            pb.setIdPhoto(photo.getId());
            photoBreakpointRepository.save(pb);
        }
    }

    @Transactional
    private void deletePhotoOnLocal(Photo photo) {
        photoBreakpointRepository.delete(photo.getBreakPoints());
        photoRepository.delete(photo);
    }

    @Transactional
    private void deleteInTheCloud(Photo photo) throws IOException {
        Cloudinary cloudinary = new Cloudinary("cloudinary://865793447284318:Hzz6uCahJt-L7k1YEVT2Pv6pRZw@lemursportal");
        Map cloudinaryResult = cloudinary.uploader().destroy(photo.getName(), null);
        System.out.println(cloudinaryResult.toString());
    }
    
    @Transactional
    @Override
    public void deletePhoto(Photo photo) throws IOException {
        deleteInTheCloud(photo);
        deletePhotoOnLocal(photo);
    }

    @Transactional
    @Override
    public void SaveWithBreakpoints(Photo photo) throws IOException, RuntimeException {
        try {
            Cloudinary cloudinary = new Cloudinary("cloudinary://865793447284318:Hzz6uCahJt-L7k1YEVT2Pv6pRZw@lemursportal");
            File file = new File(photo.getPath());
//            String fileName = photo.getName();
            List<PhotoBreakpoint> breakpoints = new ArrayList();
            Map cloudinaryResult = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap(
                            "responsive_breakpoints", new ResponsiveBreakpoint().createDerived(false).bytesStep(20000).minWidth(50).maxWidth(1000).maxImages(20),
                            "public_id", photo.getName()));
            photo.setLink((String) cloudinaryResult.get("secure_url"));
            photo.setSize((Integer) cloudinaryResult.get("bytes"));
            List<Map> bp = (List) cloudinaryResult.get("responsive_breakpoints");
            for (Map m : (List<Map>) bp.get(0).get("breakpoints")) {
                PhotoBreakpoint b = new PhotoBreakpoint();
                b.setHeight((Integer) m.get("height"));
                b.setWidth((Integer) m.get("width"));
                b.setSize((Integer) m.get("bytes"));
                b.setLink((String) m.get("secure_url"));
                breakpoints.add(b);
            }
            photo.setBreakPoints(breakpoints);
            savePhoto(photo);
        } catch (IOException | RuntimeException ioe) {
            throw ioe;
        }
    }
    
    @Override
    public List<Photo> findAllByMetadata(Photo photo, Pageable pageable) {
        return photoRepository.findByIdMetadata(photo.getIdMetadata(), pageable).getContent();
    }

    @Override
    public List<Photo> findAllByMetadata(Photo photo) {
        return photoRepository.findByIdMetadata(photo.getIdMetadata());
    }
}
