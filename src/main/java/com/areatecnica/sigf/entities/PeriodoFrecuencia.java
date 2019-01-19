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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "periodo_frecuencia", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoFrecuencia.findAll", query = "SELECT p FROM PeriodoFrecuencia p")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaId", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaId = :periodoFrecuenciaId")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaDesde", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaDesde = :periodoFrecuenciaDesde")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaHasta", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaHasta = :periodoFrecuenciaHasta")})
public class PeriodoFrecuencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "periodo_frecuencia_id", nullable = false)
    private Integer periodoFrecuenciaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_frecuencia_desde", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date periodoFrecuenciaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_frecuencia_hasta", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date periodoFrecuenciaHasta;
    @JoinColumn(name = "periodo_frecuencia_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta periodoFrecuenciaIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frecuenciaServicioIdPeriodo")
    private List<FrecuenciaServicio> frecuenciaServicioList;

    public PeriodoFrecuencia() {
    }

    public PeriodoFrecuencia(Integer periodoFrecuenciaId) {
        this.periodoFrecuenciaId = periodoFrecuenciaId;
    }

    public PeriodoFrecuencia(Integer periodoFrecuenciaId, Date periodoFrecuenciaDesde, Date periodoFrecuenciaHasta) {
        this.periodoFrecuenciaId = periodoFrecuenciaId;
        this.periodoFrecuenciaDesde = periodoFrecuenciaDesde;
        this.periodoFrecuenciaHasta = periodoFrecuenciaHasta;
    }

    public Integer getPeriodoFrecuenciaId() {
        return periodoFrecuenciaId;
    }

    public void setPeriodoFrecuenciaId(Integer periodoFrecuenciaId) {
        this.periodoFrecuenciaId = periodoFrecuenciaId;
    }

    public Date getPeriodoFrecuenciaDesde() {
        return periodoFrecuenciaDesde;
    }

    public void setPeriodoFrecuenciaDesde(Date periodoFrecuenciaDesde) {
        this.periodoFrecuenciaDesde = periodoFrecuenciaDesde;
    }

    public Date getPeriodoFrecuenciaHasta() {
        return periodoFrecuenciaHasta;
    }

    public void setPeriodoFrecuenciaHasta(Date periodoFrecuenciaHasta) {
        this.periodoFrecuenciaHasta = periodoFrecuenciaHasta;
    }

    public Cuenta getPeriodoFrecuenciaIdCuenta() {
        return periodoFrecuenciaIdCuenta;
    }

    public void setPeriodoFrecuenciaIdCuenta(Cuenta periodoFrecuenciaIdCuenta) {
        this.periodoFrecuenciaIdCuenta = periodoFrecuenciaIdCuenta;
    }

    @XmlTransient
    public List<FrecuenciaServicio> getFrecuenciaServicioList() {
        return frecuenciaServicioList;
    }

    public void setFrecuenciaServicioList(List<FrecuenciaServicio> frecuenciaServicioList) {
        this.frecuenciaServicioList = frecuenciaServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodoFrecuenciaId != null ? periodoFrecuenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoFrecuencia)) {
            return false;
        }
        PeriodoFrecuencia other = (PeriodoFrecuencia) object;
        if ((this.periodoFrecuenciaId == null && other.periodoFrecuenciaId != null) || (this.periodoFrecuenciaId != null && !this.periodoFrecuenciaId.equals(other.periodoFrecuenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PeriodoFrecuencia[ periodoFrecuenciaId=" + periodoFrecuenciaId + " ]";
    }
    
}
