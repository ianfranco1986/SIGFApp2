/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "voucher", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByVoucherId", query = "SELECT v FROM Voucher v WHERE v.voucherId = :voucherId"),
    @NamedQuery(name = "Voucher.findByVoucherNumero", query = "SELECT v FROM Voucher v WHERE v.voucherNumero = :voucherNumero"),
    @NamedQuery(name = "Voucher.findByVoucherFecha", query = "SELECT v FROM Voucher v WHERE v.voucherFecha = :voucherFecha"),
    @NamedQuery(name = "Voucher.findByVoucherTipo", query = "SELECT v FROM Voucher v WHERE v.voucherTipo = :voucherTipo"),
    @NamedQuery(name = "Voucher.findByVoucherDebe", query = "SELECT v FROM Voucher v WHERE v.voucherDebe = :voucherDebe"),
    @NamedQuery(name = "Voucher.findByVoucherHaber", query = "SELECT v FROM Voucher v WHERE v.voucherHaber = :voucherHaber"),
    @NamedQuery(name = "Voucher.findByVoucherDetalle", query = "SELECT v FROM Voucher v WHERE v.voucherDetalle = :voucherDetalle"),
    @NamedQuery(name = "Voucher.findByVoucherPersona", query = "SELECT v FROM Voucher v WHERE v.voucherPersona = :voucherPersona")})
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "voucher_id")
    private Integer voucherId;
    @Basic(optional = false)
    @Column(name = "voucher_numero")
    private int voucherNumero;
    @Basic(optional = false)
    @Column(name = "voucher_fecha")
    @Temporal(TemporalType.DATE)
    private Date voucherFecha;
    @Basic(optional = false)
    @Column(name = "voucher_tipo")
    private boolean voucherTipo;
    @Basic(optional = false)
    @Column(name = "voucher_debe")
    private int voucherDebe;
    @Basic(optional = false)
    @Column(name = "voucher_haber")
    private int voucherHaber;
    @Basic(optional = false)
    @Column(name = "voucher_detalle")
    private String voucherDetalle;
    @Column(name = "voucher_persona")
    private String voucherPersona;
    @JoinColumn(name = "voucher_tipo_comp_id", referencedColumnName = "tipo_comp_voucher_id")
    @ManyToOne(optional = false)
    private TipoComprobanteVoucher voucherTipoCompId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleVoucherVoucherId")
    private List<DetalleVoucher> detalleVoucherList;

    public Voucher() {
    }

    public Voucher(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Voucher(Integer voucherId, int voucherNumero, Date voucherFecha, boolean voucherTipo, int voucherDebe, int voucherHaber, String voucherDetalle) {
        this.voucherId = voucherId;
        this.voucherNumero = voucherNumero;
        this.voucherFecha = voucherFecha;
        this.voucherTipo = voucherTipo;
        this.voucherDebe = voucherDebe;
        this.voucherHaber = voucherHaber;
        this.voucherDetalle = voucherDetalle;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public int getVoucherNumero() {
        return voucherNumero;
    }

    public void setVoucherNumero(int voucherNumero) {
        this.voucherNumero = voucherNumero;
    }

    public Date getVoucherFecha() {
        return voucherFecha;
    }

    public void setVoucherFecha(Date voucherFecha) {
        this.voucherFecha = voucherFecha;
    }

    public boolean getVoucherTipo() {
        return voucherTipo;
    }

    public void setVoucherTipo(boolean voucherTipo) {
        this.voucherTipo = voucherTipo;
    }

    public int getVoucherDebe() {
        return voucherDebe;
    }

    public void setVoucherDebe(int voucherDebe) {
        this.voucherDebe = voucherDebe;
    }

    public int getVoucherHaber() {
        return voucherHaber;
    }

    public void setVoucherHaber(int voucherHaber) {
        this.voucherHaber = voucherHaber;
    }

    public String getVoucherDetalle() {
        return voucherDetalle;
    }

    public void setVoucherDetalle(String voucherDetalle) {
        this.voucherDetalle = voucherDetalle;
    }

    public String getVoucherPersona() {
        return voucherPersona;
    }

    public void setVoucherPersona(String voucherPersona) {
        this.voucherPersona = voucherPersona;
    }

    public TipoComprobanteVoucher getVoucherTipoCompId() {
        return voucherTipoCompId;
    }

    public void setVoucherTipoCompId(TipoComprobanteVoucher voucherTipoCompId) {
        this.voucherTipoCompId = voucherTipoCompId;
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
        hash += (voucherId != null ? voucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.voucherId == null && other.voucherId != null) || (this.voucherId != null && !this.voucherId.equals(other.voucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Voucher[ voucherId=" + voucherId + " ]";
    }
    
}
