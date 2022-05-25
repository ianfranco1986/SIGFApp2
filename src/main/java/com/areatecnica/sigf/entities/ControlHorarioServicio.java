/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "control_horario_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlHorarioServicio.findAll", query = "SELECT c FROM ControlHorarioServicio c")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioId", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioId = :controlHorarioServicioId")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioTiempo", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioTiempo = :controlHorarioServicioTiempo")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioMulta", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioMulta = :controlHorarioServicioMulta")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioPeso", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioPeso = :controlHorarioServicioPeso")})
public class ControlHorarioServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_horario_servicio_id", nullable = false)
    private Integer controlHorarioServicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_horario_servicio_tiempo", nullable = false)
    private int controlHorarioServicioTiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_horario_servicio_multa", nullable = false)
    private int controlHorarioServicioMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_horario_servicio_peso", nullable = false)
    private int controlHorarioServicioPeso;
    @JoinColumn(name = "control_horario_servicio_id_control", referencedColumnName = "control_id", nullable = false)
    @ManyToOne(optional = false)
    private Control controlHorarioServicioIdControl;
    @JoinColumn(name = "control_horario_servicio_id_horario", referencedColumnName = "horario_servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private HorarioServicio controlHorarioServicioIdHorario;

    public ControlHorarioServicio() {
    }

    public ControlHorarioServicio(Integer controlHorarioServicioId) {
        this.controlHorarioServicioId = controlHorarioServicioId;
    }

    public ControlHorarioServicio(Integer controlHorarioServicioId, int controlHorarioServicioTiempo, int controlHorarioServicioMulta, int controlHorarioServicioPeso) {
        this.controlHorarioServicioId = controlHorarioServicioId;
        this.controlHorarioServicioTiempo = controlHorarioServicioTiempo;
        this.controlHorarioServicioMulta = controlHorarioServicioMulta;
        this.controlHorarioServicioPeso = controlHorarioServicioPeso;
    }

    public Integer getControlHorarioServicioId() {
        return controlHorarioServicioId;
    }

    public void setControlHorarioServicioId(Integer controlHorarioServicioId) {
        this.controlHorarioServicioId = controlHorarioServicioId;
    }

    public int getControlHorarioServicioTiempo() {
        return controlHorarioServicioTiempo;
    }

    public void setControlHorarioServicioTiempo(int controlHorarioServicioTiempo) {
        this.controlHorarioServicioTiempo = controlHorarioServicioTiempo;
    }

    public int getControlHorarioServicioMulta() {
        return controlHorarioServicioMulta;
    }

    public void setControlHorarioServicioMulta(int controlHorarioServicioMulta) {
        this.controlHorarioServicioMulta = controlHorarioServicioMulta;
    }

    public int getControlHorarioServicioPeso() {
        return controlHorarioServicioPeso;
    }

    public void setControlHorarioServicioPeso(int controlHorarioServicioPeso) {
        this.controlHorarioServicioPeso = controlHorarioServicioPeso;
    }

    public Control getControlHorarioServicioIdControl() {
        return controlHorarioServicioIdControl;
    }

    public void setControlHorarioServicioIdControl(Control controlHorarioServicioIdControl) {
        this.controlHorarioServicioIdControl = controlHorarioServicioIdControl;
    }

    public HorarioServicio getControlHorarioServicioIdHorario() {
        return controlHorarioServicioIdHorario;
    }

    public void setControlHorarioServicioIdHorario(HorarioServicio controlHorarioServicioIdHorario) {
        this.controlHorarioServicioIdHorario = controlHorarioServicioIdHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlHorarioServicioId != null ? controlHorarioServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlHorarioServicio)) {
            return false;
        }
        ControlHorarioServicio other = (ControlHorarioServicio) object;
        return (this.controlHorarioServicioId != null || other.controlHorarioServicioId == null) && (this.controlHorarioServicioId == null || this.controlHorarioServicioId.equals(other.controlHorarioServicioId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ControlHorarioServicio[ controlHorarioServicioId=" + controlHorarioServicioId + " ]";
    }
    
}
