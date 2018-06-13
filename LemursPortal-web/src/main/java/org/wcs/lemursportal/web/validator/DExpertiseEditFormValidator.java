/**
 * 
 */
package org.wcs.lemursportal.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.web.form.DExpertiseEditForm;

/**
 * @author z
 *
 */
@Component
public class DExpertiseEditFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return DExpertiseEditForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DExpertiseEditForm dExpertiseEditForm = (DExpertiseEditForm) target;
		//Validation
		if(dExpertiseEditForm.getUserId() == null || dExpertiseEditForm.getUserId() <= 0) {
			errors.rejectValue("userId", "validation.user.identifiant.incorrect");
		}
		
	}

}
