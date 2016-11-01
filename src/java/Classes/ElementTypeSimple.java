/**
 * CLASSE ElementTypeSimple
 * ----------------------------------------------------------
 * Cette classe représente un type d'élément avec son nom et son id.
 * Elle a été crée dans un souci de compatibilité entre la classe ElementType de l'appliction android
 * et la classe ElementType de ce web service qui correspond à la table de la base de donnée du serveur.
 */
package Classes;

/**
 *
 * @author Laura
 */
public class ElementTypeSimple {

    private long elementType_id;
    private String elementType_name;
    
    /**
     * Conversion d'un ElementTypeSimple en ElementType
     * @param e Element type correspondant
     */
    public ElementTypeSimple(ElementType e) {
        elementType_id = e.getElementTypeId();
        elementType_name = e.getElementTypeName();
    }
    
}
