/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "venta_boleto")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaBoleto.findAll", query = "SELECT v FROM VentaBoleto v")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoId", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoId = :ventaBoletoId")
    , @NamedQuery(name = "VentaBoleto.findBySerie", query = "SELECT v FROM VentaBoleto v WHERE :inventarioCajaSerie BETWEEN v.ventaBoletoIdInventarioCaja.inventarioCajaSerie AND (v.ventaBoletoIdInventarioCaja.inventarioCajaSerie+1000) ")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdGuiaBusBoleto", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdBus = :ventaBoletoIdBus AND v.ventaBoletoIdInventarioCaja.inventarioCajaIdInventarioInterno.inventarioInternoIdBoleto = :inventarioInternoIdBoleto ORDER BY v.ventaBoletoId DESC")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoFecha", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoFecha = :ventaBoletoFecha")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdCajaDate", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoFecha = :ventaBoletoFecha AND v.ventaBoletoIdInventarioCaja.inventarioCajaIdCaja = :inventarioCajaIdCaja ORDER BY v.ventaBoletoNumeroBoleta ASC")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoNumeroBoleta", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoNumeroBoleta = :ventaBoletoNumeroBoleta")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoValor", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoValor = :ventaBoletoValor")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoRecaudado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoRecaudado = :ventaBoletoRecaudado")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoUtilizado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoUtilizado = :ventaBoletoUtilizado")})
public class VentaBoleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_boleto_id", nullable = false)
    private Integer ventaBoletoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ventaBoletoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_numero_boleta", nullable = false)
    private int ventaBoletoNumeroBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_valor", nullable = false)
    private int ventaBoletoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_recaudado", nullable = false)
    private boolean ventaBoletoRecaudado;
    @Column(name = "venta_boleto_folio_recaudacion")
    private int ventaBoletoFolioRecaudacion;
    @Column(name = "venta_boleto_folio_solymar")
    private int ventaBoletoFolioSolyMar;
    @Column(name = "venta_boleto_utilizado")
    private Boolean ventaBoletoUtilizado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,  mappedBy = "recaudacionBoletoIdVentaBoleto", fetch = FetchType.LAZY)
    private List<RecaudacionBoleto> recaudacionBoletoList;
    @JoinColumn(name = "venta_boleto_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bus ventaBoletoIdBus;
    @JoinColumn(name = "venta_boleto_id_inventario_caja", referencedColumnName = "inventario_caja_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InventarioCaja ventaBoletoIdInventarioCaja;
    @JoinColumn(name = "venta_boleto_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trabajador ventaBoletoIdTrabajador;
    @Transient
    private Guia guia; 
    
    
    public VentaBoleto() {
    }

    public VentaBoleto(Integer ventaBoletoId) {
        this.ventaBoletoId = ventaBoletoId;
    }

    public VentaBoleto(Integer ventaBoletoId, Date ventaBoletoFecha, int ventaBoletoNumeroBoleta, int ventaBoletoValor, boolean ventaBoletoRecaudado, int ventaBoletoFolioRecaudacion, int ventaBoletoFolioSolyMar) {
        this.ventaBoletoId = ventaBoletoId;
        this.ventaBoletoFecha = ventaBoletoFecha;
        this.ventaBoletoNumeroBoleta = ventaBoletoNumeroBoleta;
        this.ventaBoletoValor = ventaBoletoValor;
        this.ventaBoletoRecaudado = ventaBoletoRecaudado;
        this.ventaBoletoFolioRecaudacion = ventaBoletoFolioRecaudacion;
        this.ventaBoletoFolioSolyMar = ventaBoletoFolioSolyMar;
    }

    @PostUpdate
    public void postUpdated(){
        
    }
    
    public Integer getVentaBoletoId() {
        return ventaBoletoId;
    }

    public void setVentaBoletoId(Integer ventaBoletoId) {
        this.ventaBoletoId = ventaBoletoId;
    }

    public Date getVentaBoletoFecha() {
        return ventaBoletoFecha;
    }

    public void setVentaBoletoFecha(Date ventaBoletoFecha) {
        this.ventaBoletoFecha = ventaBoletoFecha;
    }

    public int getVentaBoletoNumeroBoleta() {
        return ventaBoletoNumeroBoleta;
    }

    public void setVentaBoletoNumeroBoleta(int ventaBoletoNumeroBoleta) {
        this.ventaBoletoNumeroBoleta = ventaBoletoNumeroBoleta;
    }

    public int getVentaBoletoValor() {
        return ventaBoletoValor;
    }

    public void setVentaBoletoValor(int ventaBoletoValor) {
        this.ventaBoletoValor = ventaBoletoValor;
    }

    public boolean getVentaBoletoRecaudado() {
        return ventaBoletoRecaudado;
    }

    public void setVentaBoletoRecaudado(boolean ventaBoletoRecaudado) {
        this.ventaBoletoRecaudado = ventaBoletoRecaudado;
    }

    public Boolean getVentaBoletoUtilizado() {
        return ventaBoletoUtilizado;
    }

    public void setVentaBoletoUtilizado(Boolean ventaBoletoUtilizado) {
        this.ventaBoletoUtilizado = ventaBoletoUtilizado;
    }

    public int getVentaBoletoFolioRecaudacion() {
        return ventaBoletoFolioRecaudacion;
    }

    public int getVentaBoletoFolioSolyMar() {
        return ventaBoletoFolioSolyMar;
    }

    public void setVentaBoletoFolioSolyMar(int ventaBoletoFolioSolyMar) {
        this.ventaBoletoFolioSolyMar = ventaBoletoFolioSolyMar;
    }

    public void setVentaBoletoFolioRecaudacion(int ventaBoletoFolioRecaudacion) {
        this.ventaBoletoFolioRecaudacion = ventaBoletoFolioRecaudacion;
    }

    @XmlTransient
    public List<RecaudacionBoleto> getRecaudacionBoletoList() {
        return recaudacionBoletoList;
    }

    public void setRecaudacionBoletoList(List<RecaudacionBoleto> recaudacionBoletoList) {
        this.recaudacionBoletoList = recaudacionBoletoList;
    }

    public Bus getVentaBoletoIdBus() {
        return ventaBoletoIdBus;
    }

    public void setVentaBoletoIdBus(Bus ventaBoletoIdBus) {
        this.ventaBoletoIdBus = ventaBoletoIdBus;
    }

    public InventarioCaja getVentaBoletoIdInventarioCaja() {
        return ventaBoletoIdInventarioCaja;
    }

    public void setVentaBoletoIdInventarioCaja(InventarioCaja ventaBoletoIdInventarioCaja) {
        this.ventaBoletoIdInventarioCaja = ventaBoletoIdInventarioCaja;
    }

    public Trabajador getVentaBoletoIdTrabajador() {
        return ventaBoletoIdTrabajador;
    }

    public void setVentaBoletoIdTrabajador(Trabajador ventaBoletoIdTrabajador) {
        this.ventaBoletoIdTrabajador = ventaBoletoIdTrabajador;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    public Guia getGuia() {
        return guia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaBoletoId != null ? ventaBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaBoleto)) {
            return false;
        }
        VentaBoleto other = (VentaBoleto) object;
        return (this.ventaBoletoId != null || other.ventaBoletoId == null) && (this.ventaBoletoId == null || this.ventaBoletoId.equals(other.ventaBoletoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VentaBoleto[ ventaBoletoId=" + ventaBoletoId + " ]";
    }

}
