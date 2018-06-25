/**
 * 
 */
package org.wcs.lemursportal.factory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.web.form.ChangePasswordForm;
import org.wcs.lemursportal.web.form.RegistrationForm;

/**
 * @author mikajy.hery
 *
 */
public abstract class UserInfoFactory {
	
	public static RegistrationForm toForm(UserInfo user){
		RegistrationForm form = null;
		if( user != null){
			form = new RegistrationForm();
			form.setBiographie(user.getBiographie());
			form.setPublication(user.getPublication());
			form.setDateNaissance(user.getDateNaissance());
			form.setEmail(user.getEmail());
			form.setEnabled(user.getEnabled());
			form.setId(user.getId());
			form.setLastAccessDate(user.getLastAccessDate());
			form.setNom(user.getNom());
//			form.setPassword(user.getP);
			form.setInstitution(user.getInstitution());
			form.setPostOccupe(user.getPostOccupe());
			form.setPrenom(user.getPrenom());
			form.setUserTypeIds(new ArrayList<Integer>());
			form.setPhotoProfil(user.getPhotoProfil());
			form.setTitle(user.getTitle());
			for(UserType role: user.getRoles()){
				form.getUserTypeIds().add(role.getId());
			}
			List<Integer> dExpertises = new ArrayList<Integer>();
			if(user.getdExpertise() != null){
				for(Thematique dExpertise: user.getdExpertise()){
					dExpertises.add(dExpertise.getId());
				}
			}
			form.setdExpertises(dExpertises);
		}
		return form;
	}
	
	public static UserInfo toEntity(RegistrationForm form){
		UserInfo entity = null;
		if(form != null) {
			entity = new UserInfo();
			entity.setId(form.getId());
			entity.setBiographie(form.getBiographie());
			entity.setPublication(form.getPublication());
			entity.setDateNaissance(form.getDateNaissance());
			entity.setEmail(form.getEmail());
			entity.setEnabled(form.getEnabled());
			//entity.setLastAccessDate(form.getLastAccessDate());
			entity.setPassword(form.getPassword());
			entity.setPrenom(form.getPrenom());
			entity.setNom(form.getNom());
			entity.setInstitution(form.getInstitution());
			entity.setPostOccupe(form.getPostOccupe());
			entity.setPhotoProfil(form.getPhotoProfil());
			entity.setTitle(form.getTitle());
			//entity.setRoles(form.get);

//			Set<Thematique> dExpertises = new HashSet();
//			if(form.getdExpertises() != null){
//				for(Integer id: form.getdExpertises()){
//					Thematique dExpertise = new Thematique();
//					dExpertise.setId(id);
//					dExpertises.add(dExpertise);
//				}
//			}
//			entity.setdExpertise(dExpertises);
		}
		return entity;
	}

    //Added by Zacharie: For changing password
    public static UserInfo toEntity(ChangePasswordForm form) {
        UserInfo entity = null;
        if (form != null) {
            entity = new UserInfo();
            entity.setId(form.getId());            
            entity.setPassword(form.getPassword());            
        }
        return entity;
    }
    
    public static ChangePasswordForm toChangePasswordForm(UserInfo user) {
        ChangePasswordForm form = null;
        if (user != null) {
            form = new ChangePasswordForm();            
            form.setId(user.getId());            
        }
        return form;
    }
}
