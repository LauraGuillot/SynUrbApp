/**
 * CLASSE MaterialCSVParser
 * -----------------------------
 * Parser pour la liste des matériaux des fichiers csv et xml
 */
package Parser;

import Classes.Material;
import Managers.ElementManager;
import Managers.ElementManagerImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class MaterialCSVParser {

    /**
     * Conversion d'une ligne du fichier CSV en matériau Une ligne se compose
     * des attributs suivants : Description (short), Description (long),
     * Creation date, Update date, Type, Manufacturer, Origin, References,URL,
     * Technical documentation, Conductivity (min bound) (W.m-1.K-1),
     * Conductivity (max bound) (W.m-1.K-1), Thermal capacity (min bound)
     * (J.K-1.kg-1), Thermal capacity (max bound) (J.K-1.kg-1), Density (min
     * bound) (kg.m-3), Density (max bound) (kg.m-3), Emissivity (min bound),
     * Emissivity (max bound), Emissivity (conditions), Solar reflectivity (min
     * bound), Solar reflectivity (max bound)
     *
     * @param line ligne CSV
     * @return Materiau
     */
    public static Material lineToMater(String line) {
        StringTokenizer st = new StringTokenizer(line, ",");

        String name = st.nextToken();//Description (short)
        st.nextToken();//Description (long)
        st.nextToken();//Creation date
        st.nextToken();//Update date
        st.nextToken();//Type
        st.nextToken();//Manufacturer
        st.nextToken();//Origin
        st.nextToken();//References
        st.nextToken();//URL
        st.nextToken();//Technical documentation
        Double cond = Double.parseDouble(st.nextToken());//Conductivity (min bound) (W.m-1.K-1)
        st.nextToken();//Conductivity (max bound) (W.m-1.K-1)
        Double therm = Double.parseDouble(st.nextToken());;//Thermal capacity (min bound) (J.K-1.kg-1)
        st.nextToken();//Thermal capacity (maxbound) (J.K-1.kg-1)
        Double dens = Double.parseDouble(st.nextToken());;//Density (min bound) (kg.m-3)
        st.nextToken();//Density (max bound) (kg.m-3)

        Material m = new Material();

        if (cond != -1) {
            m.setMaterialConduct(cond);
        }
        if (therm != -1) {
            m.setMaterialHeatCapa(therm);
        }
        if (dens != -1) {
            m.setMaterialMassDensity(dens);
        }
        m.setMaterialName(name);

        return m;
    }

    /**
     * Lecture
     */
    public static void readCSV() {
        ArrayList<Material> mater = new ArrayList<>();
        String line = null;
        int n = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(MaterialCSVParser.class.getResourceAsStream("material_list.csv")));
            br.readLine(); // liste des colonnes du CSV
            br.readLine();//Ligne de description
            br.readLine();//Ligne de description
            br.readLine();//Ligne de description
            while ((line = br.readLine()) != null) { 
                n++;
                Material m = lineToMater(line);
                m.setMaterialId(n);             
                mater.add(m);
            }
            //Ajout du fichier xml
            parseXml(mater);

            //Sauvegarde
            save(mater);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour enregistrer la liste des matériaux dans la base de données
     *
     * @param mater liste des materiaux
     */
    private static void save(ArrayList<Material> mater) {
        ElementManager man = ElementManagerImpl.getInstance();
        man.saveMater(mater);
    }

    /**
     * Parser pour le fichier materiau.xml
     *
     * @param mater liste de materiaux à laquelle on ajoute ceux obtenus par le
     * xml
     */
    public static void parseXml(ArrayList<Material> mater) {

//On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();

        try {
            //On crée un nouveau document JDOM avec en argument le fichier XML
            //Le parsing est terminé ;)
            Document document = sxb.build(new InputStreamReader(MaterialCSVParser.class.getResourceAsStream("materiau.xml")));

            //On initialise un nouvel élément racine avec l'élément racine du document.
            Element racine = document.getRootElement();

            //List des materiaux
            List materiaux = racine.getChildren("materiau");

            //On ajoute chaque materiau à la liste
            Iterator i = materiaux.iterator();
            while (i.hasNext()) {
                Element e = (Element) i.next();
                String name = e.getChild("nom").getText();
                double cond = Double.parseDouble(e.getChild("conductivite").getText());
                double therm = Double.parseDouble(e.getChild("capacite_thermique").getText());
                double dens = Double.parseDouble(e.getChild("masse_volumique").getText());
                Material m = new Material();
                m.setMaterialConduct(cond);
                m.setMaterialHeatCapa(therm);
                m.setMaterialMassDensity(dens);
                m.setMaterialName(name);
                m.setMaterialId(mater.size() + 1);
                mater.add(m);
            }
        } catch (IOException ex) {
            Logger.getLogger(MaterialCSVParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(MaterialCSVParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
