/**
 * CLASSE MaterialSimple
 * ----------------------------------------------------------
 * Cette classe représente un matériau.
 * Elle a été crée dans un souci de compatibilité entre la classe material de l'appliction android
 * et la classe material de ce web service qui correspond à la table de la base de donnée du serveur.
 */
package Classes;

/**
 *
 * @author Laura
 */
public class MaterialSimple {

    private long material_id;
    private String material_name;
    
    /**
     * Conversion d'un materialSimple en material
     * @param m Mateerial
     */
    public MaterialSimple(Material m) {
        material_id=m.getMaterialId();
        material_name=m.getMaterialName();
    }
    
    
}
