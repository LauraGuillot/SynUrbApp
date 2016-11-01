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
@Table(name = "urbapp.element_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementType.findAll", query = "SELECT e FROM ElementType e"),
    @NamedQuery(name = "ElementType.findByElementTypeId", query = "SELECT e FROM ElementType e WHERE e.elementTypeId = :elementTypeId"),
    @NamedQuery(name = "ElementType.findByElementTypeName", query = "SELECT e FROM ElementType e WHERE e.elementTypeName = :elementTypeName")})
public class ElementType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "element_type_id")
    private Integer elementTypeId;
    @Basic(optional = false)
    @Column(name = "element_type_name")
    private String elementTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elementTypeId")
    private Collection<Element> elementCollection;

    public ElementType() {
    }

    public ElementType(Integer elementTypeId) {
        this.elementTypeId = elementTypeId;
    }

    public ElementType(Integer elementTypeId, String elementTypeName) {
        this.elementTypeId = elementTypeId;
        this.elementTypeName = elementTypeName;
    }

    public Integer getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(Integer elementTypeId) {
        this.elementTypeId = elementTypeId;
    }

    public String getElementTypeName() {
        return elementTypeName;
    }

    public void setElementTypeName(String elementTypeName) {
        this.elementTypeName = elementTypeName;
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
        hash += (elementTypeId != null ? elementTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementType)) {
            return false;
        }
        ElementType other = (ElementType) object;
        if ((this.elementTypeId == null && other.elementTypeId != null) || (this.elementTypeId != null && !this.elementTypeId.equals(other.elementTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.ElementType[ elementTypeId=" + elementTypeId + " ]";
    }
    
}
