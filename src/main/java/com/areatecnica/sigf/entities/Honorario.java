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
@Table(name = "honorario", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Honorario.findAll", query = "SELECT h FROM Honorario h"),
    @NamedQuery(name = "Honorario.findByHonorarioId", query = "SELECT h FROM Honorario h WHERE h.honorarioId = :honorarioId"),
    @NamedQuery(name = "Honorario.findByHonorarioFolio", query = "SELECT h FROM Honorario h WHERE h.honorarioFolio = :honorarioFolio"),
    @NamedQuery(name = "Honorario.findByHonorarioFecha", query = "SELECT h FROM Honorario h WHERE h.honorarioFecha = :honorarioFecha"),
    @NamedQuery(name = "Honorario.findByHonorarioDetalle", query = "SELECT h FROM Honorario h WHERE h.honorarioDetalle = :honorarioDetalle"),
    @NamedQuery(name = "Honorario.findByHonorarioTieneRetencion", query = "SELECT h FROM Honorario h WHERE h.honorarioTieneRetencion = :honorarioTieneRetencion"),
    @NamedQuery(name = "Honorario.findByHonorarioTotal", query = "SELECT h FROM Honorario h WHERE h.honorarioTotal = :honorarioTotal"),
    @NamedQuery(name = "Honorario.findByHonorarioRetencion", query = "SELECT h FROM Honorario h WHERE h.honorarioRetencion = :honorarioRetencion"),
    @NamedQuery(name = "Honorario.findByHonorarioLiquido", query = "SELECT h FROM Honorario h WHERE h.honorarioLiquido = :honorarioLiquido")})
public class Honorario extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "honorario_id")
    private Integer honorarioId;
    @Column(name = "honorario_folio")
    private Integer honorarioFolio;
    @Column(name = "honorario_fecha")
    @Temporal(TemporalType.DATE)
    private Date honorarioFecha;
    @Size(max = 200)
    @Column(name = "honorario_detalle")
    private String honorarioDetalle;
    @Column(name = "honorario_tiene_retencion")
    private Short honorarioTieneRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "honorario_total")
    private int honorarioTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "honorario_retencion")
    private int honorarioRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "honorario_liquido")
    private int honorarioLiquido;
    @JoinColumn(name = "honorario_cuenta_mayor_id", referencedColumnName = "cuenta_mayor_id")
    @ManyToOne(optional = false)
    private CuentaMayor honorarioCuentaMayorId;
    @JoinColumn(name = "honorario_emisor_id", referencedColumnName = "emisor_boleta_id")
    @ManyToOne(optional = false)
    private EmisorBoleta honorarioEmisorId;
    @JoinColumn(name = "honorario_movimiento_id", referencedColumnName = "movimiento_mes_id")
    @ManyToOne(optional = false)
    private MovimientoMes honorarioMovimientoId;
    @JoinColumn(name = "honorario_tipo_boleta_id", referencedColumnName = "tipo_boleta_honorario_id")
    @ManyToOne(optional = false)
    private TipoBoletaHonorario honorarioTipoBoletaId;

    public Honorario() {
    }

    public Honorario(Integer honorarioId) {
        this.honorarioId = honorarioId;
    }

    public Honorario(Integer honorarioId, int honorarioTotal, int honorarioRetencion, int honorarioLiquido) {
        this.honorarioId = honorarioId;
        this.honorarioTotal = honorarioTotal;
        this.honorarioRetencion = honorarioRetencion;
        this.honorarioLiquido = honorarioLiquido;
    }

    public Integer getHonorarioId() {
        return honorarioId;
    }

    public void setHonorarioId(Integer honorarioId) {
        this.honorarioId = honorarioId;
    }

    public Integer getHonorarioFolio() {
        return honorarioFolio;
    }

    public void setHonorarioFolio(Integer honorarioFolio) {
        this.honorarioFolio = honorarioFolio;
    }

    public Date getHonorarioFecha() {
        return honorarioFecha;
    }

    public void setHonorarioFecha(Date honorarioFecha) {
        this.honorarioFecha = honorarioFecha;
    }

    public String getHonorarioDetalle() {
        return honorarioDetalle;
    }

    public void setHonorarioDetalle(String honorarioDetalle) {
        this.honorarioDetalle = honorarioDetalle;
    }

    public Short getHonorarioTieneRetencion() {
        return honorarioTieneRetencion;
    }

    public void setHonorarioTieneRetencion(Short honorarioTieneRetencion) {
        this.honorarioTieneRetencion = honorarioTieneRetencion;
    }

    public int getHonorarioTotal() {
        return honorarioTotal;
    }

    public void setHonorarioTotal(int honorarioTotal) {
        this.honorarioTotal = honorarioTotal;
    }

    public int getHonorarioRetencion() {
        return honorarioRetencion;
    }

    public void setHonorarioRetencion(int honorarioRetencion) {
        this.honorarioRetencion = honorarioRetencion;
    }

    public int getHonorarioLiquido() {
        return honorarioLiquido;
    }

    public void setHonorarioLiquido(int honorarioLiquido) {
        this.honorarioLiquido = honorarioLiquido;
    }

    public CuentaMayor getHonorarioCuentaMayorId() {
        return honorarioCuentaMayorId;
    }

    public void setHonorarioCuentaMayorId(CuentaMayor honorarioCuentaMayorId) {
        this.honorarioCuentaMayorId = honorarioCuentaMayorId;
    }

    public EmisorBoleta getHonorarioEmisorId() {
        return honorarioEmisorId;
    }

    public void setHonorarioEmisorId(EmisorBoleta honorarioEmisorId) {
        this.honorarioEmisorId = honorarioEmisorId;
    }

    public MovimientoMes getHonorarioMovimientoId() {
        return honorarioMovimientoId;
    }

    public void setHonorarioMovimientoId(MovimientoMes honorarioMovimientoId) {
        this.honorarioMovimientoId = honorarioMovimientoId;
    }

    public TipoBoletaHonorario getHonorarioTipoBoletaId() {
        return honorarioTipoBoletaId;
    }

    public void setHonorarioTipoBoletaId(TipoBoletaHonorario honorarioTipoBoletaId) {
        this.honorarioTipoBoletaId = honorarioTipoBoletaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (honorarioId != null ? honorarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Honorario)) {
            return false;
        }
        Honorario other = (Honorario) object;
        if ((this.honorarioId == null && other.honorarioId != null) || (this.honorarioId != null && !this.honorarioId.equals(other.honorarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Honorario[ honorarioId=" + honorarioId + " ]";
    }
    
}
