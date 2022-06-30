/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

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
@Table(name = "menu")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByMenuId", query = "SELECT m FROM Menu m WHERE m.menuId = :menuId")
    , @NamedQuery(name = "Menu.findByMenuNombre", query = "SELECT m FROM Menu m WHERE m.menuNombre = :menuNombre")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_id", nullable = false)
    private Integer menuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "menu_nombre", nullable = false, length = 45)
    private String menuNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolMenuIdMenu", fetch = FetchType.LAZY)
    private List<RolMenu> rolMenuList;
    @OrderBy("privilegioNumeroOrden ASC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilegioIdMenu", fetch = FetchType.LAZY)
    private List<Privilegio> privilegioList;

    public Menu() {
    }

    public Menu(Integer menuId) {
        this.menuId = menuId;
    }

    public Menu(Integer menuId, String menuNombre) {
        this.menuId = menuId;
        this.menuNombre = menuNombre;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuNombre() {
        return menuNombre;
    }

    public void setMenuNombre(String menuNombre) {
        this.menuNombre = menuNombre;
    }

    @XmlTransient
    public List<RolMenu> getRolMenuList() {
        return rolMenuList;
    }

    public void setRolMenuList(List<RolMenu> rolMenuList) {
        this.rolMenuList = rolMenuList;
    }

    @XmlTransient
    public List<Privilegio> getPrivilegioList() {
        return privilegioList;
    }

    public void setPrivilegioList(List<Privilegio> privilegioList) {
        this.privilegioList = privilegioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        return (this.menuId != null || other.menuId == null) && (this.menuId == null || this.menuId.equals(other.menuId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Menu[ menuId=" + menuId + " ]";
    }
    
}
