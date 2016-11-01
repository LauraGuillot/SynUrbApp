/**
 * Interface PixelGeomManager
 * ----------------------------------------------------------
 * Définition des méthodes pour manipuler les pixelgeom de la base de données
 */
package Managers;

import Classes.Pixelgeom;
import Classes.Project;

/**
 *
 * @author Laura
 */
public interface PixelgeomManager {

    /**
     * Get a pixelgeom with his id
     *
     * @param id Id of the pixelgeom
     * @return pixelgeom
     */
    public Pixelgeom getPixelgeomById(long id);

    /**
     * Ajout d'un pixemGeom dans la base
     *
     * @param g PixelGeom à ajouter (sans id)
     */
    public void savePixelgeom(Pixelgeom g);

    /**
     * Mise à jour d'un Pixelgeom dans la base
     *
     * @param g Pixelgeom à mettre à jour
     */
    public void updatePixelgeom(Pixelgeom g);

    /**
     * Synchronisation d'un pixemgeom : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param g pixelgeom à synchroniser
     * @param p Projet
     */
    public void sync(Pixelgeom g, Project p);
}
