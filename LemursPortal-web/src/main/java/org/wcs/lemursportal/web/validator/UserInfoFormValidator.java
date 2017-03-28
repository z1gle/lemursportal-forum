/**
 * 
 */
package org.wcs.lemursportal.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.web.form.UserInfoForm;

/**
 * @author mikajy.hery
 *
 */
@Component
public class UserInfoFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		
		return UserInfoForm.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfoForm user = (UserInfoForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "validation.mandatory");
//		if(user.getPassword().length() < 8){
//			
//		}
		if(!user.getPassword().equals(user.getPasswordConfirm())){
			errors.rejectValue("passwordConfirm", "validation.diff.passwordconfirm");
		}
		
	}

}
