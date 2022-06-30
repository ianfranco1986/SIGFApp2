/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "bus")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b WHERE b.busNumero>1 ORDER BY b.busIdUnidadNegocio.unidadNegocioNumero DESC, b.busNumero ASC")
    , @NamedQuery(name = "Bus.findByBusUnidadNegocio", query = "SELECT b FROM Bus b WHERE b.busIdUnidadNegocio = :busIdUnidadNegocio AND b.busIdFlota = :busIdFlota ORDER BY b.busIdUnidadNegocio.unidadNegocioNumero DESC, b.busNumero ASC")
    , @NamedQuery(name = "Bus.findByProcesoRecaudacion", query = "SELECT b FROM Bus b WHERE b.busIdProcesoRecaudacion = :busIdProcesoRecaudacion ORDER BY b.busNumero ASC")
    , @NamedQuery(name = "Bus.findByFlota", query = "SELECT b FROM Bus b WHERE b.busIdFlota = :busIdFlota ORDER BY b.busNumero")
    , @NamedQuery(name = "Bus.findByEmpresa", query = "SELECT b FROM Bus b WHERE b.busIdEmpresa = :busIdEmpresa AND b.busActivo = true ORDER BY b.busNumero")
    , @NamedQuery(name = "Bus.findByEmpresaUnidad", query = "SELECT b FROM Bus b WHERE b.busIdEmpresa = :busIdEmpresa and b.busIdUnidadNegocio = :busIdUnidadNegocio ORDER BY b.busNumero")
    , @NamedQuery(name = "Bus.findByBusIdUnidadNegocio", query = "SELECT b FROM Bus b WHERE b.busIdUnidadNegocio = :busIdUnidadNegocio ORDER BY b.busNumero ASC")
    , @NamedQuery(name = "Bus.findAllByCuenta", query = "SELECT b FROM Bus b WHERE b.busIdTerminal.terminalIdCuenta = :idCuenta ORDER BY b.busNumero ASC")
        , @NamedQuery(name = "Bus.findDefaultBus", query = "SELECT b FROM Bus b WHERE b.busIdEstadoBus = :busIdEstadoBus")
        , @NamedQuery(name = "Bus.findByGrupoServicio", query = "SELECT b FROM Bus b WHERE b.busIdGrupoServicio = :busIdGrupoServicio AND b.busActivo = true ORDER BY b.busNumero")
        , @NamedQuery(name = "Bus.findByTerminal", query = "SELECT b FROM Bus b WHERE b.busIdTerminal = :busIdTerminal AND b.busActivo = true ORDER BY b.busNumero")
        , @NamedQuery(name = "Bus.findByBusId", query = "SELECT b FROM Bus b WHERE b.busId = :busId")
    , @NamedQuery(name = "Bus.findByBusNumero", query = "SELECT b FROM Bus b WHERE b.busNumero = :busNumero")
    , @NamedQuery(name = "Bus.findByBusPatente", query = "SELECT b FROM Bus b WHERE b.busPatente = :busPatente")
    , @NamedQuery(name = "Bus.findMaxNumeroUnidad", query = "SELECT b FROM Bus b WHERE b.busIdUnidadNegocio = :busIdUnidadNegocio ORDER BY b.busNumero DESC")
    , @NamedQuery(name = "Bus.findByBusTieneAdministrador", query = "SELECT b FROM Bus b WHERE b.busTieneAdministrador = :busTieneAdministrador")
    , @NamedQuery(name = "Bus.findByBusAdministrador", query = "SELECT b FROM Bus b WHERE b.busAdministrador = :busAdministrador")
    , @NamedQuery(name = "Bus.findByBusAnio", query = "SELECT b FROM Bus b WHERE b.busAnio = :busAnio")
    , @NamedQuery(name = "Bus.findByBusFechaRevisionTecnica", query = "SELECT b FROM Bus b WHERE b.busFechaRevisionTecnica = :busFechaRevisionTecnica")
    , @NamedQuery(name = "Bus.findByBusNumeroMotor", query = "SELECT b FROM Bus b WHERE b.busNumeroMotor = :busNumeroMotor")
    , @NamedQuery(name = "Bus.findByBusNumeroChasis", query = "SELECT b FROM Bus b WHERE b.busNumeroChasis = :busNumeroChasis")
    , @NamedQuery(name = "Bus.findByBusCarroceria", query = "SELECT b FROM Bus b WHERE b.busCarroceria = :busCarroceria")
    , @NamedQuery(name = "Bus.findByBusTieneEgresoIndividual", query = "SELECT b FROM Bus b WHERE b.busTieneEgresoIndividual = :busTieneEgresoIndividual")
    , @NamedQuery(name = "Bus.findByBusTieneEgresoFlota", query = "SELECT b FROM Bus b WHERE b.busTieneEgresoFlota = :busTieneEgresoFlota")
    , @NamedQuery(name = "Bus.findByBusComparteServicio", query = "SELECT b FROM Bus b WHERE b.busComparteServicio = :busComparteServicio")
    , @NamedQuery(name = "Bus.findByBusActivo", query = "SELECT b FROM Bus b WHERE b.busActivo = :busActivo")})
public class Bus extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bus_id", nullable = false)
    private Integer busId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bus_numero", nullable = false)
    private int busNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bus_patente", nullable = false, length = 45)
    private String busPatente;
    @Column(name = "bus_tiene_administrador")
    private Boolean busTieneAdministrador;
    @Size(max = 100)
    @Column(name = "bus_administrador", length = 100)
    private String busAdministrador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bus_anio", nullable = false)
    private int busAnio;
    @Column(name = "bus_fecha_revision_tecnica")
    @Temporal(TemporalType.DATE)
    private Date busFechaRevisionTecnica;
    @Size(max = 45)
    @Column(name = "bus_numero_motor", length = 45)
    private String busNumeroMotor;
    @Size(max = 45)
    @Column(name = "bus_numero_chasis", length = 45)
    private String busNumeroChasis;
    @Size(max = 45)
    @Column(name = "bus_carroceria", length = 45)
    private String busCarroceria;
    @Column(name = "bus_tiene_egreso_individual")
    private Boolean busTieneEgresoIndividual;
    @Column(name = "bus_tiene_egreso_flota")
    private Boolean busTieneEgresoFlota;
    @Column(name = "bus_comparte_servicio")
    private Boolean busComparteServicio;
    @Column(name = "bus_activo")
    private Boolean busActivo;    
    @JoinColumn(name = "bus_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa busIdEmpresa;
    @JoinColumn(name = "bus_id_estado_bus", referencedColumnName = "estado_bus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoBus busIdEstadoBus;
    @JoinColumn(name = "bus_id_flota", referencedColumnName = "flota_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Flota busIdFlota;
    @JoinColumn(name = "bus_id_grupo_servicio", referencedColumnName = "grupo_servicio_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrupoServicio busIdGrupoServicio;
    @JoinColumn(name = "bus_id_modelo", referencedColumnName = "modelo_marca_bus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ModeloMarcaBus busIdModelo;
    @JoinColumn(name = "bus_id_proceso_recaudacion", referencedColumnName = "proceso_recaudacion_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProcesoRecaudacion busIdProcesoRecaudacion;
    @JoinColumn(name = "bus_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Terminal busIdTerminal;
    @JoinColumn(name = "bus_id_unidad_negocio", referencedColumnName = "unidad_negocio_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadNegocio busIdUnidadNegocio;
    

    public Bus() {
    }

    public Bus(Integer busId) {
        this.busId = busId;
    }

    public Bus(Integer busId, int busNumero, String busPatente, int busAnio) {
        this.busId = busId;
        this.busNumero = busNumero;
        this.busPatente = busPatente;
        this.busAnio = busAnio;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public int getBusNumero() {
        return busNumero;
    }

    public void setBusNumero(int busNumero) {
        this.busNumero = busNumero;
    }

    public String getBusPatente() {
        return busPatente;
    }

    public void setBusPatente(String busPatente) {
        this.busPatente = busPatente;
    }

    public Boolean getBusTieneAdministrador() {
        return busTieneAdministrador;
    }

    public void setBusTieneAdministrador(Boolean busTieneAdministrador) {
        this.busTieneAdministrador = busTieneAdministrador;
    }

    public String getBusAdministrador() {
        return busAdministrador;
    }

    public void setBusAdministrador(String busAdministrador) {
        this.busAdministrador = busAdministrador;
    }

    public int getBusAnio() {
        return busAnio;
    }

    public void setBusAnio(int busAnio) {
        this.busAnio = busAnio;
    }

    public Date getBusFechaRevisionTecnica() {
        return busFechaRevisionTecnica;
    }

    public void setBusFechaRevisionTecnica(Date busFechaRevisionTecnica) {
        this.busFechaRevisionTecnica = busFechaRevisionTecnica;
    }

    public String getBusNumeroMotor() {
        return busNumeroMotor;
    }

    public void setBusNumeroMotor(String busNumeroMotor) {
        this.busNumeroMotor = busNumeroMotor;
    }

    public String getBusNumeroChasis() {
        return busNumeroChasis;
    }

    public void setBusNumeroChasis(String busNumeroChasis) {
        this.busNumeroChasis = busNumeroChasis;
    }

    public String getBusCarroceria() {
        return busCarroceria;
    }

    public void setBusCarroceria(String busCarroceria) {
        this.busCarroceria = busCarroceria;
    }

    public Boolean getBusTieneEgresoIndividual() {
        return busTieneEgresoIndividual;
    }

    public void setBusTieneEgresoIndividual(Boolean busTieneEgresoIndividual) {
        this.busTieneEgresoIndividual = busTieneEgresoIndividual;
    }

    public Boolean getBusTieneEgresoFlota() {
        return busTieneEgresoFlota;
    }

    public void setBusTieneEgresoFlota(Boolean busTieneEgresoFlota) {
        this.busTieneEgresoFlota = busTieneEgresoFlota;
    }

    public Boolean getBusComparteServicio() {
        return busComparteServicio;
    }

    public void setBusComparteServicio(Boolean busComparteServicio) {
        this.busComparteServicio = busComparteServicio;
    }

    public Boolean getBusActivo() {
        return busActivo;
    }

    public void setBusActivo(Boolean busActivo) {
        this.busActivo = busActivo;
    }

    public Empresa getBusIdEmpresa() {
        return busIdEmpresa;
    }

    public void setBusIdEmpresa(Empresa busIdEmpresa) {
        this.busIdEmpresa = busIdEmpresa;
    }

    public EstadoBus getBusIdEstadoBus() {
        return busIdEstadoBus;
    }

    public void setBusIdEstadoBus(EstadoBus busIdEstadoBus) {
        this.busIdEstadoBus = busIdEstadoBus;
    }

    public Flota getBusIdFlota() {
        return busIdFlota;
    }

    public void setBusIdFlota(Flota busIdFlota) {
        this.busIdFlota = busIdFlota;
    }

    public GrupoServicio getBusIdGrupoServicio() {
        return busIdGrupoServicio;
    }

    public void setBusIdGrupoServicio(GrupoServicio busIdGrupoServicio) {
        this.busIdGrupoServicio = busIdGrupoServicio;
    }

    public ModeloMarcaBus getBusIdModelo() {
        return busIdModelo;
    }

    public void setBusIdModelo(ModeloMarcaBus busIdModelo) {
        this.busIdModelo = busIdModelo;
    }

    public ProcesoRecaudacion getBusIdProcesoRecaudacion() {
        return busIdProcesoRecaudacion;
    }

    public void setBusIdProcesoRecaudacion(ProcesoRecaudacion busIdProcesoRecaudacion) {
        this.busIdProcesoRecaudacion = busIdProcesoRecaudacion;
    }

    public Terminal getBusIdTerminal() {
        return busIdTerminal;
    }

    public void setBusIdTerminal(Terminal busIdTerminal) {
        this.busIdTerminal = busIdTerminal;
    }

    public UnidadNegocio getBusIdUnidadNegocio() {
        return busIdUnidadNegocio;
    }

    public void setBusIdUnidadNegocio(UnidadNegocio busIdUnidadNegocio) {
        this.busIdUnidadNegocio = busIdUnidadNegocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (busId != null ? busId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        return (this.busId != null || other.busId == null) && (this.busId == null || this.busId.equals(other.busId));
    }

    @Override
    public String toString() {
        return busNumero + " - " + busIdUnidadNegocio.getUnidadNegocioIdOperadorTransporte().getOperadorTransporteNombre();
    }

}
