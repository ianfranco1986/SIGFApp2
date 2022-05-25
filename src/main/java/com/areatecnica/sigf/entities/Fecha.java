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
@Table(name = "fecha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fecha.findAll", query = "SELECT f FROM Fecha f")
    , @NamedQuery(name = "Fecha.findByFechaId", query = "SELECT f FROM Fecha f WHERE f.fechaId = :fechaId")
    , @NamedQuery(name = "Fecha.findByFecha", query = "SELECT f FROM Fecha f WHERE f.fecha = :fecha")})
public class Fecha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fecha_id", nullable = false)
    private Integer fechaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Fecha() {
    }

    public Fecha(Integer fechaId) {
        this.fechaId = fechaId;
    }

    public Fecha(Integer fechaId, Date fecha) {
        this.fechaId = fechaId;
        this.fecha = fecha;
    }

    public Integer getFechaId() {
        return fechaId;
    }

    public void setFechaId(Integer fechaId) {
        this.fechaId = fechaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaId != null ? fechaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fecha)) {
            return false;
        }
        Fecha other = (Fecha) object;
        return (this.fechaId != null || other.fechaId == null) && (this.fechaId == null || this.fechaId.equals(other.fechaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Fecha[ fechaId=" + fechaId + " ]";
    }
    
}
