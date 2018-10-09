package org.wcs.lemursportal.model.post;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name = "message")
public class Post implements Serializable {

    private static final long serialVersionUID = -1722178560929257653L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title"/*, nullable=false*/)
    private String title;

    @Column(name = "contenu", columnDefinition = "text"/*, nullable=true*/)
    private String body;

    @Column(name = "date_creation", nullable = false)
    private Date creationDate;

    @Column(name = "owner_id", insertable = true, updatable = true)
    private Integer ownerId;

    @Column(name = "parent_id", insertable = true, updatable = true, nullable = true)
    private Integer parentId;

//	@Column(name="document_id", insertable=true, updatable=true, nullable=true)
//	private Integer documentId;
//	
//	@ManyToOne(fetch=FetchType.EAGER , optional=true)
//	@JoinColumn(name="document_id", insertable=false, updatable=false)
//	private Document document = null;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Document> documents = null;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserInfo owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Post parent = null;

    @OneToMany(mappedBy = "parent")
    private List<Post> children;

    @Column(name = "thematique_id", nullable = false)
    private Integer thematiqueId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "thematique_id", insertable = false, updatable = false)
    private Thematique thematique;

    @Column(name = "censored", nullable = true)
    private Boolean censored;

    @Column(name = "censored_date", nullable = true)
    private Date censoredDate;

    @Column(name = "uriYoutube")
    private String uriYoutube;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "integer", name = "censored_by", nullable = true)
    private UserInfo censoredBy;//l'utilisateur(moderateur) qui a bloqué ce POST

//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="message_document", 
//			joinColumns = {@JoinColumn(name="id_message", referencedColumnName = "id")},
//			inverseJoinColumns= {@JoinColumn(name = "id_document", referencedColumnName = "id")}
//		)
//	private List<Document> documents; //list of attachments
    public Boolean deleted = false;

    @Column(name = "deleted_by", nullable = true)
    private Integer deletedBy;

    @Column(name = "deleted_date", nullable = true)
    private Date deletedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public Thematique getThematique() {
        return thematique;
    }

    public void setThematique(Thematique thematique) {
        this.thematique = thematique;
    }

    public UserInfo getOwner() {
        return owner;
    }

    public void setOwner(UserInfo owner) {
        this.owner = owner;
    }

    public Boolean getCensored() {
        return censored;
    }

    public void setCensored(Boolean censored) {
        this.censored = censored;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCensoredDate() {
        return censoredDate;
    }

    public void setCensoredDate(Date censoredDate) {
        this.censoredDate = censoredDate;
    }

    public UserInfo getCensoredBy() {
        return censoredBy;
    }

    public void setCensoredBy(UserInfo censoredBy) {
        this.censoredBy = censoredBy;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Post> getChildren() {
        return children;
    }

    public void setChildren(List<Post> children) {
        this.children = children;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

//	public Integer getDocumentId() {
//		return documentId;
//	}
//
//	public void setDocumentId(Integer documentId) {
//		this.documentId = documentId;
//	}
//
//	public Document getDocument() {
//		return document;
//	}
//
//	public void setDocument(Document document) {
//		this.document = document;
//	}
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String toString() {
        if (getThematique() == null) {
            setThematique(new Thematique());
        }
        return "Title : " + getTitle() + "\n " + " Body: " + getBody() + " \n " + " Thematique :  " + getThematiqueId() + " - " + getThematique().getLibelle();
    }

//	public List<Document> getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(List<Document> documents) {
//		this.documents = documents;
//	}
    public Integer getThematiqueId() {
        return thematiqueId;
    }

    public void setThematiqueId(Integer thematiqueId) {
        this.thematiqueId = thematiqueId;
    }

    public String getUriYoutube() {
        return uriYoutube;
    }

    public void setUriYoutube(String uriYoutube) {
        this.uriYoutube = uriYoutube;
    }

    /**
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the deletedBy
     */
    public Integer getDeletedBy() {
        return deletedBy;
    }

    /**
     * @param deletedBy the deletedBy to set
     */
    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    /**
     * @return the deletedDate
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * @param deletedDate the deletedDate to set
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

}
