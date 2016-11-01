package Managers;

import Classes.*;
import Tools.DateMethods;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PhotoManagerImpl implements PhotoManager {

    //Attributs
    private EntityManagerFactory emf;
    private static PhotoManagerImpl thePhotoManager;

    //Constructeur
    private PhotoManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SynUrbAppPU");
        }
    }

    //Instanciation
    public static PhotoManager getInstance() {
        if (thePhotoManager == null) {
            thePhotoManager = new PhotoManagerImpl();
        }
        return thePhotoManager;
    }

    /**
     * Get a photo with his id
     *
     * @param id Id of the photo
     * @return Photo
     */
    @Override
    public Photo getPhotoById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Photo.findByPhotoId");
        q.setParameter("photoId", id);
        List l = q.getResultList();
        em.close();
        return l.isEmpty() ? null : (Photo) l.get(0);
    }

    /**
     * Get a photo with his name
     *
     * @param n Name of the photo
     * @return Photo
     */
    @Override
    public Photo getPhotoByName(String n) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Photo.findByPhotoName");
        q.setParameter("photoName", n);
        List l = q.getResultList();
        em.close();
        return l.isEmpty() ? null : (Photo) l.get(0);

    }

    /**
     * Get a photo with his path
     *
     * @param path path of the photo
     * @return Photo
     */
    @Override
    public Photo getPhotoByPath(String path) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Photo.findByPhotoPath");
        q.setParameter("photoPath", path);
        List l = q.getResultList();
        em.close();
        return l.isEmpty() ? null : (Photo) l.get(0);
    }

    /**
     * Ajout d'une photo dans la base
     *
     * @param p Photo à ajouter (sans id)
     * @return Photo ajoutée (avec id)
     */
    @Override
    public Photo savePhoto(Photo p) {

        Photo p1 = new Photo();
        p1.setGpsgeomId(p.getGpsgeomId());
        p1.setPhotoAuthor(p.getPhotoAuthor());
        p1.setPhotoDescription(p.getPhotoDescription());
        p1.setPhotoLastModification(DateMethods.getCurrentDate());
        p1.setPhotoName(p.getPhotoName());
        p1.setPhotoPath(p.getPhotoPath());
        p1.setProjectId(p.getProjectId());

        p.setPhotoLastModification(DateMethods.getCurrentDate());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();
        em.close();
        return this.getPhotoByName(p.getPhotoName());
    }

    /**
     * Mise à jour d'une photo dans la base
     *
     * @param p Photo à mettre à jour
     * @return Photo mise à jour
     */
    @Override
    public Photo updatePhoto(Photo p) {
        p.setPhotoLastModification(DateMethods.getCurrentDate());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();

        return p;
    }

    /**
     * Récupérer la liste des éléments d'une photo
     *
     * @param p photo
     * @return Collection d'éléments
     */
    @Override
    public ArrayList<Element> getElementsOfPhoto(Photo p) {
        ArrayList<Element> e = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Element e WHERE ( e.photoId=:p)");
        q.setParameter("p", p);
        List l = q.getResultList();
        for (Object o : l) {
            e.add((Element) o);
        }

        return e;
    }

    /**
     * Synchronisation d'une photo : mise à jour si elle existe déjà ou ajout
     * sinon
     *
     * @param p Photo à synchroniser
     * @return Photo synchronisée
     */
    @Override
    public Photo sync(Photo p) {
        Photo p1 = this.getPhotoById(p.getPhotoId());
        if (p1 == null|| p1.getProjectId().getProjectId()!=p.getProjectId().getProjectId()) {
            p1 = this.savePhoto(p);
        } else {
            p1 = this.updatePhoto(p);
        }
        return p1;
    }

}
