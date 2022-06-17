/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.List;
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

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "iva_no_recuperable", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "IvaNoRecuperable.findAll", query = "SELECT i FROM IvaNoRecuperable i"),
    @NamedQuery(name = "IvaNoRecuperable.findByIvaNoRecuperableId", query = "SELECT i FROM IvaNoRecuperable i WHERE i.ivaNoRecuperableId = :ivaNoRecuperableId"),
    @NamedQuery(name = "IvaNoRecuperable.findByIvaNoRecuperableNombre", query = "SELECT i FROM IvaNoRecuperable i WHERE i.ivaNoRecuperableNombre = :ivaNoRecuperableNombre")})
public class IvaNoRecuperable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iva_no_recuperable_id")
    private Integer ivaNoRecuperableId;
    @Basic(optional = false)
    @Column(name = "iva_no_recuperable_nombre")
    private String ivaNoRecuperableNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraCodigoIvaNr")
    private List<Compra> compraList;

    public IvaNoRecuperable() {
    }

    public IvaNoRecuperable(Integer ivaNoRecuperableId) {
        this.ivaNoRecuperableId = ivaNoRecuperableId;
    }

    public IvaNoRecuperable(Integer ivaNoRecuperableId, String ivaNoRecuperableNombre) {
        this.ivaNoRecuperableId = ivaNoRecuperableId;
        this.ivaNoRecuperableNombre = ivaNoRecuperableNombre;
    }

    public Integer getIvaNoRecuperableId() {
        return ivaNoRecuperableId;
    }

    public void setIvaNoRecuperableId(Integer ivaNoRecuperableId) {
        this.ivaNoRecuperableId = ivaNoRecuperableId;
    }

    public String getIvaNoRecuperableNombre() {
        return ivaNoRecuperableNombre;
    }

    public void setIvaNoRecuperableNombre(String ivaNoRecuperableNombre) {
        this.ivaNoRecuperableNombre = ivaNoRecuperableNombre;
    }

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ivaNoRecuperableId != null ? ivaNoRecuperableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IvaNoRecuperable)) {
            return false;
        }
        IvaNoRecuperable other = (IvaNoRecuperable) object;
        if ((this.ivaNoRecuperableId == null && other.ivaNoRecuperableId != null) || (this.ivaNoRecuperableId != null && !this.ivaNoRecuperableId.equals(other.ivaNoRecuperableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.IvaNoRecuperable[ ivaNoRecuperableId=" + ivaNoRecuperableId + " ]";
    }
    
}
