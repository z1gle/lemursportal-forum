/**
 * 
 */
package org.wcs.lemursportal.factory;
import java.util.ArrayList;

import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.web.form.UserInfoForm;

/**
 * @author mikajy.hery
 *
 */
public abstract class UserInfoFactory {
	
	public static UserInfoForm toForm(UserInfo user){
		UserInfoForm form = null;
		if( user != null){
			form = new UserInfoForm();
			form.setBiographie(user.getBiographie());
			form.setDateNaissance(user.getDateNaissance());
			form.setEmail(user.getEmail());
			form.setEnabled(user.getEnabled());
			form.setId(user.getId());
			form.setLastAccessDate(user.getLastAccessDate());
			form.setLogin(user.getLogin());
			form.setNom(user.getNom());
//			form.setPassword(user.getP);
			form.setPrenom(user.getPrenom());
			form.setUserTypeIds(new ArrayList<Integer>());
			for(UserType role: user.getRoles()){
				form.getUserTypeIds().add(role.getId());
			}
		}
		return form;
	}
	
	public static UserInfo toEntity(UserInfoForm form){
		UserInfo entity = null;
		if(form != null){
			entity = new UserInfo();
			entity.setId(form.getId());
			entity.setBiographie(form.getBiographie());
			entity.setDateNaissance(form.getDateNaissance());
			entity.setEmail(form.getEmail());
			entity.setEnabled(form.getEnabled());
			//entity.setLastAccessDate(form.getLastAccessDate());
			entity.setLogin(form.getLogin());
			entity.setPassword(form.getPassword());
			entity.setPrenom(form.getPrenom());
			//entity.setRoles(form.get);
			
		}
		return entity;
	}
}
