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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "voucher_movimiento", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "VoucherMovimiento.findAll", query = "SELECT v FROM VoucherMovimiento v"),
    @NamedQuery(name = "VoucherMovimiento.findByVoucherMovimientoId", query = "SELECT v FROM VoucherMovimiento v WHERE v.voucherMovimientoId = :voucherMovimientoId"),
    @NamedQuery(name = "VoucherMovimiento.findByVoucherMovimientoEsDebito", query = "SELECT v FROM VoucherMovimiento v WHERE v.voucherMovimientoEsDebito = :voucherMovimientoEsDebito"),
    @NamedQuery(name = "VoucherMovimiento.findByVoucherMovimientoMonto", query = "SELECT v FROM VoucherMovimiento v WHERE v.voucherMovimientoMonto = :voucherMovimientoMonto"),
    @NamedQuery(name = "VoucherMovimiento.findByVoucherMovimientoDescripcion", query = "SELECT v FROM VoucherMovimiento v WHERE v.voucherMovimientoDescripcion = :voucherMovimientoDescripcion")})
public class VoucherMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "voucher_movimiento_id")
    private Integer voucherMovimientoId;
    @Basic(optional = false)
    @Column(name = "voucher_movimiento_es_debito")
    private boolean voucherMovimientoEsDebito;
    @Basic(optional = false)
    @Column(name = "voucher_movimiento_monto")
    private int voucherMovimientoMonto;
    @Column(name = "voucher_movimiento_descripcion")
    private String voucherMovimientoDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auxiliarComprasVMovId", fetch = FetchType.LAZY)
    private List<AuxiliarCompras> auxiliarComprasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoBancarioVmovId", fetch = FetchType.LAZY)
    private List<MovimientoBancario> movimientoBancarioList;
    @JoinColumn(name = "voucher_movimiento_centro_costo_id", referencedColumnName = "centro_costo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CentroCosto voucherMovimientoCentroCostoId;
    @JoinColumn(name = "voucher_movimiento_cuenta_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CuentaMayor voucherMovimientoCuentaId;
    @JoinColumn(name = "voucher_movimiento_vid", referencedColumnName = "voucher_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Voucher voucherMovimientoVid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auxiliarFacturasVMovId", fetch = FetchType.LAZY)
    private List<AuxiliarFacturas> auxiliarFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auxiliarHonorarioVMovId", fetch = FetchType.LAZY)
    private List<AuxiliarHonorarios> auxiliarHonorariosList;

    public VoucherMovimiento() {
    }

    public VoucherMovimiento(Voucher voucher) {
        this.voucherMovimientoVid = voucher;
    }

    public VoucherMovimiento(Integer voucherMovimientoId) {
        this.voucherMovimientoId = voucherMovimientoId;
    }

    public VoucherMovimiento(Integer voucherMovimientoId, boolean voucherMovimientoEsDebito, int voucherMovimientoMonto) {
        this.voucherMovimientoId = voucherMovimientoId;
        this.voucherMovimientoEsDebito = voucherMovimientoEsDebito;
        this.voucherMovimientoMonto = voucherMovimientoMonto;
    }

    public Integer getVoucherMovimientoId() {
        return voucherMovimientoId;
    }

    public void setVoucherMovimientoId(Integer voucherMovimientoId) {
        this.voucherMovimientoId = voucherMovimientoId;
    }

    public boolean getVoucherMovimientoEsDebito() {
        return voucherMovimientoEsDebito;
    }

    public void setVoucherMovimientoEsDebito(boolean voucherMovimientoEsDebito) {
        this.voucherMovimientoEsDebito = voucherMovimientoEsDebito;
    }

    public int getVoucherMovimientoMonto() {
        return voucherMovimientoMonto;
    }

    public void setVoucherMovimientoMonto(int voucherMovimientoMonto) {
        this.voucherMovimientoMonto = voucherMovimientoMonto;
    }

    public String getVoucherMovimientoDescripcion() {
        return voucherMovimientoDescripcion;
    }

    public void setVoucherMovimientoDescripcion(String voucherMovimientoDescripcion) {
        this.voucherMovimientoDescripcion = voucherMovimientoDescripcion;
    }

    public List<AuxiliarCompras> getAuxiliarComprasList() {
        return auxiliarComprasList;
    }

    public void setAuxiliarComprasList(List<AuxiliarCompras> auxiliarComprasList) {
        this.auxiliarComprasList = auxiliarComprasList;
    }

    public List<MovimientoBancario> getMovimientoBancarioList() {
        return movimientoBancarioList;
    }

    public void setMovimientoBancarioList(List<MovimientoBancario> movimientoBancarioList) {
        this.movimientoBancarioList = movimientoBancarioList;
    }

    public CentroCosto getVoucherMovimientoCentroCostoId() {
        return voucherMovimientoCentroCostoId;
    }

    public void setVoucherMovimientoCentroCostoId(CentroCosto voucherMovimientoCentroCostoId) {
        this.voucherMovimientoCentroCostoId = voucherMovimientoCentroCostoId;
    }

    public CuentaMayor getVoucherMovimientoCuentaId() {
        return voucherMovimientoCuentaId;
    }

    public void setVoucherMovimientoCuentaId(CuentaMayor voucherMovimientoCuentaId) {
        this.voucherMovimientoCuentaId = voucherMovimientoCuentaId;
    }

    public Voucher getVoucherMovimientoVid() {
        return voucherMovimientoVid;
    }

    public void setVoucherMovimientoVid(Voucher voucherMovimientoVid) {
        this.voucherMovimientoVid = voucherMovimientoVid;
    }

    public List<AuxiliarFacturas> getAuxiliarFacturasList() {
        return auxiliarFacturasList;
    }

    public void setAuxiliarFacturasList(List<AuxiliarFacturas> auxiliarFacturasList) {
        this.auxiliarFacturasList = auxiliarFacturasList;
    }

    public List<AuxiliarHonorarios> getAuxiliarHonorariosList() {
        return auxiliarHonorariosList;
    }

    public void setAuxiliarHonorariosList(List<AuxiliarHonorarios> auxiliarHonorariosList) {
        this.auxiliarHonorariosList = auxiliarHonorariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voucherMovimientoId != null ? voucherMovimientoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoucherMovimiento)) {
            return false;
        }
        VoucherMovimiento other = (VoucherMovimiento) object;
        if ((this.voucherMovimientoId == null && other.voucherMovimientoId != null) || (this.voucherMovimientoId != null && !this.voucherMovimientoId.equals(other.voucherMovimientoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VoucherMovimiento[ voucherMovimientoId=" + voucherMovimientoId + " ]";
    }

}
