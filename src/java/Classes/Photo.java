/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "urbapp.photo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p"),
    @NamedQuery(name = "Photo.findByPhotoId", query = "SELECT p FROM Photo p WHERE p.photoId = :photoId"),
    @NamedQuery(name = "Photo.findByPhotoName", query = "SELECT p FROM Photo p WHERE p.photoName = :photoName"),
    @NamedQuery(name = "Photo.findByPhotoDescription", query = "SELECT p FROM Photo p WHERE p.photoDescription = :photoDescription"),
    @NamedQuery(name = "Photo.findByPhotoAuthor", query = "SELECT p FROM Photo p WHERE p.photoAuthor = :photoAuthor"),
    @NamedQuery(name = "Photo.findByPhotoLastModification", query = "SELECT p FROM Photo p WHERE p.photoLastModification = :photoLastModification"),
    @NamedQuery(name = "Photo.findByPhotoPath", query = "SELECT p FROM Photo p WHERE p.photoPath = :photoPath")})
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "photo_id")
    private Integer photoId;
    @Basic(optional = false)
    @Column(name = "photo_name")
    private String photoName;
    @Column(name = "photo_description")
    private String photoDescription;
    @Basic(optional = false)
    @Column(name = "photo_author")
    private String photoAuthor;
    @Basic(optional = false)
    @Column(name = "photo_last_modification")
    @Temporal(TemporalType.DATE)
    private Date photoLastModification;
    @Basic(optional = false)
    @Column(name = "photo_path")
    private String photoPath;
    @JoinColumn(name = "gpsgeom_id", referencedColumnName = "gpsgeom_id")
    @ManyToOne(optional = false)
    private Gpsgeom gpsgeomId;
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    @ManyToOne(optional = false)
    private Project projectId;
    @OneToMany( mappedBy = "photoId")
    private Collection<Element> elementCollection;

    public Photo() {
    }

    public Photo(Integer photoId) {
        this.photoId = photoId;
    }

    public Photo(Integer photoId, String photoName, String photoAuthor, Date photoLastModification, String photoPath) {
        this.photoId = photoId;
        this.photoName = photoName;
        this.photoAuthor = photoAuthor;
        this.photoLastModification = photoLastModification;
        this.photoPath = photoPath;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public String getPhotoAuthor() {
        return photoAuthor;
    }

    public void setPhotoAuthor(String photoAuthor) {
        this.photoAuthor = photoAuthor;
    }

    public Date getPhotoLastModification() {
        return photoLastModification;
    }

    public void setPhotoLastModification(Date photoLastModification) {
        this.photoLastModification = photoLastModification;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Gpsgeom getGpsgeomId() {
        return gpsgeomId;
    }

    public void setGpsgeomId(Gpsgeom gpsgeomId) {
        this.gpsgeomId = gpsgeomId;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    @XmlTransient
    public Collection<Element> getElementCollection() {
        return elementCollection;
    }

    public void setElementCollection(Collection<Element> elementCollection) {
        this.elementCollection = elementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photoId != null ? photoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if ((this.photoId == null && other.photoId != null) || (this.photoId != null && !this.photoId.equals(other.photoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Photo[ photoId=" + photoId + " ]";
    }
    
}
