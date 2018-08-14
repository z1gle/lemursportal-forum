package org.wcs.lemursportal.model.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Haritiana <r.haritiana.z@gmail.com>
 *
 */
@Entity
@Table(name = "user_view")
public class UserView {

    /**
     *
     */
    private static final long serialVersionUID = 7606421457192166667L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "nbr_document")
    private Integer nbrDocument;

    @Column(name = "commentaire")
    private String commentaire;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getNbrDocument() {
        return nbrDocument;
    }

    public void setNbrDocument(Integer nbrDocument) {
        this.nbrDocument = nbrDocument;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}
