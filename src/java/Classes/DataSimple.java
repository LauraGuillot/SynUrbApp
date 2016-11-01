/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laura
 */
public class DataSimple {

    private ProjectSimple project;
    private GpsGeomSimple project_gpsgeom;
    private List<PhotoSimple> photos;
    private List<GpsGeomSimple> photos_gpsgeom;
    private ArrayList<ArrayList<ElementSimple>> elements;
    private ArrayList<ArrayList<PixelgeomSimple>> elements_pixelgeom;

    public DataSimple(Data data) {
        project = new ProjectSimple(data.getProject());
        project_gpsgeom = new GpsGeomSimple(data.getProject_gpsgeom());

        photos = new ArrayList<>();
        for (Photo ph : data.getPhotos()) {
            photos.add(new PhotoSimple(ph));
        }

        photos_gpsgeom = new ArrayList<>();
        for (Gpsgeom g : data.getPhotos_gpsgeom()) {
            photos_gpsgeom.add(new GpsGeomSimple(g));
        }

        elements = new ArrayList<>();
        for (ArrayList<Element> l : data.getElements()) {
            ArrayList<ElementSimple> elem = new ArrayList<>();
            for (Element e : l) {
                elem.add(new ElementSimple(e));
            }
            elements.add(elem);
        }

        elements_pixelgeom = new ArrayList<>();
        for (ArrayList<Pixelgeom> l : data.getElements_pixelgeom()) {
            ArrayList<PixelgeomSimple> pix = new ArrayList<>();
            for (Pixelgeom p : l) {
                pix.add(new PixelgeomSimple(p));
            }
            elements_pixelgeom.add(pix);
        }

    }

    public Data toData() {

        //Projet 
        Project p = project.toProject();

        //Gps geom du projet
        Gpsgeom pgpsgeom = project_gpsgeom.toGpsGeom();

        //Gps geom des photos
        ArrayList<Gpsgeom> gpsphoto = new ArrayList<>();
        for (GpsGeomSimple gp : photos_gpsgeom) {
            gpsphoto.add(gp.toGpsGeom());
        }

        //Pixel geom des elements
        ArrayList<ArrayList<Pixelgeom>> pix = new ArrayList<>();
        for (ArrayList<PixelgeomSimple> pixsimp : elements_pixelgeom) {
            ArrayList<Pixelgeom> pix1 = new ArrayList<>();
            for (PixelgeomSimple pixs : pixsimp) {
                pix1.add(pixs.toPixelGeom());
            }
            pix.add(pix1);
        }

        //Elements
        ArrayList<ArrayList<Element>> elem = new ArrayList<>();
        for (ArrayList<ElementSimple> elemsimp : elements) {
            ArrayList<Element> el = new ArrayList<>();
            for (ElementSimple e : elemsimp) {
                el.add(e.toElement());
            }
            elem.add(el);
        }

        //Photos
        ArrayList<Photo> phot = new ArrayList<>();
        for (PhotoSimple ph : photos) {
            phot.add(ph.toPhoto());
        }

        return new Data(p, pgpsgeom, phot, gpsphoto, elem, pix);
    }

    public ProjectSimple getProject() {
        return project;
    }

    public void setProject(ProjectSimple project) {
        this.project = project;
    }

    public GpsGeomSimple getProject_gpsgeom() {
        return project_gpsgeom;
    }

    public void setProject_gpsgeom(GpsGeomSimple project_gpsgeom) {
        this.project_gpsgeom = project_gpsgeom;
    }

    public List<PhotoSimple> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoSimple> photos) {
        this.photos = photos;
    }

    public List<GpsGeomSimple> getPhotos_gpsgeom() {
        return photos_gpsgeom;
    }

    public void setPhotos_gpsgeom(List<GpsGeomSimple> photos_gpsgeom) {
        this.photos_gpsgeom = photos_gpsgeom;
    }

    public ArrayList<ArrayList<ElementSimple>> getElements() {
        return elements;
    }

    public void setElements(ArrayList<ArrayList<ElementSimple>> elements) {
        this.elements = elements;
    }

    public ArrayList<ArrayList<PixelgeomSimple>> getElements_pixelgeom() {
        return elements_pixelgeom;
    }

    public void setElements_pixelgeom(ArrayList<ArrayList<PixelgeomSimple>> elements_pixelgeom) {
        this.elements_pixelgeom = elements_pixelgeom;
    }
    
    
    
}
