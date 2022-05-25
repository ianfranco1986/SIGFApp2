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
 * @author ianfr
 */
@Entity
@Table(name = "rol_menu")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RolMenu.findAll", query = "SELECT r FROM RolMenu r")
    , @NamedQuery(name = "RolMenu.findByRolMenuId", query = "SELECT r FROM RolMenu r WHERE r.rolMenuId = :rolMenuId")
    , @NamedQuery(name = "RolMenu.findByRol", query = "SELECT r FROM RolMenu r WHERE r.rolMenuIdRol = :rolMenuIdRol ORDER BY r.rolMenuIdMenu.menuNombre ASC")})
public class RolMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_menu_id", nullable = false)
    private Integer rolMenuId;
    @JoinColumn(name = "rol_menu_id_menu", referencedColumnName = "menu_id", nullable = false)
    @ManyToOne(optional = false)
    private Menu rolMenuIdMenu;
    @JoinColumn(name = "rol_menu_id_rol", referencedColumnName = "rol_id", nullable = false)
    @ManyToOne(optional = false)
    private Rol rolMenuIdRol;

    public RolMenu() {
    }

    public RolMenu(Integer rolMenuId) {
        this.rolMenuId = rolMenuId;
    }

    public Integer getRolMenuId() {
        return rolMenuId;
    }

    public void setRolMenuId(Integer rolMenuId) {
        this.rolMenuId = rolMenuId;
    }

    public Menu getRolMenuIdMenu() {
        return rolMenuIdMenu;
    }

    public void setRolMenuIdMenu(Menu rolMenuIdMenu) {
        this.rolMenuIdMenu = rolMenuIdMenu;
    }

    public Rol getRolMenuIdRol() {
        return rolMenuIdRol;
    }

    public void setRolMenuIdRol(Rol rolMenuIdRol) {
        this.rolMenuIdRol = rolMenuIdRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolMenuId != null ? rolMenuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolMenu)) {
            return false;
        }
        RolMenu other = (RolMenu) object;
        return (this.rolMenuId != null || other.rolMenuId == null) && (this.rolMenuId == null || this.rolMenuId.equals(other.rolMenuId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RolMenu[ rolMenuId=" + rolMenuId + " ]";
    }

}
