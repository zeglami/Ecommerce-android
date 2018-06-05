package entities;

import entities.Client;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T15:55:44")
@StaticMetamodel(Evaluation.class)
public class Evaluation_ { 

    public static volatile SingularAttribute<Evaluation, String> titre;
    public static volatile SingularAttribute<Evaluation, Integer> idevaluation;
    public static volatile SingularAttribute<Evaluation, String> texte;
    public static volatile SingularAttribute<Evaluation, Client> idclient;
    public static volatile SingularAttribute<Evaluation, Integer> vote;

}