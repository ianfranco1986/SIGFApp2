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
@Table(name = "auxiliar_facturas", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "AuxiliarFacturas.findAll", query = "SELECT a FROM AuxiliarFacturas a"),
    @NamedQuery(name = "AuxiliarFacturas.findByAuxiliarFacturasId", query = "SELECT a FROM AuxiliarFacturas a WHERE a.auxiliarFacturasId = :auxiliarFacturasId")})
public class AuxiliarFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auxiliar_facturas_id")
    private Integer auxiliarFacturasId;
    @JoinColumn(name = "auxiliar_facturas_factura_id", referencedColumnName = "factura_id")
    @ManyToOne(optional = false)
    private Factura auxiliarFacturasFacturaId;
    @JoinColumn(name = "auxiliar_facturas_v_mov_id", referencedColumnName = "voucher_movimiento_id")
    @ManyToOne(optional = false)
    private VoucherMovimiento auxiliarFacturasVMovId;

    public AuxiliarFacturas() {
    }

    public AuxiliarFacturas(Integer auxiliarFacturasId) {
        this.auxiliarFacturasId = auxiliarFacturasId;
    }

    public Integer getAuxiliarFacturasId() {
        return auxiliarFacturasId;
    }

    public void setAuxiliarFacturasId(Integer auxiliarFacturasId) {
        this.auxiliarFacturasId = auxiliarFacturasId;
    }

    public Factura getAuxiliarFacturasFacturaId() {
        return auxiliarFacturasFacturaId;
    }

    public void setAuxiliarFacturasFacturaId(Factura auxiliarFacturasFacturaId) {
        this.auxiliarFacturasFacturaId = auxiliarFacturasFacturaId;
    }

    public VoucherMovimiento getAuxiliarFacturasVMovId() {
        return auxiliarFacturasVMovId;
    }

    public void setAuxiliarFacturasVMovId(VoucherMovimiento auxiliarFacturasVMovId) {
        this.auxiliarFacturasVMovId = auxiliarFacturasVMovId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auxiliarFacturasId != null ? auxiliarFacturasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuxiliarFacturas)) {
            return false;
        }
        AuxiliarFacturas other = (AuxiliarFacturas) object;
        if ((this.auxiliarFacturasId == null && other.auxiliarFacturasId != null) || (this.auxiliarFacturasId != null && !this.auxiliarFacturasId.equals(other.auxiliarFacturasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AuxiliarFacturas[ auxiliarFacturasId=" + auxiliarFacturasId + " ]";
    }
    
}
