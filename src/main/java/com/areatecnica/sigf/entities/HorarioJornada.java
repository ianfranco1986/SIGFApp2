/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "horario_jornada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorarioJornada.findAll", query = "SELECT h FROM HorarioJornada h")
    , @NamedQuery(name = "HorarioJornada.findByHorarioJornadaId", query = "SELECT h FROM HorarioJornada h WHERE h.horarioJornadaId = :horarioJornadaId")
    , @NamedQuery(name = "HorarioJornada.findByHorarioJornadaInicioHorario", query = "SELECT h FROM HorarioJornada h WHERE h.horarioJornadaInicioHorario = :horarioJornadaInicioHorario")
    , @NamedQuery(name = "HorarioJornada.findByHorarioJornadaTerminoHorario", query = "SELECT h FROM HorarioJornada h WHERE h.horarioJornadaTerminoHorario = :horarioJornadaTerminoHorario")
    , @NamedQuery(name = "HorarioJornada.findByHorarioJornadaHorarioVariable", query = "SELECT h FROM HorarioJornada h WHERE h.horarioJornadaHorarioVariable = :horarioJornadaHorarioVariable")})
public class HorarioJornada extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "horario_jornada_id", nullable = false)
    private Integer horarioJornadaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_jornada_inicio_horario", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioJornadaInicioHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_jornada_termino_horario", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioJornadaTerminoHorario;
    @Column(name = "horario_jornada_horario_variable")
    private Boolean horarioJornadaHorarioVariable;
    @JoinColumn(name = "horario_jornada_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta horarioJornadaIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornadaLaboralIdHorarioJornada")
    private List<JornadaLaboral> jornadaLaboralList;

    public HorarioJornada() {
    }

    public HorarioJornada(Integer horarioJornadaId) {
        this.horarioJornadaId = horarioJornadaId;
    }

    public HorarioJornada(Integer horarioJornadaId, Date horarioJornadaInicioHorario, Date horarioJornadaTerminoHorario) {
        this.horarioJornadaId = horarioJornadaId;
        this.horarioJornadaInicioHorario = horarioJornadaInicioHorario;
        this.horarioJornadaTerminoHorario = horarioJornadaTerminoHorario;
    }

    public Integer getHorarioJornadaId() {
        return horarioJornadaId;
    }

    public void setHorarioJornadaId(Integer horarioJornadaId) {
        this.horarioJornadaId = horarioJornadaId;
    }

    public Date getHorarioJornadaInicioHorario() {
        return horarioJornadaInicioHorario;
    }

    public void setHorarioJornadaInicioHorario(Date horarioJornadaInicioHorario) {
        this.horarioJornadaInicioHorario = horarioJornadaInicioHorario;
    }

    public Date getHorarioJornadaTerminoHorario() {
        return horarioJornadaTerminoHorario;
    }

    public void setHorarioJornadaTerminoHorario(Date horarioJornadaTerminoHorario) {
        this.horarioJornadaTerminoHorario = horarioJornadaTerminoHorario;
    }

    public Boolean getHorarioJornadaHorarioVariable() {
        return horarioJornadaHorarioVariable;
    }

    public void setHorarioJornadaHorarioVariable(Boolean horarioJornadaHorarioVariable) {
        this.horarioJornadaHorarioVariable = horarioJornadaHorarioVariable;
    }

    public Cuenta getHorarioJornadaIdCuenta() {
        return horarioJornadaIdCuenta;
    }

    public void setHorarioJornadaIdCuenta(Cuenta horarioJornadaIdCuenta) {
        this.horarioJornadaIdCuenta = horarioJornadaIdCuenta;
    }

    @XmlTransient
    public List<JornadaLaboral> getJornadaLaboralList() {
        return jornadaLaboralList;
    }

    public void setJornadaLaboralList(List<JornadaLaboral> jornadaLaboralList) {
        this.jornadaLaboralList = jornadaLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horarioJornadaId != null ? horarioJornadaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioJornada)) {
            return false;
        }
        HorarioJornada other = (HorarioJornada) object;
        return (this.horarioJornadaId != null || other.horarioJornadaId == null) && (this.horarioJornadaId == null || this.horarioJornadaId.equals(other.horarioJornadaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.HorarioJornada[ horarioJornadaId=" + horarioJornadaId + " ]";
    }
    
}
