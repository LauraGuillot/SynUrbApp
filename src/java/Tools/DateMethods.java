
package Tools;


import java.sql.Timestamp;
import java.util.Date;


public class DateMethods {
    
    
    public static Timestamp getCurrentDate(){ 
        Date date = new Date(); 
        return new Timestamp(date.getTime());
    }
}
