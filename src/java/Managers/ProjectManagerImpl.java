package Managers;

import Classes.Photo;
import Classes.Project;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Tools.*;
import java.sql.Timestamp;

public class ProjectManagerImpl implements ProjectManager {

    //Attributs
    private EntityManagerFactory emf;
    private static ProjectManagerImpl theProjectManager;

    //Constructeur
    private ProjectManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SynUrbAppPU");
        }
    }

    //Instanciation
    public static ProjectManager getInstance() {
        if (theProjectManager == null) {
            theProjectManager = new ProjectManagerImpl();
        }
        return theProjectManager;
    }

    /**
     * Méthode renvoyant la liste des projets
     *
     * @return Collection de projets
     */
    @Override
    public Collection<Project> listProjects() {
        EntityManager em = emf.createEntityManager();
       Query q = em.createNamedQuery("Project.findAll");
        Collection theList = q.getResultList();
        return theList;
    }

    /**
     * Get a project with his id
     *
     * @param id Id of the project
     * @return Project
     */
    @Override
    public Project getProjectById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Project.findByProjectId");
        q.setParameter("projectId", id);
        List l = q.getResultList();
        return l.isEmpty()?null:(Project) l.get(0);
    }

    /**
     * Get a project with his name
     *
     * @param name Name of the project
     * @return Project
     */
    @Override
    public Project getProjectByName(String name) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Project.findByProjectName");
        q.setParameter("projectName", name);
        List l = q.getResultList();
        return l.isEmpty()?null:(Project) l.get(0);
    }

    /**
     * Rendre un projet disponible
     *
     * @param id Id du projet
     */
    @Override
    public void setAvailable(long id) {
        Project p = getProjectById(id);
        p.setProjectIsavailable(true);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

    /**
     * Rendre un projet indisponible
     *
     * @param id Id du projet
     */
    @Override
    public void setUnavailable(long id) {
        Project p = getProjectById(id);
        p.setProjectIsavailable(false);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();

    }
    
    /**
     * Mise à jour d'un projet
     * @param newp Projet avec les mises à jour
     * @return Projet mis à jour
     */
    @Override
    public Project updateProject(Project newp){
        Project oldp = getProjectById(newp.getProjectId());
        oldp.setGpsgeomId(newp.getGpsgeomId());
        oldp.setProjectDescription(newp.getProjectDescription());
        oldp.setProjectName(newp.getProjectName());
        oldp.setProjectVersion(oldp.getProjectVersion()+1);
        newp.setProjectVersion(oldp.getProjectVersion());
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(oldp);
        em.getTransaction().commit();
         return newp;
    }
    
    /**
     * Ajout d'un projet dans la base
     * @param p Projet à ajouter (sans id)
     * @return Projet ajouté (avec id)
     */
    @Override
    public Project saveProject(Project p){
        
        Project p1  = new Project();
        p1.setGpsgeomId(p.getGpsgeomId());
        p1.setProjectName(p.getProjectName());
        p1.setProjectDescription(p.getProjectDescription());
        p1.setProjectIsavailable(p.getProjectIsavailable());
        p1.setProjectVersion(p.getProjectVersion());
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();
        
        return this.getProjectByName(p.getProjectName());
    }
    
    /**
     * Récupérer la liste des photos d'un projet
     * @param id Id du projet
     * @return Collection de photos
     */
    @Override
    public Collection<Photo> getPhotoOfProject (Long id){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Photo p WHERE (p.projectId=:id)");
        q.setParameter("id", this.getProjectById(id));
        List l = q.getResultList();
       return l;
    }

    /**
     * Synchronisation d'un projet : mise à jour si il existe déjà ou ajout sinon
     * @param p Projet à synchroniser
     * @return Projet synchronisé
     */
    @Override
    public Project sync(Project p){
        Project p1 = this.getProjectById(p.getProjectId());
 
        if(p1==null ){
            p1=this.saveProject(p);
            System.out.println(p1.getProjectId());
        }else{
            p1 = this.updateProject(p);
        }
        return p1;
    }
  
}
