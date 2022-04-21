/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "compra", catalog = "sigfdb_baquedano", schema = "")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByCompraId", query = "SELECT c FROM Compra c WHERE c.compraId = :compraId"),
    @NamedQuery(name = "Compra.findByCompraBetweenDates", query = "SELECT c FROM Compra c WHERE c.compraFechaDocumento BETWEEN :from AND :to ORDER BY c.compraFechaDocumento"),
    @NamedQuery(name = "Compra.findByCompraFechaDocumento", query = "SELECT c FROM Compra c WHERE c.compraFechaDocumento = :compraFechaDocumento"),
    @NamedQuery(name = "Compra.findByCompraFechaAcuse", query = "SELECT c FROM Compra c WHERE c.compraFechaAcuse = :compraFechaAcuse"),
    @NamedQuery(name = "Compra.findByCompraFechaRecepcion", query = "SELECT c FROM Compra c WHERE c.compraFechaRecepcion = :compraFechaRecepcion"),
    @NamedQuery(name = "Compra.findByCompraFolio", query = "SELECT c FROM Compra c WHERE c.compraFolio = :compraFolio"),
    @NamedQuery(name = "Compra.findByCompraDescripcion", query = "SELECT c FROM Compra c WHERE c.compraDescripcion = :compraDescripcion"),
    @NamedQuery(name = "Compra.findByCompraNeto", query = "SELECT c FROM Compra c WHERE c.compraNeto = :compraNeto"),
    @NamedQuery(name = "Compra.findByCompraExento", query = "SELECT c FROM Compra c WHERE c.compraExento = :compraExento"),
    @NamedQuery(name = "Compra.findByCompraIva", query = "SELECT c FROM Compra c WHERE c.compraIva = :compraIva"),
    @NamedQuery(name = "Compra.findByCompraOtrosImpuestos", query = "SELECT c FROM Compra c WHERE c.compraOtrosImpuestos = :compraOtrosImpuestos"),
    @NamedQuery(name = "Compra.findByCompraTotal", query = "SELECT c FROM Compra c WHERE c.compraTotal = :compraTotal")})
public class Compra extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compra_id")
    private Integer compraId;
    @Column(name = "compra_fecha_documento")
    @Temporal(TemporalType.DATE)
    private Date compraFechaDocumento;
    @Column(name = "compra_fecha_acuse")
    @Temporal(TemporalType.DATE)
    private Date compraFechaAcuse;
    @Column(name = "compra_fecha_recepcion")
    @Temporal(TemporalType.DATE)
    private Date compraFechaRecepcion;
    @Column(name = "compra_folio")
    private Integer compraFolio;
    @Size(max = 45)
    @Column(name = "compra_descripcion")
    private String compraDescripcion;
    @Column(name = "compra_neto")
    private Integer compraNeto;
    @Column(name = "compra_exento")
    private Integer compraExento;
    @Column(name = "compra_iva")
    private Integer compraIva;
    @Column(name = "compra_otros_impuestos")
    private Integer compraOtrosImpuestos;
    @Column(name = "compra_total")
    private Integer compraTotal;
    @JoinColumn(name = "compra_cuenta_mayor_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false)
    private CuentaMayor compraCuentaMayorId;
    @JoinColumn(name = "compra_movimiento_id", referencedColumnName = "movimiento_mes_id")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private MovimientoMes compraMovimientoId;
    @JoinColumn(name = "compra_proveedor_id", referencedColumnName = "proveedor_id")
    @ManyToOne(optional = false)
    private Proveedor compraProveedorId;
    @JoinColumn(name = "compra_tipo_documento_id", referencedColumnName = "tipo_documento_id")
    @ManyToOne(optional = false)
    private TipoDocumento compraTipoDocumentoId;

    public Compra() {
    }

    public Compra(Integer compraId) {
        this.compraId = compraId;
    }

    public Integer getCompraId() {
        return compraId;
    }

    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

    public Date getCompraFechaDocumento() {
        return compraFechaDocumento;
    }

    public void setCompraFechaDocumento(Date compraFechaDocumento) {
        this.compraFechaDocumento = compraFechaDocumento;
    }

    public Date getCompraFechaAcuse() {
        return compraFechaAcuse;
    }

    public void setCompraFechaAcuse(Date compraFechaAcuse) {
        this.compraFechaAcuse = compraFechaAcuse;
    }

    public Date getCompraFechaRecepcion() {
        return compraFechaRecepcion;
    }

    public void setCompraFechaRecepcion(Date compraFechaRecepcion) {
        this.compraFechaRecepcion = compraFechaRecepcion;
    }

    public Integer getCompraFolio() {
        return compraFolio;
    }

    public void setCompraFolio(Integer compraFolio) {
        this.compraFolio = compraFolio;
    }

    public String getCompraDescripcion() {
        return compraDescripcion;
    }

    public void setCompraDescripcion(String compraDescripcion) {
        this.compraDescripcion = compraDescripcion;
    }

    public Integer getCompraNeto() {
        return compraNeto;
    }

    public void setCompraNeto(Integer compraNeto) {
        this.compraNeto = compraNeto;
    }

    public Integer getCompraExento() {
        return compraExento;
    }

    public void setCompraExento(Integer compraExento) {
        this.compraExento = compraExento;
    }

    public Integer getCompraIva() {
        return compraIva;
    }

    public void setCompraIva(Integer compraIva) {
        this.compraIva = compraIva;
    }

    public Integer getCompraOtrosImpuestos() {
        return compraOtrosImpuestos;
    }

    public void setCompraOtrosImpuestos(Integer compraOtrosImpuestos) {
        this.compraOtrosImpuestos = compraOtrosImpuestos;
    }

    public Integer getCompraTotal() {
        return compraTotal;
    }

    public void setCompraTotal(Integer compraTotal) {
        this.compraTotal = compraTotal;
    }

    public CuentaMayor getCompraCuentaMayorId() {
        return compraCuentaMayorId;
    }

    public void setCompraCuentaMayorId(CuentaMayor compraCuentaMayorId) {
        this.compraCuentaMayorId = compraCuentaMayorId;
    }

    public MovimientoMes getCompraMovimientoId() {
        return compraMovimientoId;
    }

    public void setCompraMovimientoId(MovimientoMes compraMovimientoId) {
        this.compraMovimientoId = compraMovimientoId;
    }

    public Proveedor getCompraProveedorId() {
        return compraProveedorId;
    }

    public void setCompraProveedorId(Proveedor compraProveedorId) {
        this.compraProveedorId = compraProveedorId;
    }

    public TipoDocumento getCompraTipoDocumentoId() {
        return compraTipoDocumentoId;
    }

    public void setCompraTipoDocumentoId(TipoDocumento compraTipoDocumentoId) {
        this.compraTipoDocumentoId = compraTipoDocumentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraId != null ? compraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.compraId == null && other.compraId != null) || (this.compraId != null && !this.compraId.equals(other.compraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Compra[ compraId=" + compraId + " ]";
    }

}
