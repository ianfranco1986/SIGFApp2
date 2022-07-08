/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "proceso_recaudacion")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "ProcesoRecaudacion.findAll", query = "SELECT p FROM ProcesoRecaudacion p"),
    @NamedQuery(name = "ProcesoRecaudacion.findByProcesoRecaudacionId", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionId = :procesoRecaudacionId"),
    @NamedQuery(name = "ProcesoRecaudacion.findByNandu", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionId IN (2, 3, 7) "),
    @NamedQuery(name = "ProcesoRecaudacion.findByCuenta", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionIdCuenta = :idCuenta"),
    @NamedQuery(name = "ProcesoRecaudacion.findByProcesoRecaudacionNombre", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionNombre = :procesoRecaudacionNombre"),
    @NamedQuery(name = "ProcesoRecaudacion.findByProcesoRecaudacionTieneCuenta", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionTieneCuenta = :procesoRecaudacionTieneCuenta"),
    @NamedQuery(name = "ProcesoRecaudacion.findByProcesoRecaudacionTieneEgresos", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionTieneEgresos = :procesoRecaudacionTieneEgresos"),
    @NamedQuery(name = "ProcesoRecaudacion.findByProcesoRecaudacionActivo", query = "SELECT p FROM ProcesoRecaudacion p WHERE p.procesoRecaudacionActivo = :procesoRecaudacionActivo")})
public class ProcesoRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proceso_recaudacion_id", nullable = false)
    private Integer procesoRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "proceso_recaudacion_nombre", nullable = false, length = 45)
    private String procesoRecaudacionNombre;
    @Column(name = "proceso_recaudacion_tiene_cuenta")
    private Short procesoRecaudacionTieneCuenta;
    @Column(name = "proceso_recaudacion_tiene_egresos")
    private Boolean procesoRecaudacionTieneEgresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proceso_recaudacion_activo", nullable = false)
    private boolean procesoRecaudacionActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancoProcesoIdProceso", fetch = FetchType.LAZY)
    private List<CuentaBancoProceso> cuentaBancoProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busIdProcesoRecaudacion", fetch = FetchType.LAZY)
    private List<Bus> busList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoProcesoRecaudacionIdProceso", fetch = FetchType.LAZY)
    private List<EgresoProcesoRecaudacion> egresoProcesoRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaProcesoIdProceso", fetch = FetchType.LAZY)
    private List<CajaProceso> cajaProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resumenRecaudacionIdProceso", fetch = FetchType.LAZY)
    private List<ResumenRecaudacion> resumenRecaudacionList;
    @JoinColumn(name = "proceso_recaudacion_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta procesoRecaudacionIdCuenta;

    public ProcesoRecaudacion() {
    }

    public ProcesoRecaudacion(Integer procesoRecaudacionId) {
        this.procesoRecaudacionId = procesoRecaudacionId;
    }

    public ProcesoRecaudacion(Integer procesoRecaudacionId, String procesoRecaudacionNombre, boolean procesoRecaudacionActivo) {
        this.procesoRecaudacionId = procesoRecaudacionId;
        this.procesoRecaudacionNombre = procesoRecaudacionNombre;
        this.procesoRecaudacionActivo = procesoRecaudacionActivo;
    }

    public Integer getProcesoRecaudacionId() {
        return procesoRecaudacionId;
    }

    public void setProcesoRecaudacionId(Integer procesoRecaudacionId) {
        this.procesoRecaudacionId = procesoRecaudacionId;
    }

    public String getProcesoRecaudacionNombre() {
        return procesoRecaudacionNombre;
    }

    public void setProcesoRecaudacionNombre(String procesoRecaudacionNombre) {
        this.procesoRecaudacionNombre = procesoRecaudacionNombre;
    }

    public Short getProcesoRecaudacionTieneCuenta() {
        return procesoRecaudacionTieneCuenta;
    }

    public void setProcesoRecaudacionTieneCuenta(Short procesoRecaudacionTieneCuenta) {
        this.procesoRecaudacionTieneCuenta = procesoRecaudacionTieneCuenta;
    }

    public Boolean getProcesoRecaudacionTieneEgresos() {
        return procesoRecaudacionTieneEgresos;
    }

    public void setProcesoRecaudacionTieneEgresos(Boolean procesoRecaudacionTieneEgresos) {
        this.procesoRecaudacionTieneEgresos = procesoRecaudacionTieneEgresos;
    }

    public boolean getProcesoRecaudacionActivo() {
        return procesoRecaudacionActivo;
    }

    public void setProcesoRecaudacionActivo(boolean procesoRecaudacionActivo) {
        this.procesoRecaudacionActivo = procesoRecaudacionActivo;
    }

    public List<ResumenRecaudacion> getResumenRecaudacionList() {
        return resumenRecaudacionList;
    }

    public void setResumenRecaudacionList(List<ResumenRecaudacion> resumenRecaudacionList) {
        this.resumenRecaudacionList = resumenRecaudacionList;
    }

    @XmlTransient
    public List<CuentaBancoProceso> getCuentaBancoProcesoList() {
        return cuentaBancoProcesoList;
    }

    public void setCuentaBancoProcesoList(List<CuentaBancoProceso> cuentaBancoProcesoList) {
        this.cuentaBancoProcesoList = cuentaBancoProcesoList;
    }

    @XmlTransient
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    @XmlTransient
    public List<EgresoProcesoRecaudacion> getEgresoProcesoRecaudacionList() {
        return egresoProcesoRecaudacionList;
    }

    public void setEgresoProcesoRecaudacionList(List<EgresoProcesoRecaudacion> egresoProcesoRecaudacionList) {
        this.egresoProcesoRecaudacionList = egresoProcesoRecaudacionList;
    }

    @XmlTransient
    public List<CajaProceso> getCajaProcesoList() {
        return cajaProcesoList;
    }

    public void setCajaProcesoList(List<CajaProceso> cajaProcesoList) {
        this.cajaProcesoList = cajaProcesoList;
    }

    public Cuenta getProcesoRecaudacionIdCuenta() {
        return procesoRecaudacionIdCuenta;
    }

    public void setProcesoRecaudacionIdCuenta(Cuenta procesoRecaudacionIdCuenta) {
        this.procesoRecaudacionIdCuenta = procesoRecaudacionIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procesoRecaudacionId != null ? procesoRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoRecaudacion)) {
            return false;
        }
        ProcesoRecaudacion other = (ProcesoRecaudacion) object;
        return (this.procesoRecaudacionId != null || other.procesoRecaudacionId == null) && (this.procesoRecaudacionId == null || this.procesoRecaudacionId.equals(other.procesoRecaudacionId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ProcesoRecaudacion[ procesoRecaudacionId=" + procesoRecaudacionId + " ]";
    }

}
