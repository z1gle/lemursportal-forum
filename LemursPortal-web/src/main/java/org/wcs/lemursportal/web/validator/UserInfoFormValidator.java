/**
 * 
 */
package org.wcs.lemursportal.web.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.web.form.RegistrationForm;

/**
 * @author mikajy.hery
 *
 */
@Component
public class UserInfoFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		
		return RegistrationForm.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		RegistrationForm user = (RegistrationForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "validation.mandatory");
//		if(user.getPassword().length() < 8){
//			
//		}
		if(StringUtils.isNotEmpty(user.getEmail()) && !EmailValidator.getInstance().isValid(user.getEmail())){
			errors.rejectValue("email", "validation.email.format.invalid", "Invalid email");
		}
		if(!user.getPassword().equals(user.getPasswordConfirm())){
			errors.rejectValue("passwordConfirm", "validation.diff.passwordconfirm");
		}
		
	}

}
