/**
 * Classe SaveDataRequest
 * ----------------------------------------------------------
 * Servlet appelée à chaque fois qu'un utilisateur veut sauvegarder ses données.
 * On synchronise alors les données de l'appli avec celle du serveur.
 */
package Requests;

import Classes.Data;
import Classes.DataSimple;
import Managers.SyncManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura
 */
@WebServlet(name = "SaveDataRequest", urlPatterns = {"/SaveDataRequest"})
public class SaveDataRequest extends HttpServlet {

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
        datajson = URLDecoder.decode(datajson, "UTF-8");
        DataSimple d = JsonTransformer.JsontoData(datajson);
        Data data = d.toData();

        //Synchronisation
        SyncManagerImpl sman = new SyncManagerImpl();
        sman.UpdateData(data);

        //On renvoie les données
        String json = JsonTransformer.DatatoJson(sman.prepareExport(Long.parseLong(data.getProject().getProjectId() + "")));
        try (PrintWriter out = response.getWriter()) {
            out.println(json);

        }
        System.out.println(json);
        request.setAttribute("data", json);

    }
}
