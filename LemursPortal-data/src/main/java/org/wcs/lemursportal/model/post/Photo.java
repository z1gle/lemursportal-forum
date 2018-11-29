package org.wcs.lemursportal.model.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
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
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "photo")
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", insertable = true, updatable = true, length = 100)
    private String name;
    
    @Column(name = "link", insertable = true, updatable = true, length = 500)
    private String link;

    @Column(name = "img_size", insertable = true, updatable = true)
    private Integer size;

    @Column(name = "id_metadata", insertable = true, updatable = true)
    private Integer idMetadata;
    
    // It's not linked to the class Metadata yet
    @Column(name = "id_user", insertable = true, updatable = true)
    private Integer idUser;
    
    @Column(name = "id_post", insertable = true, updatable = true)
    private Integer idPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_metadata", insertable = false, updatable = false)
    @JsonBackReference
    private Metadata metadata = null;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_post", insertable = false, updatable = false)
    private Post post = null;
    
    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PhotoBreakpoint> breakpoints = null;
    
    @Transient
    private String path;
    
    public String getBreakpointsAsStringForSrcset () {
        String response = "";
        for (PhotoBreakpoint b : breakpoints) {
            response += b.getLink() + " " + b.getWidth() + "w,";
        }
        response = response.substring(0, response.length()-1);
        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getIdMetadata() {
        return idMetadata;
    }

    public void setIdMetadata(Integer idMetadata) {
        this.idMetadata = idMetadata;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public List<PhotoBreakpoint> getBreakPoints() {
        return breakpoints;
    }

    public void setBreakPoints(List<PhotoBreakpoint> breakPoints) {
        this.breakpoints = breakPoints;
    }

    public List<PhotoBreakpoint> getBreakpoints() {
        return breakpoints;
    }

    public void setBreakpoints(List<PhotoBreakpoint> breakpoints) {
        this.breakpoints = breakpoints;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    
}
