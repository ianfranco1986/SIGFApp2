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
@Table(name = "detalle_voucher", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "DetalleVoucher.findAll", query = "SELECT d FROM DetalleVoucher d"),
    @NamedQuery(name = "DetalleVoucher.findByDetalleVoucherId", query = "SELECT d FROM DetalleVoucher d WHERE d.detalleVoucherId = :detalleVoucherId"),
    @NamedQuery(name = "DetalleVoucher.findByDetalleVoucherDescripcion", query = "SELECT d FROM DetalleVoucher d WHERE d.detalleVoucherDescripcion = :detalleVoucherDescripcion"),
    @NamedQuery(name = "DetalleVoucher.findByDetalleVoucherDebe", query = "SELECT d FROM DetalleVoucher d WHERE d.detalleVoucherDebe = :detalleVoucherDebe"),
    @NamedQuery(name = "DetalleVoucher.findByDetalleVoucherHaber", query = "SELECT d FROM DetalleVoucher d WHERE d.detalleVoucherHaber = :detalleVoucherHaber")})
public class DetalleVoucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_voucher_id")
    private Integer detalleVoucherId;
    @Basic(optional = false)
    @Column(name = "detalle_voucher_descripcion")
    private String detalleVoucherDescripcion;
    @Basic(optional = false)
    @Column(name = "detalle_voucher_debe")
    private int detalleVoucherDebe;
    @Basic(optional = false)
    @Column(name = "detalle_voucher_haber")
    private int detalleVoucherHaber;
    @JoinColumn(name = "detalle_voucher_cuenta_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false)
    private CuentaMayor detalleVoucherCuentaId;
    @JoinColumn(name = "detalle_voucher_doc_voucher_id", referencedColumnName = "tipo_dcto_voucher_id")
    @ManyToOne(optional = false)
    private TipoDctoVoucher detalleVoucherDocVoucherId;
    @JoinColumn(name = "detalle_voucher_voucher_id", referencedColumnName = "voucher_id")
    @ManyToOne(optional = false)
    private Voucher detalleVoucherVoucherId;

    public DetalleVoucher() {
    }

    public DetalleVoucher(Integer detalleVoucherId) {
        this.detalleVoucherId = detalleVoucherId;
    }

    public DetalleVoucher(Integer detalleVoucherId, String detalleVoucherDescripcion, int detalleVoucherDebe, int detalleVoucherHaber) {
        this.detalleVoucherId = detalleVoucherId;
        this.detalleVoucherDescripcion = detalleVoucherDescripcion;
        this.detalleVoucherDebe = detalleVoucherDebe;
        this.detalleVoucherHaber = detalleVoucherHaber;
    }

    public Integer getDetalleVoucherId() {
        return detalleVoucherId;
    }

    public void setDetalleVoucherId(Integer detalleVoucherId) {
        this.detalleVoucherId = detalleVoucherId;
    }

    public String getDetalleVoucherDescripcion() {
        return detalleVoucherDescripcion;
    }

    public void setDetalleVoucherDescripcion(String detalleVoucherDescripcion) {
        this.detalleVoucherDescripcion = detalleVoucherDescripcion;
    }

    public int getDetalleVoucherDebe() {
        return detalleVoucherDebe;
    }

    public void setDetalleVoucherDebe(int detalleVoucherDebe) {
        this.detalleVoucherDebe = detalleVoucherDebe;
    }

    public int getDetalleVoucherHaber() {
        return detalleVoucherHaber;
    }

    public void setDetalleVoucherHaber(int detalleVoucherHaber) {
        this.detalleVoucherHaber = detalleVoucherHaber;
    }

    public CuentaMayor getDetalleVoucherCuentaId() {
        return detalleVoucherCuentaId;
    }

    public void setDetalleVoucherCuentaId(CuentaMayor detalleVoucherCuentaId) {
        this.detalleVoucherCuentaId = detalleVoucherCuentaId;
    }

    public TipoDctoVoucher getDetalleVoucherDocVoucherId() {
        return detalleVoucherDocVoucherId;
    }

    public void setDetalleVoucherDocVoucherId(TipoDctoVoucher detalleVoucherDocVoucherId) {
        this.detalleVoucherDocVoucherId = detalleVoucherDocVoucherId;
    }

    public Voucher getDetalleVoucherVoucherId() {
        return detalleVoucherVoucherId;
    }

    public void setDetalleVoucherVoucherId(Voucher detalleVoucherVoucherId) {
        this.detalleVoucherVoucherId = detalleVoucherVoucherId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleVoucherId != null ? detalleVoucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVoucher)) {
            return false;
        }
        DetalleVoucher other = (DetalleVoucher) object;
        if ((this.detalleVoucherId == null && other.detalleVoucherId != null) || (this.detalleVoucherId != null && !this.detalleVoucherId.equals(other.detalleVoucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleVoucher[ detalleVoucherId=" + detalleVoucherId + " ]";
    }
    
}
