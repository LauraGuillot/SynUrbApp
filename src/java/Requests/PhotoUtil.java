/**
 * Classe PhotoUtil
 * -----------------------------------------------------------------
 * Conversion d'images en bytes[] et vice versa pour les transferts
 * de photos entre le serveur et l'appli android
 */
package Requests;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

public class PhotoUtil {

    /**
     * Transformer une image en tableau de bytes
     *
     * @param photoPath path de la photo (featureapp/Photo_date)
     * @return tableau des bytes de la photo
     * @throws IOException
     */
    public static byte[] extractBytes(String photoPath) throws IOException {
        // open image
        BufferedImage bufferedImage = ImageIO.read(PhotoUtil.class.getResource(photoPath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    /**
     * Convertir un tableau de bytes en image
     *
     * @param bytes Tableau de bytes
     * @param path path de l'image à créer
     */
    public static void bytesToFile(byte[] bytes, String path) throws IOException {
        File photo  = new File("/src/java/Requests/"+path);
        photo.mkdir();
        
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        ImageIO.write(bImageFromConvert, "jpg", photo);
    }

    /**
     * Sauvegarde de la photo
     *
     * @param header header de la requête http, contient le path de la photo :
     * Content-Disposition: form-data; name="[path]";filename="[path]"
     * @param b Bytes de la photo
     */
    public static void savePicture(String header, byte[] b) throws IOException {
        //On récupère le path
        StringTokenizer st = new StringTokenizer(header, ";");
        st.nextToken(); //form-data
        String path = st.nextToken(); //" name=[path]"
        path = path.substring(6, path.length() - 1);
        
        //Enregistrement
        bytesToFile(b,path);
    }

}
