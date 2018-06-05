/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author Saad YOUSFI
 */
public class commandejson {
    private int idcommande;
    private int idlivreur;
    private int idclient;
    private String adresselivraison;
    private String nomlivraison;
    private String prenomlivraison;
    private String tellivraison;
    private String datecommande;
    private String etat;
    
        
    public void commandejson(String etat,int idcommande,int idlivreur,int idclient,String adresselivraison,String nomlivraison,String prenomlivraison,String tellivraison,String datecommande){
        this.idcommande = idcommande;
        this.idclient = idclient;
        this.idlivreur = idlivreur;
        this.adresselivraison = adresselivraison;
        this.nomlivraison = nomlivraison;
        this.prenomlivraison = prenomlivraison;
        this.tellivraison = tellivraison;
        this.datecommande = datecommande;
        this.etat = etat;
    }


    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public String getAdresselivraison() {
        return adresselivraison;
    }

    public void setAdresselivraison(String adresselivraison) {
        this.adresselivraison = adresselivraison;
    }

    public String getNomlivraison() {
        return nomlivraison;
    }

    public void setNomlivraison(String nomlivraison) {
        this.nomlivraison = nomlivraison;
    }

    public String getPrenomlivraison() {
        return prenomlivraison;
    }

    public void setPrenomlivraison(String prenomlivraison) {
        this.prenomlivraison = prenomlivraison;
    }

    public String getTellivraison() {
        return tellivraison;
    }

    public void setTellivraison(String tellivraison) {
        this.tellivraison = tellivraison;
    }

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
