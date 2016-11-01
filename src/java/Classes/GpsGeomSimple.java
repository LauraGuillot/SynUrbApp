/**
 * CLASSE GpsGeomSimple
 * ----------------------------------------------------------
 * Cette classe représente un gpsgeom avec sa géométrie et son id.
 * Elle a été crée dans un souci de compatibilité entre la classe gpsgeom de l'appliction android
 * et la classe gpsgeom de ce web service qui correspond à la table de la base de donnée du serveur.
 */
package Classes;

/**
 *
 * @author Laura
 */
public class GpsGeomSimple {

    private int gpsGeom_id;
    private String gpsGeom_thegeom;

    public GpsGeomSimple(Gpsgeom g) {
        gpsGeom_id = g.getGpsgeomId();
        gpsGeom_thegeom = g.getGpsgeomThegeom();
    }

    /**
     * Conversion d'un gpsgeomSimple en gpsGeom
     * @return gpsgeom 
     */
    public Gpsgeom toGpsGeom() {
        Gpsgeom g = new Gpsgeom();
        g.setGpsgeomId(gpsGeom_id);
        g.setGpsgeomThegeom(gpsGeom_thegeom);
        return g;
    }
}
