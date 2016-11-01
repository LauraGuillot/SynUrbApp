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
public class PixelgeomSimple {

    private int pixelGeom_id;
    private String pixelGeom_thegeom;

    public PixelgeomSimple(Pixelgeom g) {
        pixelGeom_id = g.getPixelgeomId();
        pixelGeom_thegeom = g.getPixelgeomThegeom();
    }

    public Pixelgeom toPixelGeom() {
        Pixelgeom pix = new Pixelgeom();
        pix.setPixelgeomId(pixelGeom_id);
        pix.setPixelgeomThegeom(pixelGeom_thegeom);
        return pix;
    }
}
