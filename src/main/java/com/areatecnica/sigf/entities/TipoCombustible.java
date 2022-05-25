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
@Table(name = "tipo_combustible")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCombustible.findAll", query = "SELECT t FROM TipoCombustible t")
    , @NamedQuery(name = "TipoCombustible.findByTipoCombustibleId", query = "SELECT t FROM TipoCombustible t WHERE t.tipoCombustibleId = :tipoCombustibleId")
    , @NamedQuery(name = "TipoCombustible.findByTipoCombustibleNombre", query = "SELECT t FROM TipoCombustible t WHERE t.tipoCombustibleNombre = :tipoCombustibleNombre")})
public class TipoCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_combustible_id", nullable = false)
    private Integer tipoCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_combustible_nombre", nullable = false, length = 45)
    private String tipoCombustibleNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precioCombustibleIdTipoCombustible")
    private List<PrecioCombustible> precioCombustibleList;
    
    public TipoCombustible() {
    }

    public TipoCombustible(Integer tipoCombustibleId) {
        this.tipoCombustibleId = tipoCombustibleId;
    }

    public TipoCombustible(Integer tipoCombustibleId, String tipoCombustibleNombre) {
        this.tipoCombustibleId = tipoCombustibleId;
        this.tipoCombustibleNombre = tipoCombustibleNombre;
    }

    public Integer getTipoCombustibleId() {
        return tipoCombustibleId;
    }

    public void setTipoCombustibleId(Integer tipoCombustibleId) {
        this.tipoCombustibleId = tipoCombustibleId;
    }

    public String getTipoCombustibleNombre() {
        return tipoCombustibleNombre;
    }

    public void setTipoCombustibleNombre(String tipoCombustibleNombre) {
        this.tipoCombustibleNombre = tipoCombustibleNombre;
    }

    @XmlTransient
    public List<PrecioCombustible> getPrecioCombustibleList() {
        return precioCombustibleList;
    }

    public void setPrecioCombustibleList(List<PrecioCombustible> precioCombustibleList) {
        this.precioCombustibleList = precioCombustibleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCombustibleId != null ? tipoCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCombustible)) {
            return false;
        }
        TipoCombustible other = (TipoCombustible) object;
        return (this.tipoCombustibleId != null || other.tipoCombustibleId == null) && (this.tipoCombustibleId == null || this.tipoCombustibleId.equals(other.tipoCombustibleId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCombustible[ tipoCombustibleId=" + tipoCombustibleId + " ]";
    }
    
}
