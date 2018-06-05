package com.example.zeglami.e_commerce;

/**
 * Created by hamidze on 27/02/2018.
 */

public class Produit {

    private Integer idproduit;
    private String nomproduit;
    private String typeproduit;
    private int prix;
    private int idcatalogue;

    public Integer getIdproduit() {
        return idproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public int getPrix() {
        return prix;
    }

    public int getIdcatalogue() {
        return idcatalogue;
    }

    public void setIdproduit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setIdcatalogue(int idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

}
