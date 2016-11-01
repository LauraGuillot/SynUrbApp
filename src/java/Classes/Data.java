package Classes;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private Project project;
    private Gpsgeom project_gpsgeom;
    private List<Photo> photos;
    private List<Gpsgeom> photos_gpsgeom;
    private ArrayList<ArrayList<Element>> elements;
    private ArrayList<ArrayList<Pixelgeom>> elements_pixelgeom;

    public Data(Project project, Gpsgeom project_gpsgeom, List<Photo> photos, List<Gpsgeom> photos_gpsgeom, ArrayList<ArrayList<Element>> elements, ArrayList<ArrayList<Pixelgeom>> elements_pixelgeom) {
        this.project = project;
        this.project_gpsgeom = project_gpsgeom;
        this.photos = photos;
        this.photos_gpsgeom = photos_gpsgeom;
        this.elements = elements;
        this.elements_pixelgeom = elements_pixelgeom;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Gpsgeom getProject_gpsgeom() {
        return project_gpsgeom;
    }

    public void setProject_gpsgeom(Gpsgeom project_gpsgeom) {
        this.project_gpsgeom = project_gpsgeom;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Gpsgeom> getPhotos_gpsgeom() {
        return photos_gpsgeom;
    }

    public void setPhotos_gpsgeom(List<Gpsgeom> photos_gpsgeom) {
        this.photos_gpsgeom = photos_gpsgeom;
    }

    public ArrayList<ArrayList<Element>> getElements() {
        return elements;
    }

    public void setElements(ArrayList<ArrayList<Element>> elements) {
        this.elements = elements;
    }

    public ArrayList<ArrayList<Pixelgeom>> getElements_pixelgeom() {
        return elements_pixelgeom;
    }

    public void setElements_pixelgeom(ArrayList<ArrayList<Pixelgeom>> elements_pixelgeom) {
        this.elements_pixelgeom = elements_pixelgeom;
    }

}
