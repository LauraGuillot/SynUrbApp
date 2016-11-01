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
public class MaterialSimple {

    private long material_id;
    private String material_name;
    
    
    public MaterialSimple(Material m) {
        material_id=m.getMaterialId();
        material_name=m.getMaterialName();
    }
    
    
}
