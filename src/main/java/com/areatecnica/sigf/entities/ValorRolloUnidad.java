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

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "valor_rollo_unidad")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorRolloUnidad.findAll", query = "SELECT v FROM ValorRolloUnidad v")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadId", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadId = :valorRolloUnidadId")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadCompra", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadCompra = :valorRolloUnidadCompra")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadVenta", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadVenta = :valorRolloUnidadVenta")
    , @NamedQuery(name = "ValorRolloUnidad.findByValorRolloUnidadInterno", query = "SELECT v FROM ValorRolloUnidad v WHERE v.valorRolloUnidadInterno = :valorRolloUnidadInterno")})
public class ValorRolloUnidad extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_rollo_unidad_id", nullable = false)
    private Integer valorRolloUnidadId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_rollo_unidad_compra", nullable = false)
    private int valorRolloUnidadCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_rollo_unidad_venta", nullable = false)
    private int valorRolloUnidadVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_rollo_unidad_interno", nullable = false)
    private int valorRolloUnidadInterno;
    @JoinColumn(name = "valor_rollo_unidad_id_unidad", referencedColumnName = "unidad_negocio_id", nullable = false)
    @ManyToOne(optional = false)
    private UnidadNegocio valorRolloUnidadIdUnidad;

    public ValorRolloUnidad() {
    }

    public ValorRolloUnidad(Integer valorRolloUnidadId) {
        this.valorRolloUnidadId = valorRolloUnidadId;
    }

    public ValorRolloUnidad(Integer valorRolloUnidadId, int valorRolloUnidadCompra, int valorRolloUnidadVenta, int valorRolloUnidadInterno) {
        this.valorRolloUnidadId = valorRolloUnidadId;
        this.valorRolloUnidadCompra = valorRolloUnidadCompra;
        this.valorRolloUnidadVenta = valorRolloUnidadVenta;
        this.valorRolloUnidadInterno = valorRolloUnidadInterno;
    }

    public Integer getValorRolloUnidadId() {
        return valorRolloUnidadId;
    }

    public void setValorRolloUnidadId(Integer valorRolloUnidadId) {
        this.valorRolloUnidadId = valorRolloUnidadId;
    }

    public int getValorRolloUnidadCompra() {
        return valorRolloUnidadCompra;
    }

    public void setValorRolloUnidadCompra(int valorRolloUnidadCompra) {
        this.valorRolloUnidadCompra = valorRolloUnidadCompra;
    }

    public int getValorRolloUnidadVenta() {
        return valorRolloUnidadVenta;
    }

    public void setValorRolloUnidadVenta(int valorRolloUnidadVenta) {
        this.valorRolloUnidadVenta = valorRolloUnidadVenta;
    }

    public int getValorRolloUnidadInterno() {
        return valorRolloUnidadInterno;
    }

    public void setValorRolloUnidadInterno(int valorRolloUnidadInterno) {
        this.valorRolloUnidadInterno = valorRolloUnidadInterno;
    }

    public UnidadNegocio getValorRolloUnidadIdUnidad() {
        return valorRolloUnidadIdUnidad;
    }

    public void setValorRolloUnidadIdUnidad(UnidadNegocio valorRolloUnidadIdUnidad) {
        this.valorRolloUnidadIdUnidad = valorRolloUnidadIdUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorRolloUnidadId != null ? valorRolloUnidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorRolloUnidad)) {
            return false;
        }
        ValorRolloUnidad other = (ValorRolloUnidad) object;
        return (this.valorRolloUnidadId != null || other.valorRolloUnidadId == null) && (this.valorRolloUnidadId == null || this.valorRolloUnidadId.equals(other.valorRolloUnidadId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorRolloUnidad[ valorRolloUnidadId=" + valorRolloUnidadId + " ]";
    }
    
}
