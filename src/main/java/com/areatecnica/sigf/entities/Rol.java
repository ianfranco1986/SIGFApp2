/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByRolId", query = "SELECT r FROM Rol r WHERE r.rolId = :rolId")
    , @NamedQuery(name = "Rol.findByRolNombre", query = "SELECT r FROM Rol r WHERE r.rolNombre = :rolNombre")
    , @NamedQuery(name = "Rol.findByRolDescripcion", query = "SELECT r FROM Rol r WHERE r.rolDescripcion = :rolDescripcion")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_id", nullable = false)
    private Integer rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rol_nombre", nullable = false, length = 255)
    private String rolNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rol_descripcion", nullable = false, length = 255)
    private String rolDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolMenuIdRol")
    private List<RolMenu> rolMenuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdRol")
    private List<Usuario> usuarioList;

    public Rol() {
    }

    public Rol(Integer rolId) {
        this.rolId = rolId;
    }

    public Rol(Integer rolId, String rolNombre, String rolDescripcion) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.rolDescripcion = rolDescripcion;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    @XmlTransient
    public List<RolMenu> getRolMenuList() {
        return rolMenuList;
    }

    public void setRolMenuList(List<RolMenu> rolMenuList) {
        this.rolMenuList = rolMenuList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        return (this.rolId != null || other.rolId == null) && (this.rolId == null || this.rolId.equals(other.rolId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Rol[ rolId=" + rolId + " ]";
    }
    
}
