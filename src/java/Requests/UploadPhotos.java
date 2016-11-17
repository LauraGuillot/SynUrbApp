/**
 * Classe UploadPhoto
 * ----------------------------------------------------------
 * Servlet appelée pour l'upload des photos sur le serveur
 */
package Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.IOUtils;


/**
 *
 * @author Laura
 */
@WebServlet(name = "UploadPhotos", urlPatterns = {"/UploadPhotos"})
public class UploadPhotos extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InputStream stream = request.getInputStream();

        BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(stream));
        //Première ligne : "--*****"
        responseStreamReader.readLine();
        
        //On lit le header
        String header = responseStreamReader.readLine();
        System.out.println(header);
        
        //Troisième ligne : vide
        responseStreamReader.readLine();

        //bytes
        String pix = responseStreamReader.readLine();
        ArrayList<String> pixels = new ArrayList<>();
        while(pix!=null && !pix.equals("")){
           pixels.add(pix);
           System.out.println(pix);
           pix = responseStreamReader.readLine();
           
        }
        byte[] bytes = pix.getBytes();
        
      
        responseStreamReader.close();

        //Erreur sur cette ligne
       // PhotoUtil.savePicture(header, bytes);
    }

}
