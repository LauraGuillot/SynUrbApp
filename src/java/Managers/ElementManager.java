/**
 * Interface ElementManager
 * ----------------------------------------------------------
 * Définition des méthodes pour manipuler les éléments de la base de données
 */

package Managers;

import Classes.Element;
import Classes.ElementType;
import Classes.Material;
import java.util.ArrayList;

public interface ElementManager {

    /**
     * Get an element with his id
     *
     * @param id Id of the element
     * @return element
     */
    public Element getElementById(long id);

    /**
     * Ajout d'un element dans la base
     *
     * @param e Element à ajouter (sans id)
     */
    public void saveElement(Element e);

    /**
     * Mise à jour d'un element dans la base
     *
     * @param e Element à mettre à jour
     */
    public void updateElement(Element e);

    /**
     * Synchronisation d'un élément : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param e Element à synchroniser
     */
    public void sync(Element e);
    
    /**
     * Récupérer un materiau avec son id
     * @param id id du materiau
     * @return  Materiau
     */
     public Material getMaterialById(long id) ;
     
     /**
     * Récupérer un element type avec son id
     * @param id id de l'element type
     * @return  Element type
     */
    public ElementType getElementTypeById(long id);

    /**
     * Lister tous les types d'élément de la base de données
     * @return liste des types d'élément existants
     */
    public ArrayList<ElementType> getElementTypes();

     /**
     * Lister tous les matériaux de la base de données
     * @return liste les matériaux existants
     */
    public ArrayList<Material> getMaterials();
}
