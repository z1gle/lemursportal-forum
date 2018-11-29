package org.wcs.lemursportal.service.post;

import java.io.IOException;
import java.util.List;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.service.common.GenericCRUDService;

public interface DocumentService extends GenericCRUDService<Document, Integer> {
	public void downloadFile(String filename);        
        public void addDocument(Metadata metadata);
        public void updateDocument(Metadata metadata);
        public boolean deleteDocument(Document document) throws Exception;
        public boolean deleteDocumentIrreversible(Document document) throws Exception;
        
        //test photo
        public Object addPhoto(String link) throws IOException;
//        public List doInBackground(Void... params) throws IOException, RuntimeException;
}
