/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     */
    public void sync(Pixelgeom g, Project p);
}
