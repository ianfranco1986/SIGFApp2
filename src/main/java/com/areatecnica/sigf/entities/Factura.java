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
    @NamedQuery(name = "Factura.findAll", query = "SELECT c FROM Factura c")
    , @NamedQuery(name = "Factura.findByFacturaId", query = "SELECT c FROM Factura c WHERE c.facturaId = :facturaId")
    , @NamedQuery(name = "Factura.findByFacturaFolio", query = "SELECT c FROM Factura c WHERE c.facturaFolio = :facturaFolio")
    , @NamedQuery(name = "Factura.findBetweenDates", query = "SELECT c FROM Factura c WHERE c.facturaFecha BETWEEN :from AND :to ORDER BY c.facturaId ASC")
    , @NamedQuery(name = "Factura.findByFacturaFecha", query = "SELECT c FROM Factura c WHERE c.facturaFecha = :facturaFecha")
})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "factura_id", nullable = false)
    private Integer facturaId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_folio", nullable = false, length = 45)
    private int facturaFolio;

    @Basic(optional = false)
    @NotNull
    @Column(name = "factura_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date facturaFecha;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "factura_nombre_cliente", nullable = false, length = 100)
    private String facturaNombreCliente;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "factura_detalle", nullable = false, length = 100)
    private String facturaDetalle;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "factura_rut_cliente", nullable = false, length = 100)
    private String facturaRutCliente;

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

    public Factura() {
    }

    public Factura(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Factura(int facturaFolio, Date facturaFecha, String facturaNombreCliente, String facturaRutCliente, String facturaDetalle, int facturaNeto, int facturaIva, int facturaTotal) {
        this.facturaFolio = facturaFolio;
        this.facturaFecha = facturaFecha;
        this.facturaNombreCliente = facturaNombreCliente;
        this.facturaRutCliente = facturaRutCliente;
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

    public String getFacturaNombreCliente() {
        return facturaNombreCliente;
    }

    public void setFacturaNombreCliente(String facturaNombreCliente) {
        this.facturaNombreCliente = facturaNombreCliente;
    }

    public String getFacturaRutCliente() {
        return facturaRutCliente;
    }

    public void setFacturaRutCliente(String facturaRutCliente) {
        this.facturaRutCliente = facturaRutCliente;
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
