/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saad YOUSFI
 */
@Entity
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdproduit", query = "SELECT p FROM Produit p WHERE p.idproduit = :idproduit")
    , @NamedQuery(name = "Produit.findByNomproduit", query = "SELECT p FROM Produit p WHERE p.nomproduit = :nomproduit")
    , @NamedQuery(name = "Produit.findByTypeproduit", query = "SELECT p FROM Produit p WHERE p.typeproduit = :typeproduit")
    , @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix")})
public class Produit implements Serializable {

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduit")
    //private Collection<Image> imageCollection;

    @Column(name = "prix")
    private Integer prix;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduit")
    private Integer idproduit;
    @Size(max = 255)
    @Column(name = "nomproduit")
    private String nomproduit;
    @Size(max = 250)
    @Column(name = "typeproduit")
    private String typeproduit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
    private Collection<LigneDeCommande> ligneDeCommandeCollection;
    //@JoinColumn(name = "idcatalogue", referencedColumnName = "idcatalogue")
    //@ManyToOne(optional = false)
    //private Catalogue idcatalogue;
    @Column(name = "idcatalogue")
    private Integer idcatalogue;

    public Produit() {
    }

    public Produit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public Integer getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }


    @XmlTransient
    public Collection<LigneDeCommande> getLigneDeCommandeCollection() {
        return ligneDeCommandeCollection;
    }

    public void setLigneDeCommandeCollection(Collection<LigneDeCommande> ligneDeCommandeCollection) {
        this.ligneDeCommandeCollection = ligneDeCommandeCollection;
    }

    public Integer getIdcatalogue() {
        return idcatalogue;
    }

    public void setIdcatalogue(Integer idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduit != null ? idproduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idproduit == null && other.idproduit != null) || (this.idproduit != null && !this.idproduit.equals(other.idproduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produit[ idproduit=" + idproduit + " ]";
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    /*@XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }*/

}
