/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "urbapp.gpsgeom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gpsgeom.findAll", query = "SELECT g FROM Gpsgeom g"),
    @NamedQuery(name = "Gpsgeom.findByGpsgeomId", query = "SELECT g FROM Gpsgeom g WHERE g.gpsgeomId = :gpsgeomId"),
    @NamedQuery(name = "Gpsgeom.findByGpsgeomThegeom", query = "SELECT g FROM Gpsgeom g WHERE g.gpsgeomThegeom = :gpsgeomThegeom")})
public class Gpsgeom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gpsgeom_id")
    private Integer gpsgeomId;
    @Basic(optional = false)
    @Column(name = "gpsgeom_thegeom")
    private String gpsgeomThegeom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gpsgeomId")
    private Collection<Photo> photoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gpsgeomId")
    private Collection<Project> projectCollection;

    public Gpsgeom() {
    }

    public Gpsgeom(Integer gpsgeomId) {
        this.gpsgeomId = gpsgeomId;
    }

    public Gpsgeom(Integer gpsgeomId, String gpsgeomThegeom) {
        this.gpsgeomId = gpsgeomId;
        this.gpsgeomThegeom = gpsgeomThegeom;
    }

    public Integer getGpsgeomId() {
        return gpsgeomId;
    }

    public void setGpsgeomId(Integer gpsgeomId) {
        this.gpsgeomId = gpsgeomId;
    }

    public String getGpsgeomThegeom() {
        return gpsgeomThegeom;
    }

    public void setGpsgeomThegeom(String gpsgeomThegeom) {
        this.gpsgeomThegeom = gpsgeomThegeom;
    }

    @XmlTransient
    public Collection<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public void setPhotoCollection(Collection<Photo> photoCollection) {
        this.photoCollection = photoCollection;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gpsgeomId != null ? gpsgeomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gpsgeom)) {
            return false;
        }
        Gpsgeom other = (Gpsgeom) object;
        if ((this.gpsgeomId == null && other.gpsgeomId != null) || (this.gpsgeomId != null && !this.gpsgeomId.equals(other.gpsgeomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Gpsgeom[ gpsgeomId=" + gpsgeomId + " ]";
    }
    
}
