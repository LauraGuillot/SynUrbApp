package Managers;

import Classes.Data;
import Classes.Element;
import Classes.Gpsgeom;
import Classes.Photo;
import Classes.Pixelgeom;
import Classes.Project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SyncManagerImpl implements SyncManager {

    /**
     * Attributs : Managers pour la base de données
     */
    private final static ProjectManager projectManager = ProjectManagerImpl.getInstance();
    private final static PhotoManager photoManager = PhotoManagerImpl.getInstance();
    private final static ElementManager elementManager = ElementManagerImpl.getInstance();
    private final static GpsgeomManager gpsgeomManager = GpsgeomManagerImpl.getInstance();
    private final static PixelgeomManager pixelgeomManager = PixelgeomManagerImpl.getInstance();

    /**
     * Mise à jour des données dans la base distantes : mise à jour des lignes
     * qui existent déjà ou insertion de nouvelles lignes
     *
     * @param d Ensemble des données à mettre à jour
     * @return Id du projet mis à jour (ou ajouté)
     */
    @Override
    public long UpdateData(Data d) {
        //Mise à jour de la  position Gps du projet
        gpsgeomManager.sync(d.getProject_gpsgeom(), d.getProject());
        d.getProject().setGpsgeomId(d.getProject_gpsgeom());

        //Mise à jour de la position gps des photos
        for (Gpsgeom g : d.getPhotos_gpsgeom()) {
            gpsgeomManager.sync(g,d.getProject());
        }
        for (int i = 0; i < d.getPhotos().size(); i++) {
            d.getPhotos().get(i).setGpsgeomId(d.getPhotos_gpsgeom().get(i));
        }

        //Mise à jour des positions gps des éléments
        for (List<Pixelgeom> l : d.getElements_pixelgeom()) {
            for (Pixelgeom pix : l) {
                pixelgeomManager.sync(pix, d.getProject());
            }
        }
        for (int i = 0; i < d.getElements().size(); i++) {
            for (int j = 0; j < d.getElements().get(i).size(); j++) {
                Pixelgeom id = d.getElements_pixelgeom().get(i).get(j);
                d.getElements().get(i).get(j).setPixelgeomId(id);
            }
        }

        //Mise à jour du projet 
        d.setProject(projectManager.sync(d.getProject()));
        for (Photo ph : d.getPhotos()) {
            ph.setProjectId(d.getProject());
        }

        //Mise à jour des photos et des éléments
      
        for (int i = 0; i < d.getPhotos().size(); i++) {
            Photo ph = d.getPhotos().get(i);
            ph = photoManager.sync(ph);
            for (int j = 0; j < d.getElements().get(i).size(); j++) {
                Element e = d.getElements().get(i).get(j);
                e.setPhotoId(ph);
                elementManager.sync(e);
            }
        }
        return d.getProject().getProjectId();
    }

    public Data prepareExport(Long id) {

        //Projet
        Project p = projectManager.getProjectById(id);

        //GPSgeom du projet
        Gpsgeom gps_p = p.getGpsgeomId();

        
        //Photos
        Collection<Photo> photosCollect = projectManager.getPhotoOfProject(id);
        ArrayList<Photo> photos = new ArrayList<>();
        for (Photo ph : photosCollect) {
            photos.add(ph);
        }

        // GPSgeom des photos 
       
        ArrayList<Gpsgeom> gps_photos = new ArrayList<>();
        for (Photo ph : photos) {
            //GPS
            gps_photos.add(ph.getGpsgeomId());  
        }
        
        //Element des photos
         ArrayList<ArrayList<Element>> elements = new ArrayList<>();
         for (Photo ph : photos) {
            ArrayList<Element> elem = photoManager.getElementsOfPhoto(ph);
            elements.add(elem);
         }

        //PixelGeom des elements
        ArrayList<ArrayList<Pixelgeom>> gps_elements = new ArrayList<>();
        for (ArrayList<Element> elem : elements) {
            ArrayList<Pixelgeom> pix = new ArrayList<>();
            for (Element e : elem) {
                pix.add(e.getPixelgeomId());
            }
      
            gps_elements.add(pix);
        }

        return new Data(p,gps_p,photos,gps_photos,elements,gps_elements);
    }

}
