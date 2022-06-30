/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "auxiliar_compras", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "AuxiliarCompras.findAll", query = "SELECT a FROM AuxiliarCompras a"),
    @NamedQuery(name = "AuxiliarCompras.findByAuxiliarComprasId", query = "SELECT a FROM AuxiliarCompras a WHERE a.auxiliarComprasId = :auxiliarComprasId")})
public class AuxiliarCompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auxiliar_compras_id")
    private Integer auxiliarComprasId;
    @JoinColumn(name = "auxiliar_compras_compra_id", referencedColumnName = "compra_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Compra auxiliarComprasCompraId;
    @JoinColumn(name = "auxiliar_compras_v_mov_id", referencedColumnName = "voucher_movimiento_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VoucherMovimiento auxiliarComprasVMovId;

    public AuxiliarCompras() {
    }

    public AuxiliarCompras(Integer auxiliarComprasId) {
        this.auxiliarComprasId = auxiliarComprasId;
    }

    public Integer getAuxiliarComprasId() {
        return auxiliarComprasId;
    }

    public void setAuxiliarComprasId(Integer auxiliarComprasId) {
        this.auxiliarComprasId = auxiliarComprasId;
    }

    public Compra getAuxiliarComprasCompraId() {
        return auxiliarComprasCompraId;
    }

    public void setAuxiliarComprasCompraId(Compra auxiliarComprasCompraId) {
        this.auxiliarComprasCompraId = auxiliarComprasCompraId;
    }

    public VoucherMovimiento getAuxiliarComprasVMovId() {
        return auxiliarComprasVMovId;
    }

    public void setAuxiliarComprasVMovId(VoucherMovimiento auxiliarComprasVMovId) {
        this.auxiliarComprasVMovId = auxiliarComprasVMovId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auxiliarComprasId != null ? auxiliarComprasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuxiliarCompras)) {
            return false;
        }
        AuxiliarCompras other = (AuxiliarCompras) object;
        if ((this.auxiliarComprasId == null && other.auxiliarComprasId != null) || (this.auxiliarComprasId != null && !this.auxiliarComprasId.equals(other.auxiliarComprasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AuxiliarCompras[ auxiliarComprasId=" + auxiliarComprasId + " ]";
    }
    
}
