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
public class GpsGeomSimple {

    private int gpsGeom_id;
    private String gpsGeom_thegeom;

    public GpsGeomSimple(Gpsgeom g) {
        gpsGeom_id = g.getGpsgeomId();
        gpsGeom_thegeom = g.getGpsgeomThegeom();
    }

    public Gpsgeom toGpsGeom() {
        Gpsgeom g = new Gpsgeom();
        g.setGpsgeomId(gpsGeom_id);
        g.setGpsgeomThegeom(gpsGeom_thegeom);
        return g;
    }
}
