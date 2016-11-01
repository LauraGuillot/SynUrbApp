package Managers;

import Classes.Element;
import Classes.ElementType;
import Classes.Material;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ElementManagerImpl implements ElementManager {

    //Attributs
    private EntityManagerFactory emf;
    private static ElementManagerImpl theElementManager;

    //Constructeur
    private ElementManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SynUrbAppPU");
        }
    }

    //Instanciation
    public static ElementManager getInstance() {
        if (theElementManager == null) {
            theElementManager = new ElementManagerImpl();
        }
        return theElementManager;
    }

    /**
     * Get an element with his id
     *
     * @param id Id of the element
     * @return element
     */
    @Override
    public Element getElementById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Element.findByElementId");
        q.setParameter("elementId", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Element) l.get(0);
    }

    /**
     * Ajout d'un element dans la base
     *
     * @param e Element à ajouter (sans id)
     */
    @Override
    public void saveElement(Element e) {
        Element e1 = new Element();
        e1.setElementColor(e.getElementColor());
        e1.setElementTypeId(e.getElementTypeId());
        e1.setMaterialId(e.getMaterialId());
        e1.setPhotoId(e.getPhotoId());
        e1.setPixelgeomId(e.getPixelgeomId());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e1);
        em.getTransaction().commit();
    }

    /**
     * Mise à jour d'un element dans la base
     *
     * @param e Element à mettre à jour
     */
    @Override
    public void updateElement(Element e) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Element e1 = em.find(Element.class, e.getElementId());
        e1.setElementColor(e.getElementColor());
        e1.setElementTypeId(e.getElementTypeId());
        e1.setMaterialId(e.getMaterialId());
        e1.setPhotoId(e.getPhotoId());
        e1.setPixelgeomId(e.getPixelgeomId());
        em.merge(e1);
        em.getTransaction().commit();
    }

    /**
     * Synchronisation d'un élément : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param e Element à synchroniser
     */
    @Override
    public void sync(Element e) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Element e WHERE (e.elementId=:id)");
        q.setParameter("id", e.getElementId());

        List l = q.getResultList();

        if (l.isEmpty()|| ((Element)l.get(0)).getPhotoId().getProjectId().getProjectId()!=e.getPhotoId().getProjectId().getProjectId()) {
            System.out.println("save");
            this.saveElement(e);
        } else {
            System.out.println("up");
            this.updateElement(e);
        }

    }

    /**
     * Récupérer un element type avec son id
     *
     * @param id id de l'element type
     * @return Element type
     */
    @Override
    public ElementType getElementTypeById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("ElementType.findByElementTypeId");
        q.setParameter("elementTypeId", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (ElementType) l.get(0);
    }

    /**
     * Récupérer un materiau avec son id
     *
     * @param id id du materiau
     * @return Materiau
     */
    @Override
    public Material getMaterialById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Material.findByMaterialId");
        q.setParameter("materialId", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Material) l.get(0);
    }

    @Override
    public ArrayList<ElementType> getElementTypes() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("ElementType.findAll");
        List l = q.getResultList();

        ArrayList<ElementType> m = new ArrayList<>();
        for (Object o : l) {
            m.add((ElementType) o);
        }
        return m;
    }

    @Override
    public ArrayList<Material> getMaterials() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Material.findAll");
        List l = q.getResultList();

        ArrayList<Material> m = new ArrayList<>();
        for (Object o : l) {
            m.add((Material) o);
        }
        return m;
    }

}
