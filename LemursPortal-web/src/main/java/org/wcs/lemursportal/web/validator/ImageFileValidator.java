package org.wcs.lemursportal.web.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.web.form.FileBucket;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Component
public class ImageFileValidator implements Validator {
	
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return FileBucket.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors errors) {
		FileBucket fileBucket = (FileBucket) arg0;
		if(fileBucket != null && fileBucket.getFile() != null){
			if(!contentTypes.contains(fileBucket.getFile().getContentType())){
				errors.rejectValue("file", "signup.photoprofil.contenttype.error", "Image !");
			}
		}
		
        
        /*if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }*/

	}

}
