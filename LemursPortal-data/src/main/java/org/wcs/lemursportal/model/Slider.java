package org.wcs.lemursportal.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.wcs.lemursportal.model.post.Photo;


@Entity
@Table(name = "slider")
public class Slider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "texts", insertable = true, updatable = true, length = 250)
    private String text;
    
    @Column(name = "title", insertable = true, updatable = true, length = 250)
    private String title;
    
//    @Column(name = "img_path", insertable = true, updatable = true, length = 250)
//    private String img;
    
    @Column(name = "activated", insertable = true, updatable = true)
    private Integer activated;

    // It's not linked to the class Metadata yet
    @Column(name = "id_user", insertable = true, updatable = true)
    private Integer idUser;
    
    @Column(name = "id_photo", insertable = true, updatable = true)
    private Integer idPhoto;
    
    @Transient
    private Photo photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
