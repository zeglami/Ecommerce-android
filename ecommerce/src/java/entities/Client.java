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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByIdclient", query = "SELECT c FROM Client c WHERE c.idclient = :idclient")
    , @NamedQuery(name = "Client.findByNomclient", query = "SELECT c FROM Client c WHERE c.nomclient = :nomclient")
    , @NamedQuery(name = "Client.findByPrenomclient", query = "SELECT c FROM Client c WHERE c.prenomclient = :prenomclient")
    , @NamedQuery(name = "Client.findByTelclient", query = "SELECT c FROM Client c WHERE c.telclient = :telclient")
    , @NamedQuery(name = "Client.findByAdressecli", query = "SELECT c FROM Client c WHERE c.adressecli = :adressecli")
    , @NamedQuery(name = "Client.findByLoginclient", query = "SELECT c FROM Client c WHERE c.loginclient = :loginclient")
    , @NamedQuery(name = "Client.findByMdpclient", query = "SELECT c FROM Client c WHERE c.mdpclient = :mdpclient")
    , @NamedQuery(name = "Client.findByDatenaissance", query = "SELECT c FROM Client c WHERE c.datenaissance = :datenaissance")
    , @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclient")
    private Integer idclient;
    @Size(max = 250)
    @Column(name = "nomclient")
    private String nomclient;
    @Size(max = 250)
    @Column(name = "prenomclient")
    private String prenomclient;
    @Size(max = 250)
    @Column(name = "telclient")
    private String telclient;
    @Size(max = 250)
    @Column(name = "adressecli")
    private String adressecli;
    @Size(max = 250)
    @Column(name = "loginclient")
    private String loginclient;
    @Size(max = 250)
    @Column(name = "mdpclient")
    private String mdpclient;
    @Column(name = "datenaissance")
    @Temporal(TemporalType.DATE)
    private Date datenaissance;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Evaluation> evaluationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclient")
    private Collection<Commande> commandeCollection;

    public Client() {
    }

    public Client(Integer idclient) {
        this.idclient = idclient;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }

    public String getTelclient() {
        return telclient;
    }

    public void setTelclient(String telclient) {
        this.telclient = telclient;
    }

    public String getAdressecli() {
        return adressecli;
    }

    public void setAdressecli(String adressecli) {
        this.adressecli = adressecli;
    }

    public String getLoginclient() {
        return loginclient;
    }

    public void setLoginclient(String loginclient) {
        this.loginclient = loginclient;
    }

    public String getMdpclient() {
        return mdpclient;
    }

    public void setMdpclient(String mdpclient) {
        this.mdpclient = mdpclient;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Evaluation> getEvaluationCollection() {
        return evaluationCollection;
    }

    public void setEvaluationCollection(Collection<Evaluation> evaluationCollection) {
        this.evaluationCollection = evaluationCollection;
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
        hash += (idclient != null ? idclient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idclient == null && other.idclient != null) || (this.idclient != null && !this.idclient.equals(other.idclient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ idclient=" + idclient + " ]";
    }
    
}
