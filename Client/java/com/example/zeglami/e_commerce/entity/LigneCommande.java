package com.example.zeglami.e_commerce;
/**
 * Created by hamidze on 19/03/2018.
 */

public class LigneCommande {
    public int idcommande;
    public int idproduit;
    public int qtecommu;

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getQtecommu() {
        return qtecommu;
    }

    public void setQtecommu(int qtecommu) {
        this.qtecommu = qtecommu;
    }

}
