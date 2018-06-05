package entities;

import entities.Produit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, byte[]> photo;
    public static volatile SingularAttribute<Image, Produit> idproduit;
    public static volatile SingularAttribute<Image, Integer> idimage;

}