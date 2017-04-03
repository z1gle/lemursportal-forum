/**
 * 
 */
package org.wcs.lemursportal.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.wcs.lemursportal.web.form.UserRoleEditForm;

/**
 * @author mikajy.hery
 *
 */
@Component
public class UserRoleEditFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return UserRoleEditForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserRoleEditForm userRoleEditForm = (UserRoleEditForm) target;
		//Validation
		if(userRoleEditForm.getUserId() == null || userRoleEditForm.getUserId() <= 0){
			errors.rejectValue("userId", "validation.user.identifiant.incorrect");
		}
		if(userRoleEditForm.getRoles() == null || userRoleEditForm.getRoles().isEmpty()){
			errors.rejectValue("roles", "validation.user.no.role");
		}
		
	}

}
