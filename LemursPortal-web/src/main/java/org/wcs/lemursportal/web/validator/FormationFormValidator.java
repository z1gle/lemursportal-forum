package org.wcs.lemursportal.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.web.form.FormationForm;

/**
 * @author z
 *
 */
@Component
public class FormationFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		
		return FormationForm.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FormationForm formation = (FormationForm)target;
		if(formation.getTitle() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "validation.mandatory");
		}
		
	}

}
