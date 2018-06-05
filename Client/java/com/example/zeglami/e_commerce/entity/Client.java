package com.example.zeglami.e_commerce;

import java.util.Date;

/**
 * Created by hamidze on 27/02/2018.
 */

public class Client {

    int idclient;
    String loginclient;
    String mdpclient;
    String nomclient;
    String prenomclient;
    String telclient;
    String adressecli;
    Date datenaissance;
    String email;

    public int getIdclient() {
        return idclient;
    }

    public String getLoginclient() {
        return loginclient;
    }

    public String getMdpclient() {
        return mdpclient;
    }

    public String getNomclient() {
        return nomclient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }

    public String getTelclient() {
        return telclient;
    }

    public String getAdressecli() {
        return adressecli;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public void setLoginclient(String loginclient) {
        this.loginclient = loginclient;
    }

    public void setMdpclient(String mdpclient) {
        this.mdpclient = mdpclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }

    public void setTelclient(String telclient) {
        this.telclient = telclient;
    }

    public void setAdressecli(String adressecli) {
        this.adressecli = adressecli;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}