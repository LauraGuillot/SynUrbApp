/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Laura
 */
public class ElementTypeSimple {

    private long elementType_id;
    private String elementType_name;
    
    public ElementTypeSimple(ElementType e) {
        elementType_id = e.getElementTypeId();
        elementType_name = e.getElementTypeName();
    }
    
}
