/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "compra_petroleo", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "CompraPetroleo.findAll", query = "SELECT c FROM CompraPetroleo c"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoId", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoId = :compraPetroleoId"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoFecha", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoFecha = :compraPetroleoFecha"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoFolio", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoFolio = :compraPetroleoFolio"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoCantidadLitros", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoCantidadLitros = :compraPetroleoCantidadLitros"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoSurtidorN1", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoSurtidorN1 = :compraPetroleoSurtidorN1"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoSurtidorN2", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoSurtidorN2 = :compraPetroleoSurtidorN2"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoBaseAfecta", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoBaseAfecta = :compraPetroleoBaseAfecta"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoIev", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoIev = :compraPetroleoIev"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoIef", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoIef = :compraPetroleoIef"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoIva", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoIva = :compraPetroleoIva"),
    @NamedQuery(name = "CompraPetroleo.findByCompraPetroleoTotal", query = "SELECT c FROM CompraPetroleo c WHERE c.compraPetroleoTotal = :compraPetroleoTotal")})
public class CompraPetroleo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compra_petroleo_id")
    private Integer compraPetroleoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_fecha")
    @Temporal(TemporalType.DATE)
    private Date compraPetroleoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_folio")
    private int compraPetroleoFolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_cantidad_litros")
    private int compraPetroleoCantidadLitros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_surtidor_n1")
    private int compraPetroleoSurtidorN1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_surtidor_n2")
    private int compraPetroleoSurtidorN2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_base_afecta")
    private int compraPetroleoBaseAfecta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_iev")
    private int compraPetroleoIev;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_ief")
    private int compraPetroleoIef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_iva")
    private int compraPetroleoIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_petroleo_total")
    private int compraPetroleoTotal;
    @JoinColumn(name = "compra_petroleo_cuenta_mayor_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CuentaMayor compraPetroleoCuentaMayorId;
    @JoinColumn(name = "compra_petroleo_movt_id", referencedColumnName = "movimiento_mes_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovimientoMes compraPetroleoMovtId;
    @JoinColumn(name = "compra_petroleo_proveedor_id", referencedColumnName = "proveedor_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedor compraPetroleoProveedorId;
    @JoinColumn(name = "compra_petroleo_tipo_documento_id", referencedColumnName = "tipo_documento_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDocumento compraPetroleoTipoDocumentoId;

    public CompraPetroleo() {
    }

    public CompraPetroleo(Integer compraPetroleoId) {
        this.compraPetroleoId = compraPetroleoId;
    }

    public CompraPetroleo(Integer compraPetroleoId, Date compraPetroleoFecha, int compraPetroleoFolio, int compraPetroleoCantidadLitros, int compraPetroleoSurtidorN1, int compraPetroleoSurtidorN2, int compraPetroleoBaseAfecta, int compraPetroleoIev, int compraPetroleoIef, int compraPetroleoIva, int compraPetroleoTotal) {
        this.compraPetroleoId = compraPetroleoId;
        this.compraPetroleoFecha = compraPetroleoFecha;
        this.compraPetroleoFolio = compraPetroleoFolio;
        this.compraPetroleoCantidadLitros = compraPetroleoCantidadLitros;
        this.compraPetroleoSurtidorN1 = compraPetroleoSurtidorN1;
        this.compraPetroleoSurtidorN2 = compraPetroleoSurtidorN2;
        this.compraPetroleoBaseAfecta = compraPetroleoBaseAfecta;
        this.compraPetroleoIev = compraPetroleoIev;
        this.compraPetroleoIef = compraPetroleoIef;
        this.compraPetroleoIva = compraPetroleoIva;
        this.compraPetroleoTotal = compraPetroleoTotal;
    }

    public Integer getCompraPetroleoId() {
        return compraPetroleoId;
    }

    public void setCompraPetroleoId(Integer compraPetroleoId) {
        this.compraPetroleoId = compraPetroleoId;
    }

    public Date getCompraPetroleoFecha() {
        return compraPetroleoFecha;
    }

    public void setCompraPetroleoFecha(Date compraPetroleoFecha) {
        this.compraPetroleoFecha = compraPetroleoFecha;
    }

    public int getCompraPetroleoFolio() {
        return compraPetroleoFolio;
    }

    public void setCompraPetroleoFolio(int compraPetroleoFolio) {
        this.compraPetroleoFolio = compraPetroleoFolio;
    }

    public int getCompraPetroleoCantidadLitros() {
        return compraPetroleoCantidadLitros;
    }

    public void setCompraPetroleoCantidadLitros(int compraPetroleoCantidadLitros) {
        this.compraPetroleoCantidadLitros = compraPetroleoCantidadLitros;
    }

    public int getCompraPetroleoSurtidorN1() {
        return compraPetroleoSurtidorN1;
    }

    public void setCompraPetroleoSurtidorN1(int compraPetroleoSurtidorN1) {
        this.compraPetroleoSurtidorN1 = compraPetroleoSurtidorN1;
    }

    public int getCompraPetroleoSurtidorN2() {
        return compraPetroleoSurtidorN2;
    }

    public void setCompraPetroleoSurtidorN2(int compraPetroleoSurtidorN2) {
        this.compraPetroleoSurtidorN2 = compraPetroleoSurtidorN2;
    }

    public int getCompraPetroleoBaseAfecta() {
        return compraPetroleoBaseAfecta;
    }

    public void setCompraPetroleoBaseAfecta(int compraPetroleoBaseAfecta) {
        this.compraPetroleoBaseAfecta = compraPetroleoBaseAfecta;
    }

    public int getCompraPetroleoIev() {
        return compraPetroleoIev;
    }

    public void setCompraPetroleoIev(int compraPetroleoIev) {
        this.compraPetroleoIev = compraPetroleoIev;
    }

    public int getCompraPetroleoIef() {
        return compraPetroleoIef;
    }

    public void setCompraPetroleoIef(int compraPetroleoIef) {
        this.compraPetroleoIef = compraPetroleoIef;
    }

    public int getCompraPetroleoIva() {
        return compraPetroleoIva;
    }

    public void setCompraPetroleoIva(int compraPetroleoIva) {
        this.compraPetroleoIva = compraPetroleoIva;
    }

    public int getCompraPetroleoTotal() {
        return compraPetroleoTotal;
    }

    public void setCompraPetroleoTotal(int compraPetroleoTotal) {
        this.compraPetroleoTotal = compraPetroleoTotal;
    }

    public CuentaMayor getCompraPetroleoCuentaMayorId() {
        return compraPetroleoCuentaMayorId;
    }

    public void setCompraPetroleoCuentaMayorId(CuentaMayor compraPetroleoCuentaMayorId) {
        this.compraPetroleoCuentaMayorId = compraPetroleoCuentaMayorId;
    }

    public MovimientoMes getCompraPetroleoMovtId() {
        return compraPetroleoMovtId;
    }

    public void setCompraPetroleoMovtId(MovimientoMes compraPetroleoMovtId) {
        this.compraPetroleoMovtId = compraPetroleoMovtId;
    }

    public Proveedor getCompraPetroleoProveedorId() {
        return compraPetroleoProveedorId;
    }

    public void setCompraPetroleoProveedorId(Proveedor compraPetroleoProveedorId) {
        this.compraPetroleoProveedorId = compraPetroleoProveedorId;
    }

    public TipoDocumento getCompraPetroleoTipoDocumentoId() {
        return compraPetroleoTipoDocumentoId;
    }

    public void setCompraPetroleoTipoDocumentoId(TipoDocumento compraPetroleoTipoDocumentoId) {
        this.compraPetroleoTipoDocumentoId = compraPetroleoTipoDocumentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraPetroleoId != null ? compraPetroleoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraPetroleo)) {
            return false;
        }
        CompraPetroleo other = (CompraPetroleo) object;
        if ((this.compraPetroleoId == null && other.compraPetroleoId != null) || (this.compraPetroleoId != null && !this.compraPetroleoId.equals(other.compraPetroleoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CompraPetroleo[ compraPetroleoId=" + compraPetroleoId + " ]";
    }
    
}
