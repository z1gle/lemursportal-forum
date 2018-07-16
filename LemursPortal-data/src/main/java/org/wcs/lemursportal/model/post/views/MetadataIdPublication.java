package org.wcs.lemursportal.model.post.views;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.wcs.lemursportal.model.post.parents.MetadataParent;

@Entity
@Table(name = "vue_metadata_id_publication")
@AttributeOverrides({
    @AttributeOverride(name = "url", column = @Column(name = "url")),
    @AttributeOverride(name = "coverage", column = @Column(name = "coverage")),
    @AttributeOverride(name = "description", column = @Column(name = "description")),           
    @AttributeOverride(name = "languageMetadata", column = @Column(name = "language")),           
    @AttributeOverride(name = "relation", column = @Column(name = "relation")),           
    @AttributeOverride(name = "source", column = @Column(name = "source")),           
    @AttributeOverride(name = "subject", column = @Column(name = "subject")),           
    @AttributeOverride(name = "title", column = @Column(name = "title")),           
    @AttributeOverride(name = "dateMetadata", column = @Column(name = "date")),           
    @AttributeOverride(name = "yearMetadata", column = @Column(name = "year")),           
    @AttributeOverride(name = "format", column = @Column(name = "format")),           
    @AttributeOverride(name = "fileFormat", column = @Column(name = "fileFormat")),           
    @AttributeOverride(name = "identifier", column = @Column(name = "identifier")),           
    @AttributeOverride(name = "type", column = @Column(name = "type")),           
    @AttributeOverride(name = "contributor", column = @Column(name = "contributor")),           
    @AttributeOverride(name = "creator", column = @Column(name = "creator")),           
    @AttributeOverride(name = "publisher", column = @Column(name = "publisher")),           
    @AttributeOverride(name = "rights", column = @Column(name = "rights")),           
    @AttributeOverride(name = "idDocument", column = @Column(name = "idDocument")),           
    @AttributeOverride(name = "idUtilisateur", column = @Column(name = "idUtilisateur")),           
    @AttributeOverride(name = "bibliographicResource", column = @Column(name = "bibliographicResource"))        
})
public class MetadataIdPublication extends MetadataParent implements Serializable {
    
    @Column(name = "id_publication", insertable = false, updatable = false)
    private Integer idPublication;

    public Integer getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Integer idPublication) {
        this.idPublication = idPublication;
    }    
}
