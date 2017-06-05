package org.wcs.lemursportal.web.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class FileBucket {
	
	MultipartFile file;
    
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
