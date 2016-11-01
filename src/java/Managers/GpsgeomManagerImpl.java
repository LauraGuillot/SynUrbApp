package Managers;

import Classes.Element;
import Classes.Gpsgeom;
import Classes.Photo;
import Classes.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GpsgeomManagerImpl implements GpsgeomManager {

    //Attributs
    private EntityManagerFactory emf;
    private static GpsgeomManagerImpl theGpsgeomManager;

    //Constructeur
    private GpsgeomManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SynUrbAppPU");
        }
    }

    //Instanciation
    public static GpsgeomManager getInstance() {
        if (theGpsgeomManager == null) {
            theGpsgeomManager = new GpsgeomManagerImpl();
        }
        return theGpsgeomManager;
    }

    /**
     * Get a gpsgeom with his id
     *
     * @param id Id of the gpsgeom
     * @return gpsgeom
     */
    @Override
    public Gpsgeom getGpsgeomById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Gpsgeom.findByGpsgeomId");
        q.setParameter("gpsgeomId", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Gpsgeom) l.get(0);
    }

    /**
     * Ajout d'un gpsGeom dans la base
     *
     * @param g GpsGeom à ajouter (sans id)
     */
    @Override
    public void saveGpsgeom(Gpsgeom g) {

        Gpsgeom g1 = new Gpsgeom();
        g1.setGpsgeomThegeom(g.getGpsgeomThegeom() == null ? "" : g.getGpsgeomThegeom());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(g1);
        em.getTransaction().commit();

        //Mise à jour de l'id 
        Query q = em.createQuery("SELECT g FROM Gpsgeom g WHERE ( g.gpsgeomThegeom LIKE :x)");
        q.setParameter("x", g1.getGpsgeomThegeom());
        List l = q.getResultList();

        g.setGpsgeomId(((Gpsgeom) l.get(0)).getGpsgeomId());
    }

    /**
     * Mise à jour d'un Gpsgeom dans la base
     *
     * @param g Gpsgeom à mettre à jour
     */
    @Override
    public void updateGpsgeom(Gpsgeom g) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(g);
        em.getTransaction().commit();

    }

    /**
     * Synchronisation d'un gpsgeomt : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param g gpsgeom à synchroniser
     */
    @Override
    public void sync(Gpsgeom g, Project p) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT g FROM Gpsgeom g WHERE ( g.gpsgeomId =:id)");
        q.setParameter("id", g.getGpsgeomId());
        List l = q.getResultList();

        if (l.isEmpty() || !belongToProject(g,p)){
            this.saveGpsgeom(g);
        } else {
            this.updateGpsgeom(g);
        }
    }
    
    
    public boolean belongToProject(Gpsgeom gp, Project p){
        return gp.getGpsgeomId()==p.getGpsgeomId().getGpsgeomId() || belongToProjectPhoto(gp,p);
    }

    private boolean belongToProjectPhoto(Gpsgeom gp, Project p) {
        EntityManager em = emf.createEntityManager();
         Query q = em.createQuery("SELECT p FROM Photo p WHERE ( p.projectId =:id)");
        q.setParameter("id", p);
        List l = q.getResultList();
        boolean b = false;
        for(Object o:l){
            if(((Photo)o).getGpsgeomId().getGpsgeomId()==gp.getGpsgeomId()){
                b=true;
            }
        }
        return b;
    }


   
}
