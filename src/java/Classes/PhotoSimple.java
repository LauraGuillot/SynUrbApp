/**
 * CLASSE PhotoSimple
 * ----------------------------------------------------------
 * Cette classe représente une photo.
 * Elle a été crée dans un souci de compatibilité entre la classe photo de l'appliction android
 * et la classe Photo de ce web service qui correspond à la table de la base de donnée du serveur.
 */
package Classes;

import Managers.GpsgeomManager;
import Managers.GpsgeomManagerImpl;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;

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

    /**
     * Conversion d'une photoSimple en photo
     *
     * @return photo correspondante
     */
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
