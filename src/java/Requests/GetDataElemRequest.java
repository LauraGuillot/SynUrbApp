/**
 * Classe GetDataElemRequest
 * ----------------------------------------------------------
 * Servlet appelée lorsqu'un utilisateur définit des éléments sur une photo.
 * Elle sert à obtenir la liste des matériaux ainsi que les types d'éléments disponibles.
 */
package Requests;

import Classes.ElementType;
import Classes.Material;
import Managers.ElementManager;
import Managers.ElementManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura
 */
@WebServlet(name = "GetDataElemRequest", urlPatterns = {"/GetDataElemRequest"})
public class GetDataElemRequest extends HttpServlet {

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
       
        //On récupère la liste des matériaux et la liste des types d'élément
        ElementManager man = ElementManagerImpl.getInstance();
        ArrayList<ElementType> types = man.getElementTypes();
        ArrayList<Material> mater = man.getMaterials();
        request.setAttribute("types", JsonTransformer.ListTypeToJson(types));
        request.setAttribute("mater", JsonTransformer.ListMaterToJson(mater));

        //On envoie ces listes séparées par un #
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(request.getAttribute("types"));
            out.println("#");
            out.println(request.getAttribute("mater"));
        }
    }
}
