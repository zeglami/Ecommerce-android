/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saad YOUSFI
 */
@Entity
@Table(name = "catalogue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogue.findAll", query = "SELECT c FROM Catalogue c")
    , @NamedQuery(name = "Catalogue.findByIdcatalogue", query = "SELECT c FROM Catalogue c WHERE c.idcatalogue = :idcatalogue")
    , @NamedQuery(name = "Catalogue.findByNomcatalogue", query = "SELECT c FROM Catalogue c WHERE c.nomcatalogue = :nomcatalogue")
    , @NamedQuery(name = "Catalogue.findByDatecatalogue", query = "SELECT c FROM Catalogue c WHERE c.datecatalogue = :datecatalogue")})
public class Catalogue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcatalogue")
    private Integer idcatalogue;
    @Size(max = 250)
    @Column(name = "nomcatalogue")
    private String nomcatalogue;
    @Column(name = "datecatalogue")
    @Temporal(TemporalType.DATE)
    private Date datecatalogue;
    //OneToMany(cascade = CascadeType.ALL, mappedBy = "idcatalogue")
    //private Collection<Produit> produitCollection;

    public Catalogue() {
    }

    public Catalogue(Integer idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

    public Integer getIdcatalogue() {
        return idcatalogue;
    }

    public void setIdcatalogue(Integer idcatalogue) {
        this.idcatalogue = idcatalogue;
    }

    public String getNomcatalogue() {
        return nomcatalogue;
    }

    public void setNomcatalogue(String nomcatalogue) {
        this.nomcatalogue = nomcatalogue;
    }

    public Date getDatecatalogue() {
        return datecatalogue;
    }

    public void setDatecatalogue(Date datecatalogue) {
        this.datecatalogue = datecatalogue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcatalogue != null ? idcatalogue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogue)) {
            return false;
        }
        Catalogue other = (Catalogue) object;
        if ((this.idcatalogue == null && other.idcatalogue != null) || (this.idcatalogue != null && !this.idcatalogue.equals(other.idcatalogue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Catalogue[ idcatalogue=" + idcatalogue + " ]";
    }

    /*@XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }*/
    
}
