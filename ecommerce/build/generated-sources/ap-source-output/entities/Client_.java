package entities;

import entities.Commande;
import entities.Evaluation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> mdpclient;
    public static volatile SingularAttribute<Client, String> nomclient;
    public static volatile SingularAttribute<Client, String> loginclient;
    public static volatile SingularAttribute<Client, Date> datenaissance;
    public static volatile SingularAttribute<Client, String> prenomclient;
    public static volatile SingularAttribute<Client, String> telclient;
    public static volatile SingularAttribute<Client, String> adressecli;
    public static volatile CollectionAttribute<Client, Evaluation> evaluationCollection;
    public static volatile SingularAttribute<Client, Integer> idclient;
    public static volatile SingularAttribute<Client, String> email;
    public static volatile CollectionAttribute<Client, Commande> commandeCollection;

}