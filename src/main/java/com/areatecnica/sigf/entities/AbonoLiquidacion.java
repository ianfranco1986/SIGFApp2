/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
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

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "abono_liquidacion", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "AbonoLiquidacion.findAll", query = "SELECT a FROM AbonoLiquidacion a"),
    @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionId", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionId = :abonoLiquidacionId"),
    @NamedQuery(name = "AbonoLiquidacion.findByEmpresaBetweenDates", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaIdEmpresa = :empresaId AND a.abonoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaFechaLiquidacion BETWEEN :from AND :to"),
    @NamedQuery(name = "AbonoLiquidacion.findByEmpresaTipoAbonoBetweenDates", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaIdEmpresa = :empresaId AND a.abonoLiquidacionTipoId =:abonoLiquidacionTipoId AND a.abonoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaFechaLiquidacion BETWEEN :from AND :to"),
    @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionMonto", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionMonto = :abonoLiquidacionMonto"),
    @NamedQuery(name = "AbonoLiquidacion.findByAbonoLiquidacionDescripcion", query = "SELECT a FROM AbonoLiquidacion a WHERE a.abonoLiquidacionDescripcion = :abonoLiquidacionDescripcion")})
public class AbonoLiquidacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "abono_liquidacion_id")
    private Integer abonoLiquidacionId;
    @Basic(optional = false)
    @Column(name = "abono_liquidacion_monto")
    private int abonoLiquidacionMonto;
    @Basic(optional = false)
    @Column(name = "abono_liquidacion_descripcion")
    private String abonoLiquidacionDescripcion;
    @JoinColumn(name = "abono_liquidacion_liquidacion_empresa_id", referencedColumnName = "liquidacion_empresa_id")
    @ManyToOne(optional = false)
    private LiquidacionEmpresa abonoLiquidacionLiquidacionEmpresaId;
    @JoinColumn(name = "abono_liquidacion_tipo_id", referencedColumnName = "tipo_abono_id")
    @ManyToOne(optional = false)
    private TipoAbono abonoLiquidacionTipoId;

    public AbonoLiquidacion() {
    }

    public AbonoLiquidacion(LiquidacionEmpresa liquidacion) {
        this.abonoLiquidacionLiquidacionEmpresaId = liquidacion;
        this.abonoLiquidacionDescripcion = "";
        this.abonoLiquidacionMonto = 0;
    }

    public AbonoLiquidacion(Integer abonoLiquidacionId) {
        this.abonoLiquidacionId = abonoLiquidacionId;
    }

    public AbonoLiquidacion(Integer abonoLiquidacionId, int abonoLiquidacionMonto, String abonoLiquidacionDescripcion) {
        this.abonoLiquidacionId = abonoLiquidacionId;
        this.abonoLiquidacionMonto = abonoLiquidacionMonto;
        this.abonoLiquidacionDescripcion = abonoLiquidacionDescripcion;
    }

    public Integer getAbonoLiquidacionId() {
        return abonoLiquidacionId;
    }

    public void setAbonoLiquidacionId(Integer abonoLiquidacionId) {
        this.abonoLiquidacionId = abonoLiquidacionId;
    }

    public int getAbonoLiquidacionMonto() {
        return abonoLiquidacionMonto;
    }

    public void setAbonoLiquidacionMonto(int abonoLiquidacionMonto) {
        this.abonoLiquidacionMonto = abonoLiquidacionMonto;
    }

    public String getAbonoLiquidacionDescripcion() {
        return abonoLiquidacionDescripcion;
    }

    public void setAbonoLiquidacionDescripcion(String abonoLiquidacionDescripcion) {
        this.abonoLiquidacionDescripcion = abonoLiquidacionDescripcion;
    }

    public LiquidacionEmpresa getAbonoLiquidacionLiquidacionEmpresaId() {
        return abonoLiquidacionLiquidacionEmpresaId;
    }

    public void setAbonoLiquidacionLiquidacionEmpresaId(LiquidacionEmpresa abonoLiquidacionLiquidacionEmpresaId) {
        this.abonoLiquidacionLiquidacionEmpresaId = abonoLiquidacionLiquidacionEmpresaId;
    }

    public TipoAbono getAbonoLiquidacionTipoId() {
        return abonoLiquidacionTipoId;
    }

    public void setAbonoLiquidacionTipoId(TipoAbono abonoLiquidacionTipoId) {
        this.abonoLiquidacionTipoId = abonoLiquidacionTipoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (abonoLiquidacionId != null ? abonoLiquidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoLiquidacion)) {
            return false;
        }
        AbonoLiquidacion other = (AbonoLiquidacion) object;
        if ((this.abonoLiquidacionId == null && other.abonoLiquidacionId != null) || (this.abonoLiquidacionId != null && !this.abonoLiquidacionId.equals(other.abonoLiquidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AbonoLiquidacion[ abonoLiquidacionId=" + abonoLiquidacionId + " ]";
    }

}
