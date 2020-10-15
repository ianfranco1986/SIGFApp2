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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "factura", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByFacturaId", query = "SELECT f FROM Factura f WHERE f.facturaId = :facturaId"),
    @NamedQuery(name = "Factura.findByFacturaFolio", query = "SELECT f FROM Factura f WHERE f.facturaFolio = :facturaFolio"),
    @NamedQuery(name = "Factura.findBetweenDates", query = "SELECT f FROM Factura f WHERE f.facturaFecha BETWEEN :from AND :to ORDER BY f.facturaFecha ASC"),
    @NamedQuery(name = "Factura.findByFacturaFecha", query = "SELECT f FROM Factura f WHERE f.facturaFecha = :facturaFecha"),
    @NamedQuery(name = "Factura.findByFacturaDetalle", query = "SELECT f FROM Factura f WHERE f.facturaDetalle = :facturaDetalle"),
    @NamedQuery(name = "Factura.findByFacturaNeto", query = "SELECT f FROM Factura f WHERE f.facturaNeto = :facturaNeto"),
    @NamedQuery(name = "Factura.findByFacturaIva", query = "SELECT f FROM Factura f WHERE f.facturaIva = :facturaIva"),
    @NamedQuery(name = "Factura.findByFacturaTotal", query = "SELECT f FROM Factura f WHERE f.facturaTotal = :facturaTotal")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "factura_id")
    private Integer facturaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_folio")
    private int facturaFolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_fecha")
    @Temporal(TemporalType.DATE)
    private Date facturaFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "factura_detalle")
    private String facturaDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_neto")
    private int facturaNeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_iva")
    private int facturaIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_total")
    private int facturaTotal;
    @JoinColumn(name = "factura_cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false)
    private Cliente facturaClienteId;
    @JoinColumn(name = "factura_cuenta_mayor_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false)
    private CuentaMayor facturaCuentaMayorId;
    @JoinColumn(name = "factura_movimiento_id", referencedColumnName = "movimiento_mes_id")
    @ManyToOne(optional = false)
    private MovimientoMes facturaMovimientoId;
    @JoinColumn(name = "factura_tipo_documento_id", referencedColumnName = "tipo_documento_id")
    @ManyToOne(optional = false)
    private TipoDocumento facturaTipoDocumentoId;

    public Factura() {
    }

    public Factura(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Factura(Integer facturaId, int facturaFolio, Date facturaFecha, String facturaDetalle, int facturaNeto, int facturaIva, int facturaTotal) {
        this.facturaId = facturaId;
        this.facturaFolio = facturaFolio;
        this.facturaFecha = facturaFecha;
        this.facturaDetalle = facturaDetalle;
        this.facturaNeto = facturaNeto;
        this.facturaIva = facturaIva;
        this.facturaTotal = facturaTotal;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public int getFacturaFolio() {
        return facturaFolio;
    }

    public void setFacturaFolio(int facturaFolio) {
        this.facturaFolio = facturaFolio;
    }

    public Date getFacturaFecha() {
        return facturaFecha;
    }

    public void setFacturaFecha(Date facturaFecha) {
        this.facturaFecha = facturaFecha;
    }

    public String getFacturaDetalle() {
        return facturaDetalle;
    }

    public void setFacturaDetalle(String facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
    }

    public int getFacturaNeto() {
        return facturaNeto;
    }

    public void setFacturaNeto(int facturaNeto) {
        this.facturaNeto = facturaNeto;
    }

    public int getFacturaIva() {
        return facturaIva;
    }

    public void setFacturaIva(int facturaIva) {
        this.facturaIva = facturaIva;
    }

    public int getFacturaTotal() {
        return facturaTotal;
    }

    public void setFacturaTotal(int facturaTotal) {
        this.facturaTotal = facturaTotal;
    }

    public Cliente getFacturaClienteId() {
        return facturaClienteId;
    }

    public void setFacturaClienteId(Cliente facturaClienteId) {
        this.facturaClienteId = facturaClienteId;
    }

    public CuentaMayor getFacturaCuentaMayorId() {
        return facturaCuentaMayorId;
    }

    public void setFacturaCuentaMayorId(CuentaMayor facturaCuentaMayorId) {
        this.facturaCuentaMayorId = facturaCuentaMayorId;
    }

    public MovimientoMes getFacturaMovimientoId() {
        return facturaMovimientoId;
    }

    public void setFacturaMovimientoId(MovimientoMes facturaMovimientoId) {
        this.facturaMovimientoId = facturaMovimientoId;
    }

    public TipoDocumento getFacturaTipoDocumentoId() {
        return facturaTipoDocumentoId;
    }

    public void setFacturaTipoDocumentoId(TipoDocumento facturaTipoDocumentoId) {
        this.facturaTipoDocumentoId = facturaTipoDocumentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaId != null ? facturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facturaId == null && other.facturaId != null) || (this.facturaId != null && !this.facturaId.equals(other.facturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Factura[ facturaId=" + facturaId + " ]";
    }
    
}
