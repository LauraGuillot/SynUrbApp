/**
 * Classe CloseProjectRequest
 * ----------------------------------------------------------
 * Servlet appelée lorsqu'un utilisateur ferme un projet et souhaite
 * enregistrer les modifications qu'il a faites.
 */
package Requests;

import Classes.Data;
import Classes.DataSimple;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import Managers.SyncManagerImpl;
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
@WebServlet(name = "CloseProjectRequest", urlPatterns = {"/CloseProjectRequest"})
public class CloseProjectRequest extends HttpServlet {

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

        //On récupère les données à sauvegarder
        String datajson = request.getParameter("data");
        DataSimple d = JsonTransformer.JsontoData(datajson);
        Data data = d.toData();
        System.out.println(datajson);
        //On ferme le projet
        ProjectManager projectManager = ProjectManagerImpl.getInstance();
        projectManager.setAvailable(data.getProject().getProjectId());
        System.out.println(data.getProject().getProjectId());

        //Synchronisation
        SyncManagerImpl sman = new SyncManagerImpl();
        sman.UpdateData(data);

    }

}
