/**
 * Classe CloseRequest
 * ----------------------------------------------------------
 * Servlet appelée lorsqu'un utilisateur ferme un projet et ne souhaite
 * pas enregistrer les modifications qu'il a faites.
 */
package Requests;

import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura
 */
@WebServlet(name = "CloseRequest", urlPatterns = {"/CloseRequest"})
public class CloseRequest extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //On récupère l'id du projet à fermer
        long id = Long.parseLong(request.getParameter("projectid"));

        //On ferme le projet
        ProjectManager projectManager = ProjectManagerImpl.getInstance();
        projectManager.setAvailable(id);

    }

}
