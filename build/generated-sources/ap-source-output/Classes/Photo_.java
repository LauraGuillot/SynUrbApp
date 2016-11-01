package Classes;

import Classes.Element;
import Classes.Gpsgeom;
import Classes.Project;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-01T11:01:33")
@StaticMetamodel(Photo.class)
public class Photo_ { 

    public static volatile SingularAttribute<Photo, String> photoAuthor;
    public static volatile SingularAttribute<Photo, String> photoPath;
    public static volatile CollectionAttribute<Photo, Element> elementCollection;
    public static volatile SingularAttribute<Photo, Integer> photoId;
    public static volatile SingularAttribute<Photo, String> photoDescription;
    public static volatile SingularAttribute<Photo, String> photoName;
    public static volatile SingularAttribute<Photo, Project> projectId;
    public static volatile SingularAttribute<Photo, Gpsgeom> gpsgeomId;
    public static volatile SingularAttribute<Photo, Date> photoLastModification;

}