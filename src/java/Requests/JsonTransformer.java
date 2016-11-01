/**
 * Classe JsonTrnsformer
 * ----------------------------------------------------------
 * Cette classe permet la  conversion des objets Java en json et vice versa
 * pour les transferts entre le serveur et l'application.
 */
package Requests;

import Classes.Data;
import Classes.DataSimple;
import Classes.ElementType;
import Classes.ElementTypeSimple;
import Classes.Material;
import Classes.MaterialSimple;
import Classes.Project;
import Classes.ProjectSimple;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class JsonTransformer {

    private final static GsonBuilder builder = new GsonBuilder();
    private final static Gson gson = builder.create();

    /**
     * Convertir un object Data en json
     *
     * @param d Data
     * @return String json
     */
    public static String DatatoJson(Data d) {
        return gson.toJson(new DataSimple(d));
    }

    /**
     * Conversion d'une chaîne json en data
     *
     * @param json String json
     * @return Data object
     */
    public static DataSimple JsontoData(String json) {
        return gson.fromJson(json, DataSimple.class);
    }

    /**
     * Convertir la liste des projets en json
     *
     * @param p
     * @return
     */
    public static String ListProjectsToJson(ArrayList<Project> p) {
        ProjectSimple[] ps = new ProjectSimple[p.size()];
        for (int i = 0; i < p.size(); i++) {
            ps[i] = new ProjectSimple(p.get(i));
        }

        return gson.toJson(ps, ProjectSimple[].class);

    }

    /**
     * Convertir une liste de matériaux en json
     *
     * @param m liste de materiaux
     * @return json
     */
    public static String ListMaterToJson(ArrayList<Material> m) {
        MaterialSimple[] ms = new MaterialSimple[m.size()];
        for (int i = 0; i < m.size(); i++) {
            ms[i] = new MaterialSimple(m.get(i));
        }

        return gson.toJson(ms, ProjectSimple[].class);
    }

    /**
     * Convertir une liste de types
     *
     * @param t liste de types
     * @return json
     */
    public static String ListTypeToJson(ArrayList<ElementType> t) {
        ElementTypeSimple[] ts = new ElementTypeSimple[t.size()];
        for (int i = 0; i < t.size(); i++) {
            ts[i] = new ElementTypeSimple(t.get(i));
        }

        return gson.toJson(ts, ProjectSimple[].class);
    }

}
