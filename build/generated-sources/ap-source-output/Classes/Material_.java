package Classes;

import Classes.Element;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-01T11:01:33")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> materialName;
    public static volatile CollectionAttribute<Material, Element> elementCollection;
    public static volatile SingularAttribute<Material, Double> materialConduct;
    public static volatile SingularAttribute<Material, Integer> materialHeatCapa;
    public static volatile SingularAttribute<Material, Integer> materialId;
    public static volatile SingularAttribute<Material, Integer> materialMassDensity;

}