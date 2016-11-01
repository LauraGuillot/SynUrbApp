/**
 * Classe OpenProjectRequest
 * ----------------------------------------------------------
 * Servlet appelée à chaque fois qu'un utilisateur veut ouvrir un projet.
 * On lui envoie alors toutes les données relatives au projet.
 */
package Requests;

import Classes.Data;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import Managers.SyncManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura
 */
@WebServlet(name = "OpenProjectRequest", urlPatterns = {"/OpenProjectRequest"})
public class OpenProjectRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(request.getAttribute("data"));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        //On récupère l'id du projet à ouvrir
        long id = Long.parseLong(request.getParameter("projectid"));

        //On met le projet unavailable
        ProjectManager projectManager = ProjectManagerImpl.getInstance();
        projectManager.setUnavailable(id);

        //On envoie les données du projet
        SyncManagerImpl man = new SyncManagerImpl();
        Data d = man.prepareExport(id);

        //Data to json
        String data = JsonTransformer.DatatoJson(d);
        request.setAttribute("data", data);
        processRequest(request, response);
    }

}
