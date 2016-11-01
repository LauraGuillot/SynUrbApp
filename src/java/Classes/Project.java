/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "urbapp.project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjectId", query = "SELECT p FROM Project p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
    @NamedQuery(name = "Project.findByProjectDescription", query = "SELECT p FROM Project p WHERE p.projectDescription = :projectDescription"),
    @NamedQuery(name = "Project.findByProjectIsavailable", query = "SELECT p FROM Project p WHERE p.projectIsavailable = :projectIsavailable"),
    @NamedQuery(name = "Project.findByProjectVersion", query = "SELECT p FROM Project p WHERE p.projectVersion = :projectVersion")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_id")
    private Integer projectId;
    @Basic(optional = false)
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_description")
    private String projectDescription;
    @Basic(optional = false)
    @Column(name = "project_isavailable")
    private boolean projectIsavailable;
    @Basic(optional = false)
    @Column(name = "project_version")
    private int projectVersion;
    @JoinColumn(name = "gpsgeom_id", referencedColumnName = "gpsgeom_id")
    @ManyToOne(optional = false)
    private Gpsgeom gpsgeomId;

    public Project() {
    }

    public Project(Integer projectId) {
        this.projectId = projectId;
    }

    public Project(Integer projectId, String projectName, boolean projectIsavailable, int projectVersion) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectIsavailable = projectIsavailable;
        this.projectVersion = projectVersion;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public boolean getProjectIsavailable() {
        return projectIsavailable;
    }

    public void setProjectIsavailable(boolean projectIsavailable) {
        this.projectIsavailable = projectIsavailable;
    }

    public int getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(int projectVersion) {
        this.projectVersion = projectVersion;
    }

    public Gpsgeom getGpsgeomId() {
        return gpsgeomId;
    }

    public void setGpsgeomId(Gpsgeom gpsgeomId) {
        this.gpsgeomId = gpsgeomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Project[ projectId=" + projectId + " ]";
    }
    
    
    
}
