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
@Table(name = "tipo_comprobante_voucher", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoComprobanteVoucher.findAll", query = "SELECT t FROM TipoComprobanteVoucher t"),
    @NamedQuery(name = "TipoComprobanteVoucher.findByTipoCompVoucherId", query = "SELECT t FROM TipoComprobanteVoucher t WHERE t.tipoCompVoucherId = :tipoCompVoucherId"),
    @NamedQuery(name = "TipoComprobanteVoucher.findByTipoCompVoucherDescripcion", query = "SELECT t FROM TipoComprobanteVoucher t WHERE t.tipoCompVoucherDescripcion = :tipoCompVoucherDescripcion")})
public class TipoComprobanteVoucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_comp_voucher_id")
    private Integer tipoCompVoucherId;
    @Column(name = "tipo_comp_voucher_descripcion")
    private String tipoCompVoucherDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voucherTipoCompId")
    private List<Voucher> voucherList;

    public TipoComprobanteVoucher() {
    }

    public TipoComprobanteVoucher(Integer tipoCompVoucherId) {
        this.tipoCompVoucherId = tipoCompVoucherId;
    }

    public Integer getTipoCompVoucherId() {
        return tipoCompVoucherId;
    }

    public void setTipoCompVoucherId(Integer tipoCompVoucherId) {
        this.tipoCompVoucherId = tipoCompVoucherId;
    }

    public String getTipoCompVoucherDescripcion() {
        return tipoCompVoucherDescripcion;
    }

    public void setTipoCompVoucherDescripcion(String tipoCompVoucherDescripcion) {
        this.tipoCompVoucherDescripcion = tipoCompVoucherDescripcion;
    }

    public List<Voucher> getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(List<Voucher> voucherList) {
        this.voucherList = voucherList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCompVoucherId != null ? tipoCompVoucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComprobanteVoucher)) {
            return false;
        }
        TipoComprobanteVoucher other = (TipoComprobanteVoucher) object;
        if ((this.tipoCompVoucherId == null && other.tipoCompVoucherId != null) || (this.tipoCompVoucherId != null && !this.tipoCompVoucherId.equals(other.tipoCompVoucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoComprobanteVoucher[ tipoCompVoucherId=" + tipoCompVoucherId + " ]";
    }
    
}
