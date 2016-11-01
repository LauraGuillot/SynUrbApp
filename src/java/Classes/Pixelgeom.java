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
@Table(name = "urbapp.pixelgeom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pixelgeom.findAll", query = "SELECT p FROM Pixelgeom p"),
    @NamedQuery(name = "Pixelgeom.findByPixelgeomId", query = "SELECT p FROM Pixelgeom p WHERE p.pixelgeomId = :pixelgeomId"),
    @NamedQuery(name = "Pixelgeom.findByPixelgeomThegeom", query = "SELECT p FROM Pixelgeom p WHERE p.pixelgeomThegeom = :pixelgeomThegeom")})
public class Pixelgeom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pixelgeom_id")
    private Integer pixelgeomId;
    @Basic(optional = false)
    @Column(name = "pixelgeom_thegeom")
    private String pixelgeomThegeom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pixelgeomId")
    private Collection<Element> elementCollection;

    public Pixelgeom() {
    }

    public Pixelgeom(Integer pixelgeomId) {
        this.pixelgeomId = pixelgeomId;
    }

    public Pixelgeom(Integer pixelgeomId, String pixelgeomThegeom) {
        this.pixelgeomId = pixelgeomId;
        this.pixelgeomThegeom = pixelgeomThegeom;
    }

    public Integer getPixelgeomId() {
        return pixelgeomId;
    }

    public void setPixelgeomId(Integer pixelgeomId) {
        this.pixelgeomId = pixelgeomId;
    }

    public String getPixelgeomThegeom() {
        return pixelgeomThegeom;
    }

    public void setPixelgeomThegeom(String pixelgeomThegeom) {
        this.pixelgeomThegeom = pixelgeomThegeom;
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
        hash += (pixelgeomId != null ? pixelgeomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pixelgeom)) {
            return false;
        }
        Pixelgeom other = (Pixelgeom) object;
        if ((this.pixelgeomId == null && other.pixelgeomId != null) || (this.pixelgeomId != null && !this.pixelgeomId.equals(other.pixelgeomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Pixelgeom[ pixelgeomId=" + pixelgeomId + " ]";
    }
    
}
