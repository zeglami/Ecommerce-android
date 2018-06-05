/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saad YOUSFI
 */
@Entity
@Table(name = "livreur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livreur.findAll", query = "SELECT l FROM Livreur l")
    , @NamedQuery(name = "Livreur.findByIdlivreur", query = "SELECT l FROM Livreur l WHERE l.idlivreur = :idlivreur")
    , @NamedQuery(name = "Livreur.findByNomlivreur", query = "SELECT l FROM Livreur l WHERE l.nomlivreur = :nomlivreur")
    , @NamedQuery(name = "Livreur.findByPrenomlivreur", query = "SELECT l FROM Livreur l WHERE l.prenomlivreur = :prenomlivreur")
    , @NamedQuery(name = "Livreur.findByTellivreur", query = "SELECT l FROM Livreur l WHERE l.tellivreur = :tellivreur")
    , @NamedQuery(name = "Livreur.findByLoginlivreur", query = "SELECT l FROM Livreur l WHERE l.loginlivreur = :loginlivreur")
    , @NamedQuery(name = "Livreur.findByMdplivreur", query = "SELECT l FROM Livreur l WHERE l.mdplivreur = :mdplivreur")})
public class Livreur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlivreur")
    private Integer idlivreur;
    @Size(max = 250)
    @Column(name = "nomlivreur")
    private String nomlivreur;
    @Size(max = 250)
    @Column(name = "prenomlivreur")
    private String prenomlivreur;
    @Size(max = 250)
    @Column(name = "tellivreur")
    private String tellivreur;
    @Size(max = 250)
    @Column(name = "loginlivreur")
    private String loginlivreur;
    @Size(max = 250)
    @Column(name = "mdplivreur")
    private String mdplivreur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlivreur")
    private Collection<Commande> commandeCollection;

    public Livreur() {
    }

    public Livreur(Integer idlivreur) {
        this.idlivreur = idlivreur;
    }

    public Integer getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(Integer idlivreur) {
        this.idlivreur = idlivreur;
    }

    public String getNomlivreur() {
        return nomlivreur;
    }

    public void setNomlivreur(String nomlivreur) {
        this.nomlivreur = nomlivreur;
    }

    public String getPrenomlivreur() {
        return prenomlivreur;
    }

    public void setPrenomlivreur(String prenomlivreur) {
        this.prenomlivreur = prenomlivreur;
    }

    public String getTellivreur() {
        return tellivreur;
    }

    public void setTellivreur(String tellivreur) {
        this.tellivreur = tellivreur;
    }

    public String getLoginlivreur() {
        return loginlivreur;
    }

    public void setLoginlivreur(String loginlivreur) {
        this.loginlivreur = loginlivreur;
    }

    public String getMdplivreur() {
        return mdplivreur;
    }

    public void setMdplivreur(String mdplivreur) {
        this.mdplivreur = mdplivreur;
    }

    @XmlTransient
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlivreur != null ? idlivreur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livreur)) {
            return false;
        }
        Livreur other = (Livreur) object;
        if ((this.idlivreur == null && other.idlivreur != null) || (this.idlivreur != null && !this.idlivreur.equals(other.idlivreur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Livreur[ idlivreur=" + idlivreur + " ]";
    }
    
}
