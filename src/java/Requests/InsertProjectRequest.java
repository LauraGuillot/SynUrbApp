/**
 * Classe InsertProjectRequest
 * ----------------------------------------------------------
 * Servlet appelée lorsqu'un utilisateur crée un nouveau projet.
 * Celui-ci est sauvegardé sur le serveur et renvoyer à l'utilisateur.
 */
package Requests;

import Classes.Data;
import Classes.DataSimple;
import Managers.GpsgeomManager;
import Managers.GpsgeomManagerImpl;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
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
@WebServlet(name = "InsertProjectRequest", urlPatterns = {"/InsertProjectRequest"})
public class InsertProjectRequest extends HttpServlet {

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

        //On récupère le projet
        String datajson = request.getParameter("data");
        DataSimple d = JsonTransformer.JsontoData(datajson);
        Data data = d.toData();

        //On sauvegarde le projet
        GpsgeomManager gman = GpsgeomManagerImpl.getInstance();
        gman.saveGpsgeom(data.getProject_gpsgeom());
        data.getProject().setGpsgeomId(data.getProject_gpsgeom());
        ProjectManager man = ProjectManagerImpl.getInstance();
        data.setProject(man.saveProject(data.getProject()));

        //On envoie les données du projet
        String json = JsonTransformer.DatatoJson(data);

        request.setAttribute("data", json);
        processRequest(request, response);
    }

}
