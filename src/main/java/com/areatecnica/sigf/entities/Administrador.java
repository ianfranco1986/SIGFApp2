/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByAdministradorId", query = "SELECT a FROM Administrador a WHERE a.administradorId = :administradorId")
    , @NamedQuery(name = "Administrador.findByAdministradorActivo", query = "SELECT a FROM Administrador a WHERE a.administradorActivo = :administradorActivo")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administrador_id", nullable = false)
    private Integer administradorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "administrador_activo", nullable = false)
    private boolean administradorActivo;
    @JoinColumn(name = "administrador_id_usuario", referencedColumnName = "usuario_id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario administradorIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administradorFlotaIdAdmin")
    private List<AdministradorFlota> administradorFlotaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administradorBusIdAdmin")
    private List<AdministradorBus> administradorBusList;

    public Administrador() {
    }

    public Administrador(Integer administradorId) {
        this.administradorId = administradorId;
    }

    public Administrador(Integer administradorId, boolean administradorActivo) {
        this.administradorId = administradorId;
        this.administradorActivo = administradorActivo;
    }

    public Integer getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Integer administradorId) {
        this.administradorId = administradorId;
    }

    public boolean getAdministradorActivo() {
        return administradorActivo;
    }

    public void setAdministradorActivo(boolean administradorActivo) {
        this.administradorActivo = administradorActivo;
    }

    public Usuario getAdministradorIdUsuario() {
        return administradorIdUsuario;
    }

    public void setAdministradorIdUsuario(Usuario administradorIdUsuario) {
        this.administradorIdUsuario = administradorIdUsuario;
    }

    @XmlTransient
    public List<AdministradorFlota> getAdministradorFlotaList() {
        return administradorFlotaList;
    }

    public void setAdministradorFlotaList(List<AdministradorFlota> administradorFlotaList) {
        this.administradorFlotaList = administradorFlotaList;
    }

    @XmlTransient
    public List<AdministradorBus> getAdministradorBusList() {
        return administradorBusList;
    }

    public void setAdministradorBusList(List<AdministradorBus> administradorBusList) {
        this.administradorBusList = administradorBusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administradorId != null ? administradorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        return (this.administradorId != null || other.administradorId == null) && (this.administradorId == null || this.administradorId.equals(other.administradorId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Administrador[ administradorId=" + administradorId + " ]";
    }
    
}
