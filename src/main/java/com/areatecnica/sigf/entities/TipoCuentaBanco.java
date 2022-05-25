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
@Table(name = "tipo_cuenta_banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCuentaBanco.findAll", query = "SELECT t FROM TipoCuentaBanco t")
    , @NamedQuery(name = "TipoCuentaBanco.findByTipoCuentaBancoId", query = "SELECT t FROM TipoCuentaBanco t WHERE t.tipoCuentaBancoId = :tipoCuentaBancoId")
    , @NamedQuery(name = "TipoCuentaBanco.findByTipoCuentaBancoNombre", query = "SELECT t FROM TipoCuentaBanco t WHERE t.tipoCuentaBancoNombre = :tipoCuentaBancoNombre")})
public class TipoCuentaBanco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_cuenta_banco_id", nullable = false)
    private Integer tipoCuentaBancoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_cuenta_banco_nombre", nullable = false, length = 45)
    private String tipoCuentaBancoNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancariaIdTipoCuenta")
    private List<CuentaBancaria> cuentaBancariaList;

    public TipoCuentaBanco() {
    }

    public TipoCuentaBanco(Integer tipoCuentaBancoId) {
        this.tipoCuentaBancoId = tipoCuentaBancoId;
    }

    public TipoCuentaBanco(Integer tipoCuentaBancoId, String tipoCuentaBancoNombre) {
        this.tipoCuentaBancoId = tipoCuentaBancoId;
        this.tipoCuentaBancoNombre = tipoCuentaBancoNombre;
    }

    public Integer getTipoCuentaBancoId() {
        return tipoCuentaBancoId;
    }

    public void setTipoCuentaBancoId(Integer tipoCuentaBancoId) {
        this.tipoCuentaBancoId = tipoCuentaBancoId;
    }

    public String getTipoCuentaBancoNombre() {
        return tipoCuentaBancoNombre;
    }

    public void setTipoCuentaBancoNombre(String tipoCuentaBancoNombre) {
        this.tipoCuentaBancoNombre = tipoCuentaBancoNombre;
    }

    @XmlTransient
    public List<CuentaBancaria> getCuentaBancariaList() {
        return cuentaBancariaList;
    }

    public void setCuentaBancariaList(List<CuentaBancaria> cuentaBancariaList) {
        this.cuentaBancariaList = cuentaBancariaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCuentaBancoId != null ? tipoCuentaBancoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCuentaBanco)) {
            return false;
        }
        TipoCuentaBanco other = (TipoCuentaBanco) object;
        return (this.tipoCuentaBancoId != null || other.tipoCuentaBancoId == null) && (this.tipoCuentaBancoId == null || this.tipoCuentaBancoId.equals(other.tipoCuentaBancoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCuentaBanco[ tipoCuentaBancoId=" + tipoCuentaBancoId + " ]";
    }
    
}
