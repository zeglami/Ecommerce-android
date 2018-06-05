/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saad YOUSFI
 */
@Entity
@Table(name = "ligne_de_commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneDeCommande.findAll", query = "SELECT l FROM LigneDeCommande l")
    , @NamedQuery(name = "LigneDeCommande.findByIdcommande", query = "SELECT l FROM LigneDeCommande l WHERE l.ligneDeCommandePK.idcommande = :idcommande")
    , @NamedQuery(name = "LigneDeCommande.findByIdproduit", query = "SELECT l FROM LigneDeCommande l WHERE l.ligneDeCommandePK.idproduit = :idproduit")
    , @NamedQuery(name = "LigneDeCommande.findByQtecomm", query = "SELECT l FROM LigneDeCommande l WHERE l.qtecomm = :qtecomm")})
public class LigneDeCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LigneDeCommandePK ligneDeCommandePK;
    @Column(name = "Qtecomm")
    private BigInteger qtecomm;
    @JoinColumn(name = "idcommande", referencedColumnName = "idcommande", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commande commande;
    @JoinColumn(name = "idproduit", referencedColumnName = "idproduit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produit produit;


    public LigneDeCommande() {
    }

    public LigneDeCommande(LigneDeCommandePK ligneDeCommandePK) {
        this.ligneDeCommandePK = ligneDeCommandePK;
    }

    public LigneDeCommande(int idcommande, int idproduit) {
        this.ligneDeCommandePK = new LigneDeCommandePK(idcommande, idproduit);
    }

    public LigneDeCommandePK getLigneDeCommandePK() {
        return ligneDeCommandePK;
    }

    public void setLigneDeCommandePK(LigneDeCommandePK ligneDeCommandePK) {
        this.ligneDeCommandePK = ligneDeCommandePK;
    }

    public BigInteger getQtecomm() {
        return qtecomm;
    }

    public void setQtecomm(BigInteger qtecomm) {
        this.qtecomm = qtecomm;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ligneDeCommandePK != null ? ligneDeCommandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneDeCommande)) {
            return false;
        }
        LigneDeCommande other = (LigneDeCommande) object;
        if ((this.ligneDeCommandePK == null && other.ligneDeCommandePK != null) || (this.ligneDeCommandePK != null && !this.ligneDeCommandePK.equals(other.ligneDeCommandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LigneDeCommande[ ligneDeCommandePK=" + ligneDeCommandePK + " ]";
    }
    
}
