/**
 * Classe ProjectListRequest
 * ----------------------------------------------------------
 * Servlet appelée à l'ouverture de l'application pour récupérer la liste des projets
 */
package Requests;

import Classes.Project;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura
 */
public class ProjectListRequest extends HttpServlet {

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
            out.println(request.getAttribute("project_list"));

        }
    }

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
        //On récupère tous les projets de la base de données
        ProjectManager projectManager = ProjectManagerImpl.getInstance();
        Collection<Project> p = projectManager.listProjects();
        ArrayList<Project> p1 = new ArrayList<>();
        p.stream().forEach((pr) -> {
            p1.add(pr);
        });

        //On envoie les projets
        request.setAttribute("project_list", JsonTransformer.ListProjectsToJson(p1));
        processRequest(request, response);
    }

}
