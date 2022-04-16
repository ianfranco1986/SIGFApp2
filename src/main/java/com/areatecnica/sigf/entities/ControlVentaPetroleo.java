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
import javax.validation.constraints.NotNull;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "control_venta_petroleo")
@EntityListeners(AuditListener.class)
@NamedQueries({
    @NamedQuery(name = "ControlVentaPetroleo.findAll", query = "SELECT c FROM ControlVentaPetroleo c"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoId", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoId = :controlVentaPetroleoId"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoFecha", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoFecha = :controlVentaPetroleoFecha"),
    @NamedQuery(name = "ControlVentaPetroleo.findByDates", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoFecha BETWEEN :from AND :to and c.controlIdCuenta = :idCuenta ORDER BY c.controlVentaPetroleoFecha "),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN1StockInicial", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN1StockInicial = :controlVentaPetroleoN1StockInicial"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN1NumeralInicial", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN1NumeralInicial = :controlVentaPetroleoN1NumeralInicial"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN1NumeralFinal", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN1NumeralFinal = :controlVentaPetroleoN1NumeralFinal"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN1StockFinal", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN1StockFinal = :controlVentaPetroleoN1StockFinal"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN1Carga", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN1Carga = :controlVentaPetroleoN1Carga"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN2StockInicial", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN2StockInicial = :controlVentaPetroleoN2StockInicial"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN2NumeralInicial", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN2NumeralInicial = :controlVentaPetroleoN2NumeralInicial"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN2NumeralFinal", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN2NumeralFinal = :controlVentaPetroleoN2NumeralFinal"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN2Carga", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN2Carga = :controlVentaPetroleoN2Carga"),
    @NamedQuery(name = "ControlVentaPetroleo.findByControlVentaPetroleoN2StockFinal", query = "SELECT c FROM ControlVentaPetroleo c WHERE c.controlVentaPetroleoN2StockFinal = :controlVentaPetroleoN2StockFinal")})
public class ControlVentaPetroleo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_venta_petroleo_id")
    private Integer controlVentaPetroleoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_fecha")
    @Temporal(TemporalType.DATE)
    private Date controlVentaPetroleoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n1_stock_inicial")
    private float controlVentaPetroleoN1StockInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n1_numeral_inicial")
    private float controlVentaPetroleoN1NumeralInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n1_numeral_final")
    private float controlVentaPetroleoN1NumeralFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n1_stock_final")
    private float controlVentaPetroleoN1StockFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n1_carga")
    private float controlVentaPetroleoN1Carga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n2_stock_inicial")
    private float controlVentaPetroleoN2StockInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n2_numeral_inicial")
    private float controlVentaPetroleoN2NumeralInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n2_numeral_final")
    private float controlVentaPetroleoN2NumeralFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n2_carga")
    private float controlVentaPetroleoN2Carga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_venta_petroleo_n2_stock_final")
    private float controlVentaPetroleoN2StockFinal;
    @JoinColumn(name = "control_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta controlIdCuenta;

    public ControlVentaPetroleo() {
    }

    public ControlVentaPetroleo(Integer controlVentaPetroleoId) {
        this.controlVentaPetroleoId = controlVentaPetroleoId;
    }

    public ControlVentaPetroleo(Integer controlVentaPetroleoId, Date controlVentaPetroleoFecha, float controlVentaPetroleoN1StockInicial, float controlVentaPetroleoN1NumeralInicial, float controlVentaPetroleoN1NumeralFinal, float controlVentaPetroleoN1StockFinal, float controlVentaPetroleoN1Carga, float controlVentaPetroleoN2StockInicial, float controlVentaPetroleoN2NumeralInicial, float controlVentaPetroleoN2NumeralFinal, float controlVentaPetroleoN2Carga, float controlVentaPetroleoN2StockFinal) {
        this.controlVentaPetroleoId = controlVentaPetroleoId;
        this.controlVentaPetroleoFecha = controlVentaPetroleoFecha;
        this.controlVentaPetroleoN1StockInicial = controlVentaPetroleoN1StockInicial;
        this.controlVentaPetroleoN1NumeralInicial = controlVentaPetroleoN1NumeralInicial;
        this.controlVentaPetroleoN1NumeralFinal = controlVentaPetroleoN1NumeralFinal;
        this.controlVentaPetroleoN1StockFinal = controlVentaPetroleoN1StockFinal;
        this.controlVentaPetroleoN1Carga = controlVentaPetroleoN1Carga;
        this.controlVentaPetroleoN2StockInicial = controlVentaPetroleoN2StockInicial;
        this.controlVentaPetroleoN2NumeralInicial = controlVentaPetroleoN2NumeralInicial;
        this.controlVentaPetroleoN2NumeralFinal = controlVentaPetroleoN2NumeralFinal;
        this.controlVentaPetroleoN2Carga = controlVentaPetroleoN2Carga;
        this.controlVentaPetroleoN2StockFinal = controlVentaPetroleoN2StockFinal;
    }

    public Integer getControlVentaPetroleoId() {
        return controlVentaPetroleoId;
    }

    public void setControlVentaPetroleoId(Integer controlVentaPetroleoId) {
        this.controlVentaPetroleoId = controlVentaPetroleoId;
    }

    public Date getControlVentaPetroleoFecha() {
        return controlVentaPetroleoFecha;
    }

    public void setControlVentaPetroleoFecha(Date controlVentaPetroleoFecha) {
        this.controlVentaPetroleoFecha = controlVentaPetroleoFecha;
    }

    public float getControlVentaPetroleoN1StockInicial() {
        return controlVentaPetroleoN1StockInicial;
    }

    public void setControlVentaPetroleoN1StockInicial(float controlVentaPetroleoN1StockInicial) {
        this.controlVentaPetroleoN1StockInicial = controlVentaPetroleoN1StockInicial;
    }

    public float getControlVentaPetroleoN1NumeralInicial() {
        return controlVentaPetroleoN1NumeralInicial;
    }

    public void setControlVentaPetroleoN1NumeralInicial(float controlVentaPetroleoN1NumeralInicial) {
        this.controlVentaPetroleoN1NumeralInicial = controlVentaPetroleoN1NumeralInicial;
    }

    public float getControlVentaPetroleoN1NumeralFinal() {
        return controlVentaPetroleoN1NumeralFinal;
    }

    public void setControlVentaPetroleoN1NumeralFinal(float controlVentaPetroleoN1NumeralFinal) {
        this.controlVentaPetroleoN1NumeralFinal = controlVentaPetroleoN1NumeralFinal;
    }

    public float getControlVentaPetroleoN1StockFinal() {
        return controlVentaPetroleoN1StockFinal;
    }

    public void setControlVentaPetroleoN1StockFinal(float controlVentaPetroleoN1StockFinal) {
        this.controlVentaPetroleoN1StockFinal = controlVentaPetroleoN1StockFinal;
    }

    public float getControlVentaPetroleoN1Carga() {
        return controlVentaPetroleoN1Carga;
    }

    public void setControlVentaPetroleoN1Carga(float controlVentaPetroleoN1Carga) {
        this.controlVentaPetroleoN1Carga = controlVentaPetroleoN1Carga;
    }

    public float getControlVentaPetroleoN2StockInicial() {
        return controlVentaPetroleoN2StockInicial;
    }

    public void setControlVentaPetroleoN2StockInicial(float controlVentaPetroleoN2StockInicial) {
        this.controlVentaPetroleoN2StockInicial = controlVentaPetroleoN2StockInicial;
    }

    public float getControlVentaPetroleoN2NumeralInicial() {
        return controlVentaPetroleoN2NumeralInicial;
    }

    public void setControlVentaPetroleoN2NumeralInicial(float controlVentaPetroleoN2NumeralInicial) {
        this.controlVentaPetroleoN2NumeralInicial = controlVentaPetroleoN2NumeralInicial;
    }

    public float getControlVentaPetroleoN2NumeralFinal() {
        return controlVentaPetroleoN2NumeralFinal;
    }

    public void setControlVentaPetroleoN2NumeralFinal(float controlVentaPetroleoN2NumeralFinal) {
        this.controlVentaPetroleoN2NumeralFinal = controlVentaPetroleoN2NumeralFinal;
    }

    public float getControlVentaPetroleoN2Carga() {
        return controlVentaPetroleoN2Carga;
    }

    public void setControlVentaPetroleoN2Carga(float controlVentaPetroleoN2Carga) {
        this.controlVentaPetroleoN2Carga = controlVentaPetroleoN2Carga;
    }

    public float getControlVentaPetroleoN2StockFinal() {
        return controlVentaPetroleoN2StockFinal;
    }

    public void setControlVentaPetroleoN2StockFinal(float controlVentaPetroleoN2StockFinal) {
        this.controlVentaPetroleoN2StockFinal = controlVentaPetroleoN2StockFinal;
    }

    public Cuenta getControlIdCuenta() {
        return controlIdCuenta;
    }

    public void setControlIdCuenta(Cuenta controlIdCuenta) {
        this.controlIdCuenta = controlIdCuenta;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlVentaPetroleoId != null ? controlVentaPetroleoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlVentaPetroleo)) {
            return false;
        }
        ControlVentaPetroleo other = (ControlVentaPetroleo) object;
        if ((this.controlVentaPetroleoId == null && other.controlVentaPetroleoId != null) || (this.controlVentaPetroleoId != null && !this.controlVentaPetroleoId.equals(other.controlVentaPetroleoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ControlVentaPetroleo[ controlVentaPetroleoId=" + controlVentaPetroleoId + " ]";
    }
    
}
