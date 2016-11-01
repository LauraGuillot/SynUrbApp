/**
 * CLASSE PixelgeomSimple
 * ----------------------------------------------------------
 * Cette classe représente un pixelgeom avec sa géométrie et son id.
 * Elle a été crée dans un souci de compatibilité entre la classe pixelgeom de l'application android 
 * et la classe pixelgeom de ce web service qui correspond à la table de la base de donnée du serveur.
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

    
    /**
     * Conversion d'un pixelgeomSimple en pixelgeom
     * @return pixelgeom correspondant
     */
    public Pixelgeom toPixelGeom() {
        Pixelgeom pix = new Pixelgeom();
        pix.setPixelgeomId(pixelGeom_id);
        pix.setPixelgeomThegeom(pixelGeom_thegeom);
        return pix;
    }
}
