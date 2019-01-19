/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "cargo_bus", catalog = "sigfdb", schema = "")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "CargoBus.findAll", query = "SELECT c FROM CargoBus c")
    , @NamedQuery(name = "CargoBus.findLast", query = "SELECT c FROM CargoBus c ORDER BY c.cargoBusId DESC")
    , @NamedQuery(name = "CargoBus.findByTipoCargoDate", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdTipo = :cargoBusIdTipo AND c.cargoBusFechaInicio = :cargoBusFechaInicio")
    , @NamedQuery(name = "CargoBus.findByTipoCargoBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdTipo = :cargoBusIdTipo AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByCargoBusId", query = "SELECT c FROM CargoBus c WHERE c.cargoBusId = :cargoBusId")
    , @NamedQuery(name = "CargoBus.findByBusBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus = :busId AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByFlotaBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus.busIdFlota = :busIdFlota AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByFlotaTipoCargoBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdTipo =:cargoBusIdTipo AND c.cargoBusIdBus.busIdFlota = :busIdFlota AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByProcesoRecaudacionBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus.busIdProcesoRecaudacion = :busIdProcesoRecaudacion AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByProcesoRecaudacionTipoCargoBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdTipo =:cargoBusIdTipo AND c.cargoBusIdBus.busIdProcesoRecaudacion = :busIdProcesoRecaudacion AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByTerminalBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus.busIdTerminal = :busIdTerminal AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByTerminalTipoCargoBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdTipo = :cargoBusIdTipo AND c.cargoBusIdBus.busIdTerminal = :busIdTerminal AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByEmpresaBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus.busIdEmpresa = :busIdEmpresa AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByEmpresaTipoCargoBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdTipo =:cargoBusIdTipo AND c.cargoBusIdBus.busIdEmpresa = :busIdEmpresa AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByUnidadNegocioBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus.busIdUnidadNegocio = :busIdUnidadNegocio AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByBusTipoCargoBetweenDates", query = "SELECT c FROM CargoBus c WHERE c.cargoBusIdBus = :cargoBusIdBus AND c.cargoBusIdTipo = :cargoBusIdTipo AND c.cargoBusFechaInicio BETWEEN :from AND :to ORDER BY c.cargoBusFechaInicio ASC")
    , @NamedQuery(name = "CargoBus.findByCargoBusFechaInicio", query = "SELECT c FROM CargoBus c WHERE c.cargoBusFechaInicio = :cargoBusFechaInicio")
    , @NamedQuery(name = "CargoBus.findByCargoBusFechaTermino", query = "SELECT c FROM CargoBus c WHERE c.cargoBusFechaTermino = :cargoBusFechaTermino")
    , @NamedQuery(name = "CargoBus.findByCargoBusCuotaActual", query = "SELECT c FROM CargoBus c WHERE c.cargoBusCuotaActual = :cargoBusCuotaActual")
    , @NamedQuery(name = "CargoBus.findByCargoBusTotalCuotas", query = "SELECT c FROM CargoBus c WHERE c.cargoBusTotalCuotas = :cargoBusTotalCuotas")
    , @NamedQuery(name = "CargoBus.findByCargoBusMontoFijo", query = "SELECT c FROM CargoBus c WHERE c.cargoBusMontoFijo = :cargoBusMontoFijo")
    , @NamedQuery(name = "CargoBus.findByCargoBusDescripcion", query = "SELECT c FROM CargoBus c WHERE c.cargoBusDescripcion = :cargoBusDescripcion")
    , @NamedQuery(name = "CargoBus.findByCargoBusActivo", query = "SELECT c FROM CargoBus c WHERE c.cargoBusActivo = :cargoBusActivo")

})
public class CargoBus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cargo_bus_id", nullable = false)
    private Integer cargoBusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo_bus_fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date cargoBusFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo_bus_fecha_termino", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date cargoBusFechaTermino;
    @Column(name = "cargo_bus_cuota_actual")
    private Integer cargoBusCuotaActual;
    @Column(name = "cargo_bus_total_cuotas")
    private Integer cargoBusTotalCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo_bus_monto_fijo", nullable = false)
    private int cargoBusMontoFijo;
    @Size(max = 100)
    @Column(name = "cargo_bus_descripcion", length = 100)
    private String cargoBusDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo_bus_activo", nullable = false)
    private boolean cargoBusActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoLiquidacionIdCargo")
    private List<CargoLiquidacion> cargoLiquidacionList;
    @JoinColumn(name = "cargo_bus_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus cargoBusIdBus;
    @JoinColumn(name = "cargo_bus_id_tipo", referencedColumnName = "tipo_cargo_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoCargo cargoBusIdTipo;

    public CargoBus() {
    }

    public CargoBus(Integer cargoBusId) {
        this.cargoBusId = cargoBusId;
    }

    public CargoBus(Integer cargoBusId, Date cargoBusFechaInicio, Date cargoBusFechaTermino, int cargoBusMontoFijo, boolean cargoBusActivo) {
        this.cargoBusId = cargoBusId;
        this.cargoBusFechaInicio = cargoBusFechaInicio;
        this.cargoBusFechaTermino = cargoBusFechaTermino;
        this.cargoBusMontoFijo = cargoBusMontoFijo;
        this.cargoBusActivo = cargoBusActivo;
    }

    public Integer getCargoBusId() {
        return cargoBusId;
    }

    public void setCargoBusId(Integer cargoBusId) {
        this.cargoBusId = cargoBusId;
    }

    public Date getCargoBusFechaInicio() {
        return cargoBusFechaInicio;
    }

    public void setCargoBusFechaInicio(Date cargoBusFechaInicio) {
        this.cargoBusFechaInicio = cargoBusFechaInicio;
    }

    public Date getCargoBusFechaTermino() {
        return cargoBusFechaTermino;
    }

    public void setCargoBusFechaTermino(Date cargoBusFechaTermino) {
        this.cargoBusFechaTermino = cargoBusFechaTermino;
    }

    public Integer getCargoBusCuotaActual() {
        return cargoBusCuotaActual;
    }

    public void setCargoBusCuotaActual(Integer cargoBusCuotaActual) {
        this.cargoBusCuotaActual = cargoBusCuotaActual;
    }

    public Integer getCargoBusTotalCuotas() {
        return cargoBusTotalCuotas;
    }

    public void setCargoBusTotalCuotas(Integer cargoBusTotalCuotas) {
        this.cargoBusTotalCuotas = cargoBusTotalCuotas;
    }

    public int getCargoBusMontoFijo() {
        return cargoBusMontoFijo;
    }

    public void setCargoBusMontoFijo(int cargoBusMontoFijo) {
        this.cargoBusMontoFijo = cargoBusMontoFijo;
    }

    public String getCargoBusDescripcion() {
        return cargoBusDescripcion;
    }

    public void setCargoBusDescripcion(String cargoBusDescripcion) {
        this.cargoBusDescripcion = cargoBusDescripcion;
    }

    public boolean getCargoBusActivo() {
        return cargoBusActivo;
    }

    public void setCargoBusActivo(boolean cargoBusActivo) {
        this.cargoBusActivo = cargoBusActivo;
    }

    @XmlTransient
    public List<CargoLiquidacion> getCargoLiquidacionList() {
        return cargoLiquidacionList;
    }

    public void setCargoLiquidacionList(List<CargoLiquidacion> cargoLiquidacionList) {
        this.cargoLiquidacionList = cargoLiquidacionList;
    }

    public Bus getCargoBusIdBus() {
        return cargoBusIdBus;
    }

    public void setCargoBusIdBus(Bus cargoBusIdBus) {
        this.cargoBusIdBus = cargoBusIdBus;
    }

    public TipoCargo getCargoBusIdTipo() {
        return cargoBusIdTipo;
    }

    public void setCargoBusIdTipo(TipoCargo cargoBusIdTipo) {
        this.cargoBusIdTipo = cargoBusIdTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoBusId != null ? cargoBusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoBus)) {
            return false;
        }
        CargoBus other = (CargoBus) object;
        if ((this.cargoBusId == null && other.cargoBusId != null) || (this.cargoBusId != null && !this.cargoBusId.equals(other.cargoBusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CargoBus[ cargoBusId=" + cargoBusId + " ]";
    }

}
