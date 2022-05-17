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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "programacion_trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramacionTrabajador.findAll", query = "SELECT p FROM ProgramacionTrabajador p")
    , @NamedQuery(name = "ProgramacionTrabajador.findByProgramacionTrabajadorId", query = "SELECT p FROM ProgramacionTrabajador p WHERE p.programacionTrabajadorId = :programacionTrabajadorId")
    , @NamedQuery(name = "ProgramacionTrabajador.findByProgramacionTrabajadorDesde", query = "SELECT p FROM ProgramacionTrabajador p WHERE p.programacionTrabajadorDesde = :programacionTrabajadorDesde")
    , @NamedQuery(name = "ProgramacionTrabajador.findByProgramacionTrabajadorHasta", query = "SELECT p FROM ProgramacionTrabajador p WHERE p.programacionTrabajadorHasta = :programacionTrabajadorHasta")})
public class ProgramacionTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "programacion_trabajador_id", nullable = false)
    private Integer programacionTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "programacion_trabajador_desde", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date programacionTrabajadorDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "programacion_trabajador_hasta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date programacionTrabajadorHasta;
    @JoinColumn(name = "programacion_trabajador_id_administrador", referencedColumnName = "administrador_id", nullable = false)
    @ManyToOne(optional = false)
    private Administrador programacionTrabajadorIdAdministrador;
    @JoinColumn(name = "programacion_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador programacionTrabajadorIdTrabajador;
    @JoinColumn(name = "programacion_trabajador_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus programacionTrabajadorIdBus;

    public ProgramacionTrabajador() {
    }

    public ProgramacionTrabajador(Integer programacionTrabajadorId) {
        this.programacionTrabajadorId = programacionTrabajadorId;
    }

    public ProgramacionTrabajador(Integer programacionTrabajadorId, Date programacionTrabajadorDesde, Date programacionTrabajadorHasta) {
        this.programacionTrabajadorId = programacionTrabajadorId;
        this.programacionTrabajadorDesde = programacionTrabajadorDesde;
        this.programacionTrabajadorHasta = programacionTrabajadorHasta;
    }

    public Integer getProgramacionTrabajadorId() {
        return programacionTrabajadorId;
    }

    public void setProgramacionTrabajadorId(Integer programacionTrabajadorId) {
        this.programacionTrabajadorId = programacionTrabajadorId;
    }

    public Date getProgramacionTrabajadorDesde() {
        return programacionTrabajadorDesde;
    }

    public void setProgramacionTrabajadorDesde(Date programacionTrabajadorDesde) {
        this.programacionTrabajadorDesde = programacionTrabajadorDesde;
    }

    public Date getProgramacionTrabajadorHasta() {
        return programacionTrabajadorHasta;
    }

    public void setProgramacionTrabajadorHasta(Date programacionTrabajadorHasta) {
        this.programacionTrabajadorHasta = programacionTrabajadorHasta;
    }

    public Bus getProgramacionTrabajadorIdBus() {
        return programacionTrabajadorIdBus;
    }

    public void setProgramacionTrabajadorIdBus(Bus programacionTrabajadorIdBus) {
        this.programacionTrabajadorIdBus = programacionTrabajadorIdBus;
    }

    public Administrador getProgramacionTrabajadorIdAdministrador() {
        return programacionTrabajadorIdAdministrador;
    }

    public void setProgramacionTrabajadorIdAdministrador(Administrador programacionTrabajadorIdAdministrador) {
        this.programacionTrabajadorIdAdministrador = programacionTrabajadorIdAdministrador;
    }

    public Trabajador getProgramacionTrabajadorIdTrabajador() {
        return programacionTrabajadorIdTrabajador;
    }

    public void setProgramacionTrabajadorIdTrabajador(Trabajador programacionTrabajadorIdTrabajador) {
        this.programacionTrabajadorIdTrabajador = programacionTrabajadorIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programacionTrabajadorId != null ? programacionTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionTrabajador)) {
            return false;
        }
        ProgramacionTrabajador other = (ProgramacionTrabajador) object;
        if ((this.programacionTrabajadorId == null && other.programacionTrabajadorId != null) || (this.programacionTrabajadorId != null && !this.programacionTrabajadorId.equals(other.programacionTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ProgramacionTrabajador[ programacionTrabajadorId=" + programacionTrabajadorId + " ]";
    }
    
}
