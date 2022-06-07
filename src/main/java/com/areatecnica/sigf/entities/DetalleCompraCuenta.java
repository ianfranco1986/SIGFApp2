/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "detalle_compra_cuenta")
@NamedQueries({
    @NamedQuery(name = "DetalleCompraCuenta.findAll", query = "SELECT d FROM DetalleCompraCuenta d"),
    @NamedQuery(name = "DetalleCompraCuenta.findByDetalleCompraCuentaId", query = "SELECT d FROM DetalleCompraCuenta d WHERE d.detalleCompraCuentaId = :detalleCompraCuentaId"),
    @NamedQuery(name = "DetalleCompraCuenta.findByDetalleCompraCuentaMonto", query = "SELECT d FROM DetalleCompraCuenta d WHERE d.detalleCompraCuentaMonto = :detalleCompraCuentaMonto"),
    @NamedQuery(name = "DetalleCompraCuenta.findByDetalleCompraCuentaDescripcion", query = "SELECT d FROM DetalleCompraCuenta d WHERE d.detalleCompraCuentaDescripcion = :detalleCompraCuentaDescripcion")})
public class DetalleCompraCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_compra_cuenta_id")
    private Integer detalleCompraCuentaId;
    @Basic(optional = false)
    @Column(name = "detalle_compra_cuenta_monto")
    private int detalleCompraCuentaMonto;
    @Basic(optional = false)
    @Column(name = "detalle_compra_cuenta_descripcion")
    private String detalleCompraCuentaDescripcion;
    @JoinColumn(name = "detalle_compra_cuenta_compra_id", referencedColumnName = "compra_id")
    @ManyToOne(optional = false)
    private Compra detalleCompraCuentaCompraId;
    @JoinColumn(name = "detalle_compra_cuenta_cuenta_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false)
    private CuentaMayor detalleCompraCuentaCuentaId;

    public DetalleCompraCuenta() {
    }

    public DetalleCompraCuenta(Integer detalleCompraCuentaId) {
        this.detalleCompraCuentaId = detalleCompraCuentaId;
    }

    public DetalleCompraCuenta(Integer detalleCompraCuentaId, int detalleCompraCuentaMonto, String detalleCompraCuentaDescripcion) {
        this.detalleCompraCuentaId = detalleCompraCuentaId;
        this.detalleCompraCuentaMonto = detalleCompraCuentaMonto;
        this.detalleCompraCuentaDescripcion = detalleCompraCuentaDescripcion;
    }

    public Integer getDetalleCompraCuentaId() {
        return detalleCompraCuentaId;
    }

    public void setDetalleCompraCuentaId(Integer detalleCompraCuentaId) {
        this.detalleCompraCuentaId = detalleCompraCuentaId;
    }

    public int getDetalleCompraCuentaMonto() {
        return detalleCompraCuentaMonto;
    }

    public void setDetalleCompraCuentaMonto(int detalleCompraCuentaMonto) {
        this.detalleCompraCuentaMonto = detalleCompraCuentaMonto;
    }

    public String getDetalleCompraCuentaDescripcion() {
        return detalleCompraCuentaDescripcion;
    }

    public void setDetalleCompraCuentaDescripcion(String detalleCompraCuentaDescripcion) {
        this.detalleCompraCuentaDescripcion = detalleCompraCuentaDescripcion;
    }

    public Compra getDetalleCompraCuentaCompraId() {
        return detalleCompraCuentaCompraId;
    }

    public void setDetalleCompraCuentaCompraId(Compra detalleCompraCuentaCompraId) {
        this.detalleCompraCuentaCompraId = detalleCompraCuentaCompraId;
    }

    public CuentaMayor getDetalleCompraCuentaCuentaId() {
        return detalleCompraCuentaCuentaId;
    }

    public void setDetalleCompraCuentaCuentaId(CuentaMayor detalleCompraCuentaCuentaId) {
        this.detalleCompraCuentaCuentaId = detalleCompraCuentaCuentaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCompraCuentaId != null ? detalleCompraCuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCompraCuenta)) {
            return false;
        }
        DetalleCompraCuenta other = (DetalleCompraCuenta) object;
        if ((this.detalleCompraCuentaId == null && other.detalleCompraCuentaId != null) || (this.detalleCompraCuentaId != null && !this.detalleCompraCuentaId.equals(other.detalleCompraCuentaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleCompraCuenta[ detalleCompraCuentaId=" + detalleCompraCuentaId + " ]";
    }
    
}
