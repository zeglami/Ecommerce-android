/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Saad YOUSFI
 */
@Embeddable
public class LigneDeCommandePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idcommande")
    private int idcommande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproduit")
    private int idproduit;

    public LigneDeCommandePK() {
    }

    public LigneDeCommandePK(int idcommande, int idproduit) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcommande;
        hash += (int) idproduit;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneDeCommandePK)) {
            return false;
        }
        LigneDeCommandePK other = (LigneDeCommandePK) object;
        if (this.idcommande != other.idcommande) {
            return false;
        }
        if (this.idproduit != other.idproduit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LigneDeCommandePK[ idcommande=" + idcommande + ", idproduit=" + idproduit + " ]";
    }
    
}
