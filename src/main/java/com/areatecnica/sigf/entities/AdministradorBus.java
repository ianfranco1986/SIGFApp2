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
@Table(name = "administrador_bus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministradorBus.findAll", query = "SELECT a FROM AdministradorBus a")
    , @NamedQuery(name = "AdministradorBus.findByAdministradorBusId", query = "SELECT a FROM AdministradorBus a WHERE a.administradorBusId = :administradorBusId")})
public class AdministradorBus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administrador_bus_id", nullable = false)
    private Integer administradorBusId;
    @JoinColumn(name = "administrador_bus_id_admin", referencedColumnName = "administrador_id", nullable = false)
    @ManyToOne(optional = false)
    private Administrador administradorBusIdAdmin;
    @JoinColumn(name = "administrador_bus_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus administradorBusIdBus;

    public AdministradorBus() {
    }

    public AdministradorBus(Integer administradorBusId) {
        this.administradorBusId = administradorBusId;
    }

    public Integer getAdministradorBusId() {
        return administradorBusId;
    }

    public void setAdministradorBusId(Integer administradorBusId) {
        this.administradorBusId = administradorBusId;
    }

    public Administrador getAdministradorBusIdAdmin() {
        return administradorBusIdAdmin;
    }

    public void setAdministradorBusIdAdmin(Administrador administradorBusIdAdmin) {
        this.administradorBusIdAdmin = administradorBusIdAdmin;
    }

    public Bus getAdministradorBusIdBus() {
        return administradorBusIdBus;
    }

    public void setAdministradorBusIdBus(Bus administradorBusIdBus) {
        this.administradorBusIdBus = administradorBusIdBus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administradorBusId != null ? administradorBusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministradorBus)) {
            return false;
        }
        AdministradorBus other = (AdministradorBus) object;
        return (this.administradorBusId != null || other.administradorBusId == null) && (this.administradorBusId == null || this.administradorBusId.equals(other.administradorBusId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AdministradorBus[ administradorBusId=" + administradorBusId + " ]";
    }
    
}
