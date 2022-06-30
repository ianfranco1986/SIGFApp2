/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
@Table(name = "cargo_liquidacion")
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "CargoLiquidacion.findAll", query = "SELECT c FROM CargoLiquidacion c"),
    @NamedQuery(name = "CargoLiquidacion.findByEmpresaBetweenDates", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaIdEmpresa = :empresaId AND c.cargoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaFechaLiquidacion BETWEEN :from AND :to"),
    @NamedQuery(name = "CargoLiquidacion.findByEmpresaTipoCargoBetweenDates", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaIdEmpresa = :empresaId AND c.cargoLiquidacionCargoId = :cargoLiquidacionCargoId AND c.cargoLiquidacionLiquidacionEmpresaId.liquidacionEmpresaFechaLiquidacion BETWEEN :from AND :to"),
    @NamedQuery(name = "CargoLiquidacion.findByCargoLiquidacionId", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionId = :cargoLiquidacionId"),
    @NamedQuery(name = "CargoLiquidacion.findByCargoLiquidacionMonto", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionMonto = :cargoLiquidacionMonto"),
    @NamedQuery(name = "CargoLiquidacion.findByCargoLiquidacionDescripcion", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionDescripcion = :cargoLiquidacionDescripcion")})
public class CargoLiquidacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cargo_liquidacion_id")
    private Integer cargoLiquidacionId;
    @Basic(optional = false)
    @Column(name = "cargo_liquidacion_monto")
    private int cargoLiquidacionMonto;
    @Basic(optional = false)
    @Column(name = "cargo_liquidacion_descripcion")
    private String cargoLiquidacionDescripcion;
    @JoinColumn(name = "cargo_liquidacion_liquidacion_empresa_id", referencedColumnName = "liquidacion_empresa_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LiquidacionEmpresa cargoLiquidacionLiquidacionEmpresaId;
    @JoinColumn(name = "cargo_liquidacion_cargo_id", referencedColumnName = "tipo_cargo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCargo cargoLiquidacionCargoId;

    public CargoLiquidacion() {
    }

    public CargoLiquidacion(LiquidacionEmpresa liquidacion) {
        this.cargoLiquidacionLiquidacionEmpresaId = liquidacion;
        this.cargoLiquidacionDescripcion = "";
        this.cargoLiquidacionMonto = 0; 
    }

    public CargoLiquidacion(Integer cargoLiquidacionId) {
        this.cargoLiquidacionId = cargoLiquidacionId;
    }

    public CargoLiquidacion(Integer cargoLiquidacionId, int cargoLiquidacionMonto, String cargoLiquidacionDescripcion) {
        this.cargoLiquidacionId = cargoLiquidacionId;
        this.cargoLiquidacionMonto = cargoLiquidacionMonto;
        this.cargoLiquidacionDescripcion = cargoLiquidacionDescripcion;
    }

    public Integer getCargoLiquidacionId() {
        return cargoLiquidacionId;
    }

    public void setCargoLiquidacionId(Integer cargoLiquidacionId) {
        this.cargoLiquidacionId = cargoLiquidacionId;
    }

    public int getCargoLiquidacionMonto() {
        return cargoLiquidacionMonto;
    }

    public void setCargoLiquidacionMonto(int cargoLiquidacionMonto) {
        this.cargoLiquidacionMonto = cargoLiquidacionMonto;
    }

    public String getCargoLiquidacionDescripcion() {
        return cargoLiquidacionDescripcion;
    }

    public void setCargoLiquidacionDescripcion(String cargoLiquidacionDescripcion) {
        this.cargoLiquidacionDescripcion = cargoLiquidacionDescripcion;
    }

    public LiquidacionEmpresa getCargoLiquidacionLiquidacionEmpresaId() {
        return cargoLiquidacionLiquidacionEmpresaId;
    }

    public void setCargoLiquidacionLiquidacionEmpresaId(LiquidacionEmpresa cargoLiquidacionLiquidacionEmpresaId) {
        this.cargoLiquidacionLiquidacionEmpresaId = cargoLiquidacionLiquidacionEmpresaId;
    }

    public TipoCargo getCargoLiquidacionCargoId() {
        return cargoLiquidacionCargoId;
    }

    public void setCargoLiquidacionCargoId(TipoCargo cargoLiquidacionCargoId) {
        this.cargoLiquidacionCargoId = cargoLiquidacionCargoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoLiquidacionId != null ? cargoLiquidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoLiquidacion)) {
            return false;
        }
        CargoLiquidacion other = (CargoLiquidacion) object;
        if ((this.cargoLiquidacionId == null && other.cargoLiquidacionId != null) || (this.cargoLiquidacionId != null && !this.cargoLiquidacionId.equals(other.cargoLiquidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CargoLiquidacion[ cargoLiquidacionId=" + cargoLiquidacionId + " ]";
    }

}
