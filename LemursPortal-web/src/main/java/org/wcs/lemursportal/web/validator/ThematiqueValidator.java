package org.wcs.lemursportal.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Component
public class ThematiqueValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return Thematique.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors errors) {
		Thematique thematique = (Thematique) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "validation.mandatory");
		if(thematique.getManagers() == null){
			errors.rejectValue("managers", "validation.mandatory");
		}else{
			boolean hasAtLeastOneManager = false;
			for(UserInfo manager: thematique.getManagers()){
				if(manager.getId() != null && manager.getId() > 0){
					hasAtLeastOneManager = true;
					break;
				}
			}
			if(!hasAtLeastOneManager){
				errors.rejectValue("managers", "validation.mandatory");
			}
		}

	}

}
