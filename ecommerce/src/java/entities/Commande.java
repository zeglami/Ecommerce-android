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
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findByIdcommande", query = "SELECT c FROM Commande c WHERE c.idcommande = :idcommande")
    , @NamedQuery(name = "Commande.findByAdresselivraison", query = "SELECT c FROM Commande c WHERE c.adresselivraison = :adresselivraison")
    , @NamedQuery(name = "Commande.findByNomlivraison", query = "SELECT c FROM Commande c WHERE c.nomlivraison = :nomlivraison")
    , @NamedQuery(name = "Commande.findByPrenomlivraison", query = "SELECT c FROM Commande c WHERE c.prenomlivraison = :prenomlivraison")
    , @NamedQuery(name = "Commande.findByTellivraison", query = "SELECT c FROM Commande c WHERE c.tellivraison = :tellivraison")
    , @NamedQuery(name = "Commande.findByDatecommande", query = "SELECT c FROM Commande c WHERE c.datecommande = :datecommande")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcommande")
    private Integer idcommande;
    @Size(max = 250)
    @Column(name = "adresselivraison")
    private String adresselivraison;
    @Size(max = 250)
    @Column(name = "nomlivraison")
    private String nomlivraison;
    @Size(max = 250)
    @Column(name = "prenomlivraison")
    private String prenomlivraison;
    @Size(max = 250)
    @Column(name = "tellivraison")
    private String tellivraison;
    @Size(max = 250)
    @Column(name = "datecommande")
    private String datecommande;
    @Size(max = 250)
    @Column(name = "etat")
    private String etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private Collection<LigneDeCommande> ligneDeCommandeCollection;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Client idclient;
    @JoinColumn(name = "idlivreur", referencedColumnName = "idlivreur")
    @ManyToOne(optional = false)
    private Livreur idlivreur;


    public Commande() {
    }

    public Commande(Integer idcommande) {
        this.idcommande = idcommande;
    }

    public Integer getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(Integer idcommande) {
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

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public Collection<LigneDeCommande> getLigneDeCommandeCollection() {
        return ligneDeCommandeCollection;
    }

    public void setLigneDeCommandeCollection(Collection<LigneDeCommande> ligneDeCommandeCollection) {
        this.ligneDeCommandeCollection = ligneDeCommandeCollection;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    public Livreur getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(Livreur idlivreur) {
        this.idlivreur = idlivreur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcommande != null ? idcommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idcommande == null && other.idcommande != null) || (this.idcommande != null && !this.idcommande.equals(other.idcommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commande[ idcommande=" + idcommande + " ]";
    }
    
}
