package Classes;

import Classes.Gpsgeom;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-03T15:50:37")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Boolean> projectIsavailable;
    public static volatile SingularAttribute<Project, String> projectDescription;
    public static volatile SingularAttribute<Project, String> projectName;
    public static volatile SingularAttribute<Project, Integer> projectId;
    public static volatile SingularAttribute<Project, Gpsgeom> gpsgeomId;
    public static volatile SingularAttribute<Project, Integer> projectVersion;

}