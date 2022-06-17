/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "factura", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByFacturaId", query = "SELECT f FROM Factura f WHERE f.facturaId = :facturaId"),
    @NamedQuery(name = "Factura.findByFacturaFolio", query = "SELECT f FROM Factura f WHERE f.facturaFolio = :facturaFolio"),
    @NamedQuery(name = "Factura.findByFacturaFecha", query = "SELECT f FROM Factura f WHERE f.facturaFecha = :facturaFecha"),
    @NamedQuery(name = "Factura.findByFacturaDetalle", query = "SELECT f FROM Factura f WHERE f.facturaDetalle = :facturaDetalle"),
    @NamedQuery(name = "Factura.findByFacturaNeto", query = "SELECT f FROM Factura f WHERE f.facturaNeto = :facturaNeto"),
    @NamedQuery(name = "Factura.findByFacturaIva", query = "SELECT f FROM Factura f WHERE f.facturaIva = :facturaIva"),
    @NamedQuery(name = "Factura.findByFacturaTotal", query = "SELECT f FROM Factura f WHERE f.facturaTotal = :facturaTotal")})
public class Factura extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "factura_id")
    private Integer facturaId;
    @Basic(optional = false)
    @Column(name = "factura_folio")
    private int facturaFolio;
    @Basic(optional = false)
    @Column(name = "factura_fecha")
    @Temporal(TemporalType.DATE)
    private Date facturaFecha;
    @Basic(optional = false)
    @Column(name = "factura_detalle")
    private String facturaDetalle;
    @Basic(optional = false)
    @Column(name = "factura_neto")
    private int facturaNeto;
    @Basic(optional = false)
    @Column(name = "factura_iva")
    private int facturaIva;
    @Basic(optional = false)
    @Column(name = "factura_total")
    private int facturaTotal;
    @JoinColumn(name = "factura_cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false)
    private Cliente facturaClienteId;
    @JoinColumn(name = "factura_tipo_documento_id", referencedColumnName = "tipo_documento_id")
    @ManyToOne(optional = false)
    private TipoDocumento facturaTipoDocumentoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auxiliarFacturasFacturaId")
    private List<AuxiliarFacturas> auxiliarFacturasList;

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

    public TipoDocumento getFacturaTipoDocumentoId() {
        return facturaTipoDocumentoId;
    }

    public void setFacturaTipoDocumentoId(TipoDocumento facturaTipoDocumentoId) {
        this.facturaTipoDocumentoId = facturaTipoDocumentoId;
    }

    public List<AuxiliarFacturas> getAuxiliarFacturasList() {
        return auxiliarFacturasList;
    }

    public void setAuxiliarFacturasList(List<AuxiliarFacturas> auxiliarFacturasList) {
        this.auxiliarFacturasList = auxiliarFacturasList;
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
