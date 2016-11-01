/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Managers.GpsgeomManager;
import Managers.GpsgeomManagerImpl;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laura
 */
public class PhotoSimple {
    
    private Integer photo_id;
    private String photo_name;
    private String photo_description;
    private String photo_author;
    private String photo_path;
    private int gpsGeom_id;
    private int project_id;
    private int photo_last_modification;
    
    public PhotoSimple(Photo p) {
        photo_id = p.getPhotoId();
        photo_name = p.getPhotoName();
        photo_description = p.getPhotoDescription();
        photo_author = p.getPhotoAuthor();
        photo_path = p.getPhotoPath();
        gpsGeom_id = p.getGpsgeomId().getGpsgeomId();
        project_id = p.getProjectId().getProjectId();
        photo_last_modification = 0;
    }
    
    public Photo toPhoto() {
        Photo p = new Photo();
        
        GpsgeomManager man = GpsgeomManagerImpl.getInstance();
        p.setGpsgeomId(man.getGpsgeomById(gpsGeom_id));
        
        p.setPhotoAuthor(photo_author);
        p.setPhotoDescription(photo_description);
        p.setPhotoName(photo_name);
        p.setPhotoPath(photo_path);
        
        ProjectManager pman = ProjectManagerImpl.getInstance();
        p.setProjectId(pman.getProjectById(project_id));
        
        p.setPhotoId(photo_id);
        
        return p;
    }
    
}
