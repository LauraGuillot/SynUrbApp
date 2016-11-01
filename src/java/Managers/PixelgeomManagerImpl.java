/**
 * Classe PixelgeomManagerImpl
 * ----------------------------------------------------------
 * Implémentation de l'interface PixelgeomManager.
 * Manipulation des pixelgeom de la base de données
 */
package Managers;

import Classes.Element;
import Classes.Pixelgeom;
import Classes.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PixelgeomManagerImpl implements PixelgeomManager {

    //Attributs
    private EntityManagerFactory emf;
    private static PixelgeomManagerImpl thePixelgeomManager;

    //Constructeur
    private PixelgeomManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SynUrbAppPU");
        }
    }

    //Instanciation
    public static PixelgeomManager getInstance() {
        if (thePixelgeomManager == null) {
            thePixelgeomManager = new PixelgeomManagerImpl();
        }
        return thePixelgeomManager;
    }

    /**
     * Get a pixelgeom with his id
     *
     * @param id Id of the pixelgeom
     * @return pixelgeom
     */
    @Override
    public Pixelgeom getPixelgeomById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Pixelgeom.findByPixelgeomId");
        q.setParameter("pixelgeomId", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Pixelgeom) l.get(0);
    }

    /**
     * Ajout d'un pixemGeom dans la base
     *
     * @param g PixelGeom à ajouter (sans id)
     */
    @Override
    public void savePixelgeom(Pixelgeom g) {
        Pixelgeom pix = new Pixelgeom();
        pix.setPixelgeomThegeom(g.getPixelgeomThegeom());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pix);
        em.getTransaction().commit();

        //Mise à jour de l'id
        Query q = em.createQuery("SELECT g FROM Pixelgeom g WHERE ( g.pixelgeomThegeom LIKE :x)");
        q.setParameter("x", g.getPixelgeomThegeom());
        List l = q.getResultList();
        g.setPixelgeomId(((Pixelgeom) l.get(0)).getPixelgeomId());
    }

    /**
     * Mise à jour d'un Pixelgeom dans la base
     *
     * @param g Pixelgeom à mettre à jour
     */
    @Override
    public void updatePixelgeom(Pixelgeom g) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(g);
        em.getTransaction().commit();
    }

    /**
     * Synchronisation d'un pixemgeom : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param g pixelgeom à synchroniser
     */
    @Override
    public void sync(Pixelgeom g, Project p) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT g FROM Pixelgeom g WHERE ( g.pixelgeomId =:id)");
        q.setParameter("id", g.getPixelgeomId());
        List l = q.getResultList();

        if (l.isEmpty() || !belongToProject(g, p)) {
            this.savePixelgeom(g);
        } else {
            this.updatePixelgeom(g);
        }

    }

    /**
     * Test si un pixelgeom est lié ou non àun projet. Il est lié au projet si
     * il est la géométrie d'un élément d'une des photos du projet.
     *
     * @param pix Pixel geom
     * @param p Projet
     * @return booléen
     */
    public boolean belongToProject(Pixelgeom pix, Project p) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Element e WHERE ( e.pixelgeomId.pixelgeomId=:id)");
        q.setParameter("id", pix.getPixelgeomId());
        List l = q.getResultList();

        if (l.isEmpty()) {
            return false;
        } else {
            Element e = (Element) l.get(0);
            return e.getPhotoId().getProjectId().getProjectId() == p.getProjectId();
        }

    }

}
