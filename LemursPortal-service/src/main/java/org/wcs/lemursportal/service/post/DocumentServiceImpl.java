package org.wcs.lemursportal.service.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.ResponsiveBreakpoint;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.repository.post.DocumentCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl extends
        GenericCRUDServiceImpl<Document, Integer> implements DocumentService {

    @Autowired
    DocumentCrudRepository documentCrudRepository;
    @Autowired
    MetadataRepository metadataRepository;
    @Autowired
    PhotoService photoService;
//    //Maybe temporary, it depends on the use of cloudinary or equivalent
//    @Autowired
//    PhotoRepository photoRepository;
//    @Autowired
//    PhotoBreakpointRepository photoBreakpointRepository;

    @Override
    protected JpaRepository<Document, Integer> getJpaRepository() {
        // TODO Auto-generated method stub
        return documentCrudRepository;
    }

    @Override
    public void downloadFile(String filename) {

    }

    @Override
    public Page<Document> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public void addDocument(Metadata metadata) {
        metadataRepository.insert(metadata);
        if (metadata.getPhotos() != null && !metadata.getPhotos().isEmpty()) {
            for (Photo photo : metadata.getPhotos()) {
                try {
                    photo.setIdMetadata(metadata.getId());
                    photoService.SaveWithBreakpoints(photo);
                } catch (IOException | RuntimeException ex) {
                    Logger.getLogger(DocumentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateDocument(Metadata metadata) {
        metadataRepository.update(metadata);
    }

    @Override
    public boolean deleteDocument(Document document) throws Exception {
        String rootPath = this.getClass().getClassLoader().getResource("").getPath().split("WEB-INF" + File.separator + "classes")[0];
        String filePath = rootPath + "resources" + File.separator + "upload" + File.separator + document.getFilename();
        try {
            File file = new File(filePath);
            return file.delete();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteDocumentIrreversible(Document document) throws Exception {
        String rootPath = this.getClass().getClassLoader().getResource("").getPath().split("WEB-INF" + File.separator + "classes")[0];
        String filePath = rootPath + "resources" + File.separator + "upload" + File.separator + document.getFilename();
        try {
            File file = new File(filePath);
            boolean val = file.delete();
            this.getJpaRepository().delete(document.getId());
            return val;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Object addPhoto(String link) throws IOException {
        String name = link.substring(link.lastIndexOf(File.separator) + 1, link.lastIndexOf("."));
        Cloudinary cloudinary = new Cloudinary();
        return cloudinary.uploader().upload(link,
                ObjectUtils.asMap(
                        "responsive_breakpoints",
                        new ResponsiveBreakpoint().createDerived(false).bytesStep(20000).minWidth(50).maxWidth(1000).maxImages(20),
                        name
                )
        );
    }

//    // Just to test cloudinary
//    @Override
//    public List doInBackground(Void... params) throws IOException, RuntimeException {
//        try {
//            Cloudinary cloudinary = new Cloudinary("cloudinary://865793447284318:Hzz6uCahJt-L7k1YEVT2Pv6pRZw@lemursportal");
//            File file = new File("C:\\Users\\WCS\\Documents\\LemursServeurMvn\\forum\\LemursPortal-web\\target\\lemursPortal\\resources\\images\\Bandro_Tobias_Nowlan.JPG");
//            String fileName = file.getName();
//            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
//            
//            Photo photo = new Photo();
//            photo.setName(fileName);
//            
//            List<PhotoBreakpoint> breakpoints = new ArrayList();
//
//            /**
//             * Map cloudinaryResult = cloudinary.uploader().upload(file,
//             * ObjectUtils.asMap("public_id", "profile_img/" + fileName,
//             * "responsive_breakpoints", new
//             * ResponsiveBreakpoint().createDerived(false).bytesStep(20000).minWidth(50).maxWidth(1000).maxImages(20)));
//             * // ObjectUtils.asMap ( // "responsive_breakpoints", // new
//             * ResponsiveBreakpoint().createDerived(false).bytesStep(20000).minWidth(50).maxWidth(1000).maxImages(20),
//             * file)); // Log.d(TAG, cloudinaryResult.toString());
//             *
//             */
//            
//            Map cloudinaryResult = cloudinary.uploader().upload(file,
//                    ObjectUtils.asMap(
//                            "responsive_breakpoints", new ResponsiveBreakpoint().createDerived(false).bytesStep(20000).minWidth(50).maxWidth(1000).maxImages(20),
//                            "public_id", fileName));
//            photo.setLink((String) cloudinaryResult.get("secure_url"));
//            photo.setSize((Integer) cloudinaryResult.get("bytes"));
//            
//            List<Map> bp = (List) cloudinaryResult.get("responsive_breakpoints");
//            for (Map m : (List<Map>) bp.get(0).get("breakpoints")) {
//                PhotoBreakpoint b = new PhotoBreakpoint();
//                b.setHeight((Integer)m.get("height"));
//                b.setWidth((Integer)m.get("width"));
//                b.setSize((Integer)m.get("bytes"));
//                b.setLink((String)m.get("secure_url"));
//                breakpoints.add(b);
//            }
//            
//            photoRepository.save(photo);
//            for (PhotoBreakpoint pb : breakpoints) {
//                photoBreakpointRepository.save(pb);
//            }
//            return breakpoints;
//        } catch (IOException | RuntimeException ioe) {
//            throw ioe;
//        }
//    }
}
