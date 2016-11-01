/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Classes.Element;
import Classes.Photo;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Laura
 */
public interface PhotoManager {

    /**
     * Get a photo with his id
     *
     * @param id Id of the photo
     * @return Photo
     */
    public Photo getPhotoById(Integer id);

    /**
     * Get a photo with his name
     *
     * @param n Name of the photo
     * @return Photo
     */
    public Photo getPhotoByName(String n);

    /**
     * Get a photo with his path
     *
     * @param path path of the photo
     * @return Photo
     */
    public Photo getPhotoByPath(String path);

    /**
     * Ajout d'une photo dans la base
     *
     * @param p Photo à ajouter (sans id)
     * @return Photo ajoutée (avec id)
     */
    public Photo savePhoto(Photo p);

    /**
     * Récupérer la liste des éléments d'une photo
     *
     * @param p  photo
     * @return Collection d'éléments
     */
    public ArrayList<Element> getElementsOfPhoto(Photo p);

    /**
     * Mise à jour d'une photo dans la base
     *
     * @param p Photo à mettre à jour
     * @return Photo mise à jour
     */
    public Photo updatePhoto(Photo p);
    
     /**
     * Synchronisation d'une photo : mise à jour si elle existe déjà ou ajout sinon
     * @param p Photo à synchroniser
     * @return Photo synchronisée
     */
    public Photo sync(Photo p);
}
