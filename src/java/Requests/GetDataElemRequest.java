/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import Classes.ElementType;
import Classes.Material;
import Managers.ElementManager;
import Managers.ElementManagerImpl;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
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
        ElementManager man = ElementManagerImpl.getInstance();

        ArrayList<ElementType> types = man.getElementTypes();
        ArrayList<Material> mater = man.getMaterials();

        request.setAttribute("types", JsonTransformer.ListTypeToJson(types));
        request.setAttribute("mater", JsonTransformer.ListMaterToJson(mater));

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(request.getAttribute("types"));
            out.println("#");
            out.println(request.getAttribute("mater"));
        }
    }
}
