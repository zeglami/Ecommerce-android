package entities;

import entities.Client;
import entities.LigneDeCommande;
import entities.Livreur;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(Commande.class)
public class Commande_ { 

    public static volatile SingularAttribute<Commande, String> datecommande;
    public static volatile SingularAttribute<Commande, Integer> idcommande;
    public static volatile SingularAttribute<Commande, Livreur> idlivreur;
    public static volatile SingularAttribute<Commande, String> prenomlivraison;
    public static volatile SingularAttribute<Commande, String> tellivraison;
    public static volatile SingularAttribute<Commande, String> nomlivraison;
    public static volatile SingularAttribute<Commande, Client> idclient;
    public static volatile SingularAttribute<Commande, String> etat;
    public static volatile CollectionAttribute<Commande, LigneDeCommande> ligneDeCommandeCollection;
    public static volatile SingularAttribute<Commande, String> adresselivraison;

}