/**
 * 
 */
package org.wcs.lemursportal.web.form;

import java.util.ArrayList;
import java.util.List;

import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;

/**
 * @author z
 *
 */
public class DExpertiseEditForm {
	
	private Integer userId;
	private String title;
	private List<Integer> dExpertise;
	private UserInfo user;
	
	public DExpertiseEditForm() {}
	
	public DExpertiseEditForm(UserInfo user) {
		this.user = user;
		this.userId = user.getId();
		dExpertise = new ArrayList<Integer>();
		if(user.getdExpertise() != null){
			for(Thematique thematique: user.getdExpertise()) {
				dExpertise.add(thematique.getId());
			}
		}
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public List<Integer> getdExpertise() {
		return dExpertise;
	}

	public void setdExpertise(List<Integer> dExpertise) {
		this.dExpertise = dExpertise;
	}

	public UserInfo getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
