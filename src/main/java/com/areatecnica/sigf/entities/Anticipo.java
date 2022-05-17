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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "anticipo")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anticipo.findAll", query = "SELECT a FROM Anticipo a"),
    @NamedQuery(name = "Anticipo.findByAnticipoId", query = "SELECT a FROM Anticipo a WHERE a.anticipoId = :anticipoId"),
    @NamedQuery(name = "Anticipo.findByAnticipoFecha", query = "SELECT a FROM Anticipo a WHERE a.anticipoFecha = :anticipoFecha"),
    @NamedQuery(name = "Anticipo.findByAnticipoMonto", query = "SELECT a FROM Anticipo a WHERE a.anticipoMonto = :anticipoMonto")})
public class Anticipo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "anticipo_id")
    private Integer anticipoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anticipo_fecha")
    @Temporal(TemporalType.DATE)
    private Date anticipoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anticipo_monto")
    private int anticipoMonto;
    @JoinColumn(name = "anticipo_cuenta_mayor_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false)
    private CuentaMayor anticipoCuentaMayorId;
    @JoinColumn(name = "anticipo_movimiento_id", referencedColumnName = "movimiento_mes_id")
    @ManyToOne(optional = false)
    private MovimientoMes anticipoMovimientoId;

    public Anticipo() {
    }

    public Anticipo(Integer anticipoId) {
        this.anticipoId = anticipoId;
    }

    public Anticipo(Integer anticipoId, Date anticipoFecha, int anticipoMonto) {
        this.anticipoId = anticipoId;
        this.anticipoFecha = anticipoFecha;
        this.anticipoMonto = anticipoMonto;
    }

    public Integer getAnticipoId() {
        return anticipoId;
    }

    public void setAnticipoId(Integer anticipoId) {
        this.anticipoId = anticipoId;
    }

    public Date getAnticipoFecha() {
        return anticipoFecha;
    }

    public void setAnticipoFecha(Date anticipoFecha) {
        this.anticipoFecha = anticipoFecha;
    }

    public int getAnticipoMonto() {
        return anticipoMonto;
    }

    public void setAnticipoMonto(int anticipoMonto) {
        this.anticipoMonto = anticipoMonto;
    }

    public CuentaMayor getAnticipoCuentaMayorId() {
        return anticipoCuentaMayorId;
    }

    public void setAnticipoCuentaMayorId(CuentaMayor anticipoCuentaMayorId) {
        this.anticipoCuentaMayorId = anticipoCuentaMayorId;
    }

    public MovimientoMes getAnticipoMovimientoId() {
        return anticipoMovimientoId;
    }

    public void setAnticipoMovimientoId(MovimientoMes anticipoMovimientoId) {
        this.anticipoMovimientoId = anticipoMovimientoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anticipoId != null ? anticipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anticipo)) {
            return false;
        }
        Anticipo other = (Anticipo) object;
        if ((this.anticipoId == null && other.anticipoId != null) || (this.anticipoId != null && !this.anticipoId.equals(other.anticipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Anticipo[ anticipoId=" + anticipoId + " ]";
    }

}
