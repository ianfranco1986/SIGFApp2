/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "valor_uf")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorUf.findAll", query = "SELECT v FROM ValorUf v")
    , @NamedQuery(name = "ValorUf.findByValorUfId", query = "SELECT v FROM ValorUf v WHERE v.valorUfId = :valorUfId")
    , @NamedQuery(name = "ValorUf.findByValorUfFecha", query = "SELECT v FROM ValorUf v WHERE v.valorUfFecha = :valorUfFecha")
    , @NamedQuery(name = "ValorUf.findByValorUfMonto", query = "SELECT v FROM ValorUf v WHERE v.valorUfMonto = :valorUfMonto")})
public class ValorUf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_uf_id", nullable = false)
    private Integer valorUfId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_uf_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date valorUfFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_uf_monto", nullable = false)
    private float valorUfMonto;

    public ValorUf() {
    }

    public ValorUf(Integer valorUfId) {
        this.valorUfId = valorUfId;
    }

    public ValorUf(Integer valorUfId, Date valorUfFecha, float valorUfMonto) {
        this.valorUfId = valorUfId;
        this.valorUfFecha = valorUfFecha;
        this.valorUfMonto = valorUfMonto;
    }

    public Integer getValorUfId() {
        return valorUfId;
    }

    public void setValorUfId(Integer valorUfId) {
        this.valorUfId = valorUfId;
    }

    public Date getValorUfFecha() {
        return valorUfFecha;
    }

    public void setValorUfFecha(Date valorUfFecha) {
        this.valorUfFecha = valorUfFecha;
    }

    public float getValorUfMonto() {
        return valorUfMonto;
    }

    public void setValorUfMonto(float valorUfMonto) {
        this.valorUfMonto = valorUfMonto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorUfId != null ? valorUfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorUf)) {
            return false;
        }
        ValorUf other = (ValorUf) object;
        return (this.valorUfId != null || other.valorUfId == null) && (this.valorUfId == null || this.valorUfId.equals(other.valorUfId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorUf[ valorUfId=" + valorUfId + " ]";
    }
    
}
