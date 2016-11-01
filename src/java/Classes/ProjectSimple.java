/**
 * CLASSE ProjectSimple
 * ----------------------------------------------------------
 * Cette classe représente un projet avec son id, son nom, sa description et  son gpsgeom id.
 * Elle a été crée dans un souci de compatibilité entre la classe project de l'appliction android 
 * et la classe Project de ce web service qui correspond à la table de la base de donnée du serveur.
 */
package Classes;

import Managers.GpsgeomManager;
import Managers.GpsgeomManagerImpl;
import Managers.PixelgeomManager;
import Managers.PixelgeomManagerImpl;
import java.util.Date;

/**
 * @author Laura
 */
public class ProjectSimple {

    private Integer project_id;
    private String project_name;
    private String project_description;
    private int project_version;
    private boolean project_isavailable;
    private long gpsGeom_id;

    public ProjectSimple(Project p) {
        project_id = p.getProjectId();
        project_name = p.getProjectName();
        project_description = p.getProjectDescription();
        project_version = p.getProjectVersion();
        project_isavailable = p.getProjectIsavailable();
        gpsGeom_id = p.getGpsgeomId().getGpsgeomId();
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public int getProject_version() {
        return project_version;
    }

    public void setProject_version(int project_version) {
        this.project_version = project_version;
    }

    public boolean isProject_isavailable() {
        return project_isavailable;
    }

    public void setProject_isavailable(boolean project_isavailable) {
        this.project_isavailable = project_isavailable;
    }

    public long getGpsgeom_id() {
        return gpsGeom_id;
    }

    public void setGpsgeom_id(long gpsgeom_id) {
        this.gpsGeom_id = gpsgeom_id;
    }

    /**
     * Conversion d'un projectSimple en project
     * @return Projet
     */
    public Project toProject() {
        Project p = new Project();
        GpsgeomManager man = GpsgeomManagerImpl.getInstance();
        p.setGpsgeomId(man.getGpsgeomById(gpsGeom_id));
        p.setProjectId(project_id);
        p.setProjectIsavailable(project_isavailable);
        p.setProjectName(project_name);
        p.setProjectVersion(project_version);
        p.setProjectDescription(project_description);
        return p;
    }

}
