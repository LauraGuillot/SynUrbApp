/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "urbapp.element")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Element.findAll", query = "SELECT e FROM Element e"),
    @NamedQuery(name = "Element.findByElementId", query = "SELECT e FROM Element e WHERE e.elementId = :elementId"),
    @NamedQuery(name = "Element.findByElementColor", query = "SELECT e FROM Element e WHERE e.elementColor = :elementColor")})
public class Element implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "element_id")
    private Integer elementId;
    @Basic(optional = false)
    @Column(name = "element_color")
    private String elementColor;
    @JoinColumn(name = "element_type_id", referencedColumnName = "element_type_id")
    @ManyToOne(optional = false)
    private ElementType elementTypeId;
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    @ManyToOne(optional = false)
    private Material materialId;
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    @ManyToOne(optional = false)
    private Photo photoId;
    @JoinColumn(name = "pixelgeom_id", referencedColumnName = "pixelgeom_id")
    @ManyToOne( optional = false)
    private Pixelgeom pixelgeomId;

    public Element() {
    }

    public Element(Integer elementId) {
        this.elementId = elementId;
    }

    public Element(Integer elementId, String elementColor) {
        this.elementId = elementId;
        this.elementColor = elementColor;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getElementColor() {
        return elementColor;
    }

    public void setElementColor(String elementColor) {
        this.elementColor = elementColor;
    }

    public ElementType getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(ElementType elementTypeId) {
        this.elementTypeId = elementTypeId;
    }

    public Material getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Material materialId) {
        this.materialId = materialId;
    }

    public Photo getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Photo photoId) {
        this.photoId = photoId;
    }

    public Pixelgeom getPixelgeomId() {
        return pixelgeomId;
    }

    public void setPixelgeomId(Pixelgeom pixelgeomId) {
        this.pixelgeomId = pixelgeomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elementId != null ? elementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Element)) {
            return false;
        }
        Element other = (Element) object;
        if ((this.elementId == null && other.elementId != null) || (this.elementId != null && !this.elementId.equals(other.elementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Element[ elementId=" + elementId + " ]";
    }
    
}
