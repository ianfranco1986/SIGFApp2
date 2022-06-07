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
@Table(name = "tipo_dcto_voucher", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoDctoVoucher.findAll", query = "SELECT t FROM TipoDctoVoucher t"),
    @NamedQuery(name = "TipoDctoVoucher.findByTipoDctoVoucherId", query = "SELECT t FROM TipoDctoVoucher t WHERE t.tipoDctoVoucherId = :tipoDctoVoucherId"),
    @NamedQuery(name = "TipoDctoVoucher.findByTipoDctoVoucherNombre", query = "SELECT t FROM TipoDctoVoucher t WHERE t.tipoDctoVoucherNombre = :tipoDctoVoucherNombre")})
public class TipoDctoVoucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_dcto_voucher_id")
    private Integer tipoDctoVoucherId;
    @Basic(optional = false)
    @Column(name = "tipo_dcto_voucher_nombre")
    private String tipoDctoVoucherNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleVoucherDocVoucherId")
    private List<DetalleVoucher> detalleVoucherList;

    public TipoDctoVoucher() {
    }

    public TipoDctoVoucher(Integer tipoDctoVoucherId) {
        this.tipoDctoVoucherId = tipoDctoVoucherId;
    }

    public TipoDctoVoucher(Integer tipoDctoVoucherId, String tipoDctoVoucherNombre) {
        this.tipoDctoVoucherId = tipoDctoVoucherId;
        this.tipoDctoVoucherNombre = tipoDctoVoucherNombre;
    }

    public Integer getTipoDctoVoucherId() {
        return tipoDctoVoucherId;
    }

    public void setTipoDctoVoucherId(Integer tipoDctoVoucherId) {
        this.tipoDctoVoucherId = tipoDctoVoucherId;
    }

    public String getTipoDctoVoucherNombre() {
        return tipoDctoVoucherNombre;
    }

    public void setTipoDctoVoucherNombre(String tipoDctoVoucherNombre) {
        this.tipoDctoVoucherNombre = tipoDctoVoucherNombre;
    }

    public List<DetalleVoucher> getDetalleVoucherList() {
        return detalleVoucherList;
    }

    public void setDetalleVoucherList(List<DetalleVoucher> detalleVoucherList) {
        this.detalleVoucherList = detalleVoucherList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDctoVoucherId != null ? tipoDctoVoucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDctoVoucher)) {
            return false;
        }
        TipoDctoVoucher other = (TipoDctoVoucher) object;
        if ((this.tipoDctoVoucherId == null && other.tipoDctoVoucherId != null) || (this.tipoDctoVoucherId != null && !this.tipoDctoVoucherId.equals(other.tipoDctoVoucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoDctoVoucher[ tipoDctoVoucherId=" + tipoDctoVoucherId + " ]";
    }
    
}
