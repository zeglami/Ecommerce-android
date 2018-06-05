package com.example.zeglami.e_commerce;


import java.util.Date;

/**
 * Created by hamidze on 19/03/2018.
 */

public class Commande {
    public int idcommande;
    public String adresselivraison;
    public String nomlivraison;
    public String prenomlivraison;
    public String tellivraison;
    public Date datecommande;
    public String etat;
    public int idlivreur;
    public int idclient;

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

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}
