/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import Classes.Data;
import Classes.DataSimple;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import Managers.SyncManager;
import Managers.SyncManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
        String json = JsonTransformer.DatatoJson(sman.prepareExport(Long.parseLong(data.getProject().getProjectId()+"")));
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
       
        }
        System.out.println(json);
        request.setAttribute("data", json);

    }
}
