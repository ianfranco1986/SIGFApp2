/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_deposito_recaudacion", catalog = "sigfdb_baquedano", schema = "")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleDepositoRecaudacion.findAll", query = "SELECT d FROM DetalleDepositoRecaudacion d")
    , @NamedQuery(name = "DetalleDepositoRecaudacion.findByDetalleDepositoRecaudacionId", query = "SELECT d FROM DetalleDepositoRecaudacion d WHERE d.detalleDepositoRecaudacionId = :detalleDepositoRecaudacionId")
    , @NamedQuery(name = "DetalleDepositoRecaudacion.findByDetalleDepositoRecaudacionDeposito", query = "SELECT d FROM DetalleDepositoRecaudacion d WHERE d.detalleDepositoRecaudacionDeposito = :detalleDepositoRecaudacionDeposito")
    , @NamedQuery(name = "DetalleDepositoRecaudacion.findByDetalleDepositoRecaudacionTotal", query = "SELECT d FROM DetalleDepositoRecaudacion d WHERE d.detalleDepositoRecaudacionTotal = :detalleDepositoRecaudacionTotal")
    , @NamedQuery(name = "DetalleDepositoRecaudacion.findByDetalleDepositoRecaudacionDetalle", query = "SELECT d FROM DetalleDepositoRecaudacion d WHERE d.detalleDepositoRecaudacionDetalle = :detalleDepositoRecaudacionDetalle")
    , @NamedQuery(name = "DetalleDepositoRecaudacion.findByDetalleDepositoRecaudacionSello", query = "SELECT d FROM DetalleDepositoRecaudacion d WHERE d.detalleDepositoRecaudacionSello = :detalleDepositoRecaudacionSello")
    , @NamedQuery(name = "DetalleDepositoRecaudacion.findByDetalleDepositoRecaudacionNumero", query = "SELECT d FROM DetalleDepositoRecaudacion d WHERE d.detalleDepositoRecaudacionNumero = :detalleDepositoRecaudacionNumero")})
public class DetalleDepositoRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_deposito_recaudacion_id")
    private Integer detalleDepositoRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_deposito_recaudacion_deposito")
    private int detalleDepositoRecaudacionDeposito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_deposito_recaudacion_total")
    private int detalleDepositoRecaudacionTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle_deposito_recaudacion_detalle")
    private String detalleDepositoRecaudacionDetalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle_deposito_recaudacion_sello")
    private String detalleDepositoRecaudacionSello;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle_deposito_recaudacion_ctv")
    private String detalleDepositoRecaudacionCtv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_deposito_recaudacion_numero")
    private int detalleDepositoRecaudacionNumero;
    @JoinColumn(name = "detalle_deposito_recaudacion_id_cuenta", referencedColumnName = "cuenta_bancaria_id")
    @ManyToOne(optional = false)
    private CuentaBancaria detalleDepositoRecaudacionIdCuenta;
    @JoinColumn(name = "detalle_deposito_recaudacion_id_resumen", referencedColumnName = "resumen_recaudacion_id")
    @ManyToOne(optional = false)
    private ResumenRecaudacion detalleDepositoRecaudacionIdResumen;

    public DetalleDepositoRecaudacion() {
    }

    public DetalleDepositoRecaudacion(Integer detalleDepositoRecaudacionId) {
        this.detalleDepositoRecaudacionId = detalleDepositoRecaudacionId;
    }

    public DetalleDepositoRecaudacion(Integer detalleDepositoRecaudacionId, int detalleDepositoRecaudacionDeposito, String detalleDepositoRecaudacionCtv, int detalleDepositoRecaudacionTotal, String detalleDepositoRecaudacionDetalle, String detalleDepositoRecaudacionSello, int detalleDepositoRecaudacionNumero) {
        this.detalleDepositoRecaudacionId = detalleDepositoRecaudacionId;
        this.detalleDepositoRecaudacionDeposito = detalleDepositoRecaudacionDeposito;
        this.detalleDepositoRecaudacionCtv = detalleDepositoRecaudacionCtv;
        this.detalleDepositoRecaudacionTotal = detalleDepositoRecaudacionTotal;
        this.detalleDepositoRecaudacionDetalle = detalleDepositoRecaudacionDetalle;
        this.detalleDepositoRecaudacionSello = detalleDepositoRecaudacionSello;
        this.detalleDepositoRecaudacionNumero = detalleDepositoRecaudacionNumero;
    }

    public Integer getDetalleDepositoRecaudacionId() {
        return detalleDepositoRecaudacionId;
    }

    public void setDetalleDepositoRecaudacionId(Integer detalleDepositoRecaudacionId) {
        this.detalleDepositoRecaudacionId = detalleDepositoRecaudacionId;
    }

    public int getDetalleDepositoRecaudacionDeposito() {
        return detalleDepositoRecaudacionDeposito;
    }

    public void setDetalleDepositoRecaudacionDeposito(int detalleDepositoRecaudacionDeposito) {
        this.detalleDepositoRecaudacionDeposito = detalleDepositoRecaudacionDeposito;
    }

    public int getDetalleDepositoRecaudacionTotal() {
        return detalleDepositoRecaudacionTotal;
    }

    public void setDetalleDepositoRecaudacionTotal(int detalleDepositoRecaudacionTotal) {
        this.detalleDepositoRecaudacionTotal = detalleDepositoRecaudacionTotal;
    }

    public String getDetalleDepositoRecaudacionDetalle() {
        return detalleDepositoRecaudacionDetalle;
    }

    public void setDetalleDepositoRecaudacionDetalle(String detalleDepositoRecaudacionDetalle) {
        this.detalleDepositoRecaudacionDetalle = detalleDepositoRecaudacionDetalle;
    }

    public String getDetalleDepositoRecaudacionSello() {
        return detalleDepositoRecaudacionSello;
    }

    public void setDetalleDepositoRecaudacionSello(String detalleDepositoRecaudacionSello) {
        this.detalleDepositoRecaudacionSello = detalleDepositoRecaudacionSello;
    }

    public void setDetalleDepositoRecaudacionCtv(String detalleDepositoRecaudacionCtv) {
        this.detalleDepositoRecaudacionCtv = detalleDepositoRecaudacionCtv;
    }

    public String getDetalleDepositoRecaudacionCtv() {
        return detalleDepositoRecaudacionCtv;
    }

    public int getDetalleDepositoRecaudacionNumero() {
        return detalleDepositoRecaudacionNumero;
    }

    public void setDetalleDepositoRecaudacionNumero(int detalleDepositoRecaudacionNumero) {
        this.detalleDepositoRecaudacionNumero = detalleDepositoRecaudacionNumero;
    }

    public CuentaBancaria getDetalleDepositoRecaudacionIdCuenta() {
        return detalleDepositoRecaudacionIdCuenta;
    }

    public void setDetalleDepositoRecaudacionIdCuenta(CuentaBancaria detalleDepositoRecaudacionIdCuenta) {
        this.detalleDepositoRecaudacionIdCuenta = detalleDepositoRecaudacionIdCuenta;
    }

    public ResumenRecaudacion getDetalleDepositoRecaudacionIdResumen() {
        return detalleDepositoRecaudacionIdResumen;
    }

    public void setDetalleDepositoRecaudacionIdResumen(ResumenRecaudacion detalleDepositoRecaudacionIdResumen) {
        this.detalleDepositoRecaudacionIdResumen = detalleDepositoRecaudacionIdResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleDepositoRecaudacionId != null ? detalleDepositoRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleDepositoRecaudacion)) {
            return false;
        }
        DetalleDepositoRecaudacion other = (DetalleDepositoRecaudacion) object;
        if ((this.detalleDepositoRecaudacionId == null && other.detalleDepositoRecaudacionId != null) || (this.detalleDepositoRecaudacionId != null && !this.detalleDepositoRecaudacionId.equals(other.detalleDepositoRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleDepositoRecaudacion[ detalleDepositoRecaudacionId=" + detalleDepositoRecaudacionId + " ]";
    }

}
