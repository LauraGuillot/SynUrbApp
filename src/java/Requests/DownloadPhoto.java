/**
 * Classe DownloadPhoto
 * ----------------------------------------------------------
 * Servlet appelée quand un utilisateur ouvre un projet. Elle envoie alors
 * toutes les photos du projet
 */
package Requests;

import Classes.Photo;
import Managers.PhotoManager;
import Managers.PhotoManagerImpl;
import Managers.ProjectManager;
import Managers.ProjectManagerImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

         //On récupère l'id de la photo à ouvrir
        int id = Integer.parseInt(request.getParameter("photoid"));

        //On récupère la lphoto
        PhotoManager manager = PhotoManagerImpl.getInstance();
        Photo p = manager.getPhotoById(id);

        //On l'envoie
        response.setContentType("image/jpg");
        OutputStream out = response.getOutputStream();      
        byte[] bytes = PhotoUtil.extractBytes(p.getPhotoPath());
        int length = bytes.length;
        response.setContentLength(length);
        out.write(bytes);
        out.flush();   
    }
}
