/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saad YOUSFI
 */
@Entity
@Table(name = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a")
    , @NamedQuery(name = "Admin.findByIdadmin", query = "SELECT a FROM Admin a WHERE a.idadmin = :idadmin")
    , @NamedQuery(name = "Admin.findByLoginadmin", query = "SELECT a FROM Admin a WHERE a.loginadmin = :loginadmin")
    , @NamedQuery(name = "Admin.findByMdpadmin", query = "SELECT a FROM Admin a WHERE a.mdpadmin = :mdpadmin")
    , @NamedQuery(name = "Admin.findByEmailadmin", query = "SELECT a FROM Admin a WHERE a.emailadmin = :emailadmin")
    , @NamedQuery(name = "Admin.findByTeladmin", query = "SELECT a FROM Admin a WHERE a.teladmin = :teladmin")})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadmin")
    private Integer idadmin;
    @Size(max = 250)
    @Column(name = "loginadmin")
    private String loginadmin;
    @Size(max = 250)
    @Column(name = "mdpadmin")
    private String mdpadmin;
    @Size(max = 250)
    @Column(name = "emailadmin")
    private String emailadmin;
    @Size(max = 20)
    @Column(name = "teladmin")
    private String teladmin;

    public Admin() {
    }

    public Admin(Integer idadmin) {
        this.idadmin = idadmin;
    }

    public Integer getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(Integer idadmin) {
        this.idadmin = idadmin;
    }

    public String getLoginadmin() {
        return loginadmin;
    }

    public void setLoginadmin(String loginadmin) {
        this.loginadmin = loginadmin;
    }

    public String getMdpadmin() {
        return mdpadmin;
    }

    public void setMdpadmin(String mdpadmin) {
        this.mdpadmin = mdpadmin;
    }

    public String getEmailadmin() {
        return emailadmin;
    }

    public void setEmailadmin(String emailadmin) {
        this.emailadmin = emailadmin;
    }

    public String getTeladmin() {
        return teladmin;
    }

    public void setTeladmin(String teladmin) {
        this.teladmin = teladmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadmin != null ? idadmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.idadmin == null && other.idadmin != null) || (this.idadmin != null && !this.idadmin.equals(other.idadmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Admin[ idadmin=" + idadmin + " ]";
    }
    
}
