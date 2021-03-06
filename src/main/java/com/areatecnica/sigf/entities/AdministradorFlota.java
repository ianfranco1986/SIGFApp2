/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "administrador_flota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministradorFlota.findAll", query = "SELECT a FROM AdministradorFlota a")
    , @NamedQuery(name = "AdministradorFlota.findByAdministradorFlotaId", query = "SELECT a FROM AdministradorFlota a WHERE a.administradorFlotaId = :administradorFlotaId")})
public class AdministradorFlota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administrador_flota_id", nullable = false)
    private Integer administradorFlotaId;
    @JoinColumn(name = "administrador_flota_id_admin", referencedColumnName = "administrador_id", nullable = false)
    @ManyToOne(optional = false)
    private Administrador administradorFlotaIdAdmin;
    @JoinColumn(name = "administrador_flota_id_flota", referencedColumnName = "flota_id", nullable = false)
    @ManyToOne(optional = false)
    private Flota administradorFlotaIdFlota;

    public AdministradorFlota() {
    }

    public AdministradorFlota(Integer administradorFlotaId) {
        this.administradorFlotaId = administradorFlotaId;
    }

    public Integer getAdministradorFlotaId() {
        return administradorFlotaId;
    }

    public void setAdministradorFlotaId(Integer administradorFlotaId) {
        this.administradorFlotaId = administradorFlotaId;
    }

    public Administrador getAdministradorFlotaIdAdmin() {
        return administradorFlotaIdAdmin;
    }

    public void setAdministradorFlotaIdAdmin(Administrador administradorFlotaIdAdmin) {
        this.administradorFlotaIdAdmin = administradorFlotaIdAdmin;
    }

    public Flota getAdministradorFlotaIdFlota() {
        return administradorFlotaIdFlota;
    }

    public void setAdministradorFlotaIdFlota(Flota administradorFlotaIdFlota) {
        this.administradorFlotaIdFlota = administradorFlotaIdFlota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administradorFlotaId != null ? administradorFlotaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministradorFlota)) {
            return false;
        }
        AdministradorFlota other = (AdministradorFlota) object;
        return (this.administradorFlotaId != null || other.administradorFlotaId == null) && (this.administradorFlotaId == null || this.administradorFlotaId.equals(other.administradorFlotaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AdministradorFlota[ administradorFlotaId=" + administradorFlotaId + " ]";
    }
    
}
