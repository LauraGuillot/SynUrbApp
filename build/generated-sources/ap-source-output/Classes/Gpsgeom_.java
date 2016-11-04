package Classes;

import Classes.Photo;
import Classes.Project;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-03T15:50:37")
@StaticMetamodel(Gpsgeom.class)
public class Gpsgeom_ { 

    public static volatile CollectionAttribute<Gpsgeom, Photo> photoCollection;
    public static volatile CollectionAttribute<Gpsgeom, Project> projectCollection;
    public static volatile SingularAttribute<Gpsgeom, Integer> gpsgeomId;
    public static volatile SingularAttribute<Gpsgeom, String> gpsgeomThegeom;

}