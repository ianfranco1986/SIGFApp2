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
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "licencia_medica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicenciaMedica.findAll", query = "SELECT l FROM LicenciaMedica l")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaId", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaId = :licenciaMedicaId")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaNumero", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaNumero = :licenciaMedicaNumero")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaFechaRecepcion", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaFechaRecepcion = :licenciaMedicaFechaRecepcion")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaFechaEmision", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaFechaEmision = :licenciaMedicaFechaEmision")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaFechaDesdeReposo", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaFechaDesdeReposo = :licenciaMedicaFechaDesdeReposo")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaFechaHastaReposo", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaFechaHastaReposo = :licenciaMedicaFechaHastaReposo")
    , @NamedQuery(name = "LicenciaMedica.findByLicenciaMedicaDiagnosticoLicencia", query = "SELECT l FROM LicenciaMedica l WHERE l.licenciaMedicaDiagnosticoLicencia = :licenciaMedicaDiagnosticoLicencia")})
public class LicenciaMedica extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "licencia_medica_id", nullable = false)
    private Integer licenciaMedicaId;
    @Size(max = 45)
    @Column(name = "licencia_medica_numero", length = 45)
    private String licenciaMedicaNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "licencia_medica_fecha_recepcion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date licenciaMedicaFechaRecepcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "licencia_medica_fecha_emision", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date licenciaMedicaFechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "licencia_medica_fecha_desde_reposo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date licenciaMedicaFechaDesdeReposo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "licencia_medica_fecha_hasta_reposo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date licenciaMedicaFechaHastaReposo;
    @Size(max = 100)
    @Column(name = "licencia_medica_diagnostico_licencia", length = 100)
    private String licenciaMedicaDiagnosticoLicencia;
    @JoinColumn(name = "licencia_medica_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador licenciaMedicaIdTrabajador;

    public LicenciaMedica() {
    }

    public LicenciaMedica(Integer licenciaMedicaId) {
        this.licenciaMedicaId = licenciaMedicaId;
    }

    public LicenciaMedica(Integer licenciaMedicaId, Date licenciaMedicaFechaRecepcion, Date licenciaMedicaFechaEmision, Date licenciaMedicaFechaDesdeReposo, Date licenciaMedicaFechaHastaReposo) {
        this.licenciaMedicaId = licenciaMedicaId;
        this.licenciaMedicaFechaRecepcion = licenciaMedicaFechaRecepcion;
        this.licenciaMedicaFechaEmision = licenciaMedicaFechaEmision;
        this.licenciaMedicaFechaDesdeReposo = licenciaMedicaFechaDesdeReposo;
        this.licenciaMedicaFechaHastaReposo = licenciaMedicaFechaHastaReposo;
    }

    public Integer getLicenciaMedicaId() {
        return licenciaMedicaId;
    }

    public void setLicenciaMedicaId(Integer licenciaMedicaId) {
        this.licenciaMedicaId = licenciaMedicaId;
    }

    public String getLicenciaMedicaNumero() {
        return licenciaMedicaNumero;
    }

    public void setLicenciaMedicaNumero(String licenciaMedicaNumero) {
        this.licenciaMedicaNumero = licenciaMedicaNumero;
    }

    public Date getLicenciaMedicaFechaRecepcion() {
        return licenciaMedicaFechaRecepcion;
    }

    public void setLicenciaMedicaFechaRecepcion(Date licenciaMedicaFechaRecepcion) {
        this.licenciaMedicaFechaRecepcion = licenciaMedicaFechaRecepcion;
    }

    public Date getLicenciaMedicaFechaEmision() {
        return licenciaMedicaFechaEmision;
    }

    public void setLicenciaMedicaFechaEmision(Date licenciaMedicaFechaEmision) {
        this.licenciaMedicaFechaEmision = licenciaMedicaFechaEmision;
    }

    public Date getLicenciaMedicaFechaDesdeReposo() {
        return licenciaMedicaFechaDesdeReposo;
    }

    public void setLicenciaMedicaFechaDesdeReposo(Date licenciaMedicaFechaDesdeReposo) {
        this.licenciaMedicaFechaDesdeReposo = licenciaMedicaFechaDesdeReposo;
    }

    public Date getLicenciaMedicaFechaHastaReposo() {
        return licenciaMedicaFechaHastaReposo;
    }

    public void setLicenciaMedicaFechaHastaReposo(Date licenciaMedicaFechaHastaReposo) {
        this.licenciaMedicaFechaHastaReposo = licenciaMedicaFechaHastaReposo;
    }

    public String getLicenciaMedicaDiagnosticoLicencia() {
        return licenciaMedicaDiagnosticoLicencia;
    }

    public void setLicenciaMedicaDiagnosticoLicencia(String licenciaMedicaDiagnosticoLicencia) {
        this.licenciaMedicaDiagnosticoLicencia = licenciaMedicaDiagnosticoLicencia;
    }

    public Trabajador getLicenciaMedicaIdTrabajador() {
        return licenciaMedicaIdTrabajador;
    }

    public void setLicenciaMedicaIdTrabajador(Trabajador licenciaMedicaIdTrabajador) {
        this.licenciaMedicaIdTrabajador = licenciaMedicaIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (licenciaMedicaId != null ? licenciaMedicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenciaMedica)) {
            return false;
        }
        LicenciaMedica other = (LicenciaMedica) object;
        return (this.licenciaMedicaId != null || other.licenciaMedicaId == null) && (this.licenciaMedicaId == null || this.licenciaMedicaId.equals(other.licenciaMedicaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.LicenciaMedica[ licenciaMedicaId=" + licenciaMedicaId + " ]";
    }
    
}
