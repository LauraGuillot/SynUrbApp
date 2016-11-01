/**
 * Classe DownloadPhoto
 * ----------------------------------------------------------
 * Servlet appelée quand un utilisateur ouvre un projet. Elle envoie alors
 * toutes les photos du projet
 */
package Requests;

import Classes.Photo;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura
 */
@WebServlet(name = "DownloadPhoto", urlPatterns = {"/DownloadPhoto"})
public class DownloadPhoto extends HttpServlet {

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
        //TODO
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

        //On récupère la liste des photos du projet
        ProjectManager projectManager = ProjectManagerImpl.getInstance();
        Collection<Photo> photos = projectManager.getPhotoOfProject(id);

        response.setContentType("image/gif");
        OutputStream out = response.getOutputStream();
        //TODO

    }

}
