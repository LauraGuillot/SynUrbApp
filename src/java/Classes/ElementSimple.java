/**
 * CLASSE ElementSimple
 * ----------------------------------------------------------
 * Cette classe représente un élément.
 * Elle a été crée dans un souci de compatibilité entre la classe element de l'application android
 * et la classe Element de ce web service qui correspond à la table de la base de donnée du serveur.
 */
package Classes;

import Managers.ElementManager;
import Managers.ElementManagerImpl;
import Managers.PhotoManager;
import Managers.PhotoManagerImpl;
import Managers.PixelgeomManager;
import Managers.PixelgeomManagerImpl;

/**
 *
 * @author Laura
 */
public class ElementSimple {

    private Integer element_id;
    private String element_color;
    private int elementType_id;
    private int material_id;
    private int photo_id;
    private int pixelGeom_id;
    private boolean selected;

    public ElementSimple(Element e) {
        element_id = e.getElementId();
        element_color = e.getElementColor();
        elementType_id = e.getElementTypeId().getElementTypeId();
        material_id = e.getMaterialId().getMaterialId();
        photo_id = e.getPhotoId().getPhotoId();
        pixelGeom_id = e.getPixelgeomId().getPixelgeomId();
        selected = false;
    }

    /**
     * Conversion d'un ElementSimple en Element
     * @return Element correspondant
     */
    public Element toElement() {
        Element e = new Element();
        e.setElementColor(element_color);
        e.setElementId(element_id);
        ElementManager man = ElementManagerImpl.getInstance();
        e.setElementTypeId(man.getElementTypeById(elementType_id));
        e.setMaterialId(man.getMaterialById(material_id));
        PhotoManager pman = PhotoManagerImpl.getInstance();
        e.setPhotoId(pman.getPhotoById(photo_id));
        PixelgeomManager pixMan = PixelgeomManagerImpl.getInstance();
        e.setPixelgeomId(pixMan.getPixelgeomById(pixelGeom_id));
        return e;
    }
}
