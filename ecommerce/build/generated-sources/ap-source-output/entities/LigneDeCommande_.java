package entities;

import entities.Commande;
import entities.LigneDeCommandePK;
import entities.Produit;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(LigneDeCommande.class)
public class LigneDeCommande_ { 

    public static volatile SingularAttribute<LigneDeCommande, Produit> produit;
    public static volatile SingularAttribute<LigneDeCommande, LigneDeCommandePK> ligneDeCommandePK;
    public static volatile SingularAttribute<LigneDeCommande, Commande> commande;
    public static volatile SingularAttribute<LigneDeCommande, BigInteger> qtecomm;

}