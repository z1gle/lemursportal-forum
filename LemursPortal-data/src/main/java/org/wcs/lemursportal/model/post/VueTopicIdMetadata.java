package org.wcs.lemursportal.model.post;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name = "vue_topic_id_metadata")
public class VueTopicIdMetadata implements Serializable {

    private static final long serialVersionUID = -7731302369156672050L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "libelle", unique = true, nullable = false)
    private String libelle;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "date_creation", nullable = false)
    private Date creationDate;

    @Column(name = "date_modif", nullable = true)
    private Date editDate;

    @Column(name = "id_metadata", nullable = true)
    private Integer idMetadata;

    @Column(name = "created_by")
    private Integer idCreator;

    @Column(name = "modified_by")
    private Integer idModificator;           

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getIdMetadata() {
        return idMetadata;
    }

    public void setIdMetadata(Integer idMetadata) {
        this.idMetadata = idMetadata;
    }

    public Integer getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Integer idCreator) {
        this.idCreator = idCreator;
    }

    public Integer getIdModificator() {
        return idModificator;
    }

    public void setIdModificator(Integer idModificator) {
        this.idModificator = idModificator;
    }   

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof VueTopicIdMetadata)) {
            return false;
        }

        VueTopicIdMetadata thematique = (VueTopicIdMetadata) o;
        return Objects.equals(id, thematique.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }    
}
