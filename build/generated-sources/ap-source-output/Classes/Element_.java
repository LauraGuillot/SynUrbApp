package Classes;

import Classes.ElementType;
import Classes.Material;
import Classes.Photo;
import Classes.Pixelgeom;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-03T15:50:37")
@StaticMetamodel(Element.class)
public class Element_ { 

    public static volatile SingularAttribute<Element, Integer> elementId;
    public static volatile SingularAttribute<Element, Pixelgeom> pixelgeomId;
    public static volatile SingularAttribute<Element, Photo> photoId;
    public static volatile SingularAttribute<Element, ElementType> elementTypeId;
    public static volatile SingularAttribute<Element, Material> materialId;
    public static volatile SingularAttribute<Element, String> elementColor;

}