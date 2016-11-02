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
@Table(name = "urbapp.material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByMaterialId", query = "SELECT m FROM Material m WHERE m.materialId = :materialId"),
    @NamedQuery(name = "Material.findByMaterialName", query = "SELECT m FROM Material m WHERE m.materialName = :materialName"),
    @NamedQuery(name = "Material.findByMaterialHeatCapa", query = "SELECT m FROM Material m WHERE m.materialHeatCapa = :materialHeatCapa"),
    @NamedQuery(name = "Material.findByMaterialMassDensity", query = "SELECT m FROM Material m WHERE m.materialMassDensity = :materialMassDensity"),
    @NamedQuery(name = "Material.findByMaterialConduct", query = "SELECT m FROM Material m WHERE m.materialConduct = :materialConduct")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "material_id")
    private Integer materialId;
    @Basic(optional = false)
    @Column(name = "material_name")
    private String materialName;
    @Column(name = "material_heat_capa")
    private Double materialHeatCapa;
    @Column(name = "material_mass_density")
    private Double materialMassDensity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "material_conduct")
    private Double materialConduct;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialId")
    private Collection<Element> elementCollection;

    public Material() {
    }

    public Material(Integer materialId) {
        this.materialId = materialId;
    }

    public Material(Integer materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Double getMaterialHeatCapa() {
        return materialHeatCapa;
    }

    public void setMaterialHeatCapa(Double materialHeatCapa) {
        this.materialHeatCapa = materialHeatCapa;
    }

    public Double getMaterialMassDensity() {
        return materialMassDensity;
    }

    public void setMaterialMassDensity(Double materialMassDensity) {
        this.materialMassDensity = materialMassDensity;
    }

    public Double getMaterialConduct() {
        return materialConduct;
    }

    public void setMaterialConduct(Double materialConduct) {
        this.materialConduct = materialConduct;
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
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Material[ materialId=" + materialId + " ]";
    }
    
}
