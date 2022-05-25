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
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "control_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlServicio.findAll", query = "SELECT c FROM ControlServicio c")
    , @NamedQuery(name = "ControlServicio.findByControlServicioIdControlServicio", query = "SELECT c FROM ControlServicio c WHERE c.controlServicioIdControlServicio = :controlServicioIdControlServicio")
    , @NamedQuery(name = "ControlServicio.findByControlServicioTiempo", query = "SELECT c FROM ControlServicio c WHERE c.controlServicioTiempo = :controlServicioTiempo")
    , @NamedQuery(name = "ControlServicio.findByControlServicioNumeroOrden", query = "SELECT c FROM ControlServicio c WHERE c.controlServicioNumeroOrden = :controlServicioNumeroOrden")})
public class ControlServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_servicio_id_control_servicio", nullable = false)
    private Integer controlServicioIdControlServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_servicio_tiempo", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date controlServicioTiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_servicio_numero_orden", nullable = false)
    private int controlServicioNumeroOrden;
    @JoinColumn(name = "control_servicio_id_control", referencedColumnName = "control_id", nullable = false)
    @ManyToOne(optional = false)
    private Control controlServicioIdControl;
    @JoinColumn(name = "control_servicio_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio controlServicioIdServicio;

    public ControlServicio() {
    }

    public ControlServicio(Integer controlServicioIdControlServicio) {
        this.controlServicioIdControlServicio = controlServicioIdControlServicio;
    }

    public ControlServicio(Integer controlServicioIdControlServicio, Date controlServicioTiempo, int controlServicioNumeroOrden) {
        this.controlServicioIdControlServicio = controlServicioIdControlServicio;
        this.controlServicioTiempo = controlServicioTiempo;
        this.controlServicioNumeroOrden = controlServicioNumeroOrden;
    }

    public Integer getControlServicioIdControlServicio() {
        return controlServicioIdControlServicio;
    }

    public void setControlServicioIdControlServicio(Integer controlServicioIdControlServicio) {
        this.controlServicioIdControlServicio = controlServicioIdControlServicio;
    }

    public Date getControlServicioTiempo() {
        return controlServicioTiempo;
    }

    public void setControlServicioTiempo(Date controlServicioTiempo) {
        this.controlServicioTiempo = controlServicioTiempo;
    }

    public int getControlServicioNumeroOrden() {
        return controlServicioNumeroOrden;
    }

    public void setControlServicioNumeroOrden(int controlServicioNumeroOrden) {
        this.controlServicioNumeroOrden = controlServicioNumeroOrden;
    }

    public Control getControlServicioIdControl() {
        return controlServicioIdControl;
    }

    public void setControlServicioIdControl(Control controlServicioIdControl) {
        this.controlServicioIdControl = controlServicioIdControl;
    }

    public Servicio getControlServicioIdServicio() {
        return controlServicioIdServicio;
    }

    public void setControlServicioIdServicio(Servicio controlServicioIdServicio) {
        this.controlServicioIdServicio = controlServicioIdServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlServicioIdControlServicio != null ? controlServicioIdControlServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlServicio)) {
            return false;
        }
        ControlServicio other = (ControlServicio) object;
        return (this.controlServicioIdControlServicio != null || other.controlServicioIdControlServicio == null) && (this.controlServicioIdControlServicio == null || this.controlServicioIdControlServicio.equals(other.controlServicioIdControlServicio));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ControlServicio[ controlServicioIdControlServicio=" + controlServicioIdControlServicio + " ]";
    }
    
}
