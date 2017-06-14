/**
 * 
 */
package org.wcs.lemursportal.factory;
import org.wcs.lemursportal.model.Formation;
import org.wcs.lemursportal.web.form.FormationForm;

/**
 * @author z
 *
 */
public abstract class FormationInfoFactory {
	
	public static FormationForm toForm(Formation formation){
		FormationForm form = null;
		if( formation != null){
			form = new FormationForm();
			form.setContent(formation.getBody());
			form.setTitle(formation.getTitle());
			form.setDescription(formation.getDescription());
			form.setDraft(formation.getDraft());
			form.setId(formation.getId());
			form.setLastEdit(formation.getLastEdit());
			form.setCreationDate(formation.getCreationDate());
			form.setOwner(formation.getOwnerId());
		}
		return form;
	}
	
	public static Formation toEntity(FormationForm form){
		Formation entity = null;
		if(form != null){
			entity = new Formation();
			entity.setId(form.getId());
			entity.setBody(form.getContent());
			entity.setTitle(form.getTitle());
			entity.setDraft(form.getDraft());
			entity.setOwnerId(form.getOwner());
			
		}
		return entity;
	}
}
