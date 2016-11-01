/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
