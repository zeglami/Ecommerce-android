package entities;

import entities.Commande;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(Livreur.class)
public class Livreur_ { 

    public static volatile SingularAttribute<Livreur, String> nomlivreur;
    public static volatile SingularAttribute<Livreur, String> prenomlivreur;
    public static volatile SingularAttribute<Livreur, String> tellivreur;
    public static volatile SingularAttribute<Livreur, String> mdplivreur;
    public static volatile SingularAttribute<Livreur, Integer> idlivreur;
    public static volatile SingularAttribute<Livreur, String> loginlivreur;
    public static volatile CollectionAttribute<Livreur, Commande> commandeCollection;

}