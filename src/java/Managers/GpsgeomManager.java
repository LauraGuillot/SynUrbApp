package Managers;

import Classes.Gpsgeom;
import Classes.Project;

public interface GpsgeomManager {

    /**
     * Get a gpsgeom with his id
     *
     * @param id Id of the gpsgeom
     * @return gpsgeom
     */
    public Gpsgeom getGpsgeomById(long id);

    /**
     * Ajout d'un gpsGeom dans la base
     *
     * @param g GpsGeom à ajouter (sans id)
     */
    public void saveGpsgeom(Gpsgeom g);

    /**
     * Mise à jour d'un Gpsgeom dans la base
     *
     * @param g Gpsgeom à mettre à jour
     */
    public void updateGpsgeom(Gpsgeom g);

    /**
     * Synchronisation d'un gpsgeomt : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param g gpsgeom à synchroniser
     */
    public void sync(Gpsgeom g, Project p);
 
}
