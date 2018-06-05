package com.example.zeglami.e_commerce;

import java.util.Date;

/**
 * Created by hamidze on 27/02/2018.
 */

public class Catalogue {

    private Integer idcatalogue;
    private String nomcatalogue;
    private Date datecatalogue;

    public Integer getIdcatalogue() {
        return idcatalogue;
    }

    public String getNomcatalogue() {
        return nomcatalogue;
    }

    public Date getDatecatalogue() {
        return datecatalogue;
    }



    public void setIdcatalogue(Integer idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

    public void setNomcatalogue(String nomcatalogue) {
        this.nomcatalogue = nomcatalogue;
    }

    public void setDatecatalogue(Date datecatalogue) {
        this.datecatalogue = datecatalogue;
    }

}
