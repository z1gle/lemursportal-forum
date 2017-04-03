/**
 * 
 */
package org.wcs.lemursportal.web.form;

import java.util.ArrayList;
import java.util.List;

import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;

/**
 * @author mikajy.hery
 *
 */
public class UserRoleEditForm {
	
	private Integer userId;
	private List<Integer> roles;
	
	public UserRoleEditForm(){}
	
	public UserRoleEditForm(UserInfo user){
		this.userId = user.getId();
		roles = new ArrayList<Integer>();
		if(user.getRoles() != null){
			for(UserType role: user.getRoles()){
				roles.add(role.getId());
			}
		}
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	
}
