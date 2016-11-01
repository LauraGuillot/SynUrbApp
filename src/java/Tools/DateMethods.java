/**
 * Classe DataMethod
 * ----------------------------------------------------------
 * Classe pour récupérer la date actuelle
 */
package Tools;

import java.sql.Timestamp;
import java.util.Date;

public class DateMethods {

    /**
     * Renvoi de la data actuelle
     *
     * @return Date
     */
    public static Timestamp getCurrentDate() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
