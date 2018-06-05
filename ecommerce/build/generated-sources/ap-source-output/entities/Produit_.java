package entities;

import entities.LigneDeCommande;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(Produit.class)
public class Produit_ { 

    public static volatile SingularAttribute<Produit, String> typeproduit;
    public static volatile SingularAttribute<Produit, Integer> prix;
    public static volatile SingularAttribute<Produit, String> nomproduit;
    public static volatile SingularAttribute<Produit, Integer> idproduit;
    public static volatile SingularAttribute<Produit, Integer> idcatalogue;
    public static volatile CollectionAttribute<Produit, LigneDeCommande> ligneDeCommandeCollection;

}