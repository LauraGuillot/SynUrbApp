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

    public ArrayList<ElementType> getElementTypes();

    public ArrayList<Material> getMaterials();
}
