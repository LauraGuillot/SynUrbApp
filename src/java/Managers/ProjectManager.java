/**
 * Interface ProjectManager
 * ----------------------------------------------------------
 * Définition des méthodes pour manipuler les projets de la base de données
 */
package Managers;

import Classes.Photo;
import Classes.Project;
import java.util.Collection;

/**
 *
 * @author Laura
 */
public interface ProjectManager {

    /**
     * Liste des projets
     *
     * @return liste de projets
     */
    public Collection<Project> listProjects();

    /**
     * Get a project with his id
     *
     * @param id Id of the project
     * @return Project
     */
    public Project getProjectById(long id);

    /**
     * Get a project with his name
     *
     * @param name Name of the project
     * @return Project
     */
    public Project getProjectByName(String name);

    /**
     * Rendre un projet disponible
     *
     * @param id Id du projet
     */
    public void setAvailable(long id);

    /**
     * Rendre un projet indisponible
     *
     * @param id Id du projet
     */
    public void setUnavailable(long id);

    /**
     * Mise à jour d'un projet
     *
     * @param newp Projet avec les mises à jour
     * @return projet mis à jour
     */
    public Project updateProject(Project newp);

    /**
     * Ajout d'un projet dans la base
     *
     * @param p Projet à ajouter (sans id)
     * @return Projet ajouté (avec id)
     */
    public Project saveProject(Project p);

    /**
     * Récupérer la liste des photos d'un projet
     *
     * @param id Id du projet
     * @return Collection de photos
     */
    public Collection<Photo> getPhotoOfProject(Long id);

    /**
     * Synchronisation d'un projet : mise à jour si il existe déjà ou ajout
     * sinon
     *
     * @param p Projet à synchroniser
     * @return Projet synchronisé
     */
    public Project sync(Project p);

}
