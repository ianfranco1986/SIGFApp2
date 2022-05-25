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
@Table(name = "calle_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalleServicio.findAll", query = "SELECT c FROM CalleServicio c")
    , @NamedQuery(name = "CalleServicio.findByCalleServicioId", query = "SELECT c FROM CalleServicio c WHERE c.calleServicioId = :calleServicioId")
    , @NamedQuery(name = "CalleServicio.findByCalleServicioNumeroOrden", query = "SELECT c FROM CalleServicio c WHERE c.calleServicioNumeroOrden = :calleServicioNumeroOrden")
    , @NamedQuery(name = "CalleServicio.findByCalleServicioSentido", query = "SELECT c FROM CalleServicio c WHERE c.calleServicioSentido = :calleServicioSentido")})
public class CalleServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "calle_servicio_id", nullable = false)
    private Integer calleServicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calle_servicio_numero_orden", nullable = false)
    private int calleServicioNumeroOrden;
    @Column(name = "calle_servicio_sentido")
    private Boolean calleServicioSentido;
    @JoinColumn(name = "calle_servicio_id_calle", referencedColumnName = "calle_id", nullable = false)
    @ManyToOne(optional = false)
    private Calle calleServicioIdCalle;
    @JoinColumn(name = "calle_servicio_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio calleServicioIdServicio;

    public CalleServicio() {
    }

    public CalleServicio(Integer calleServicioId) {
        this.calleServicioId = calleServicioId;
    }

    public CalleServicio(Integer calleServicioId, int calleServicioNumeroOrden) {
        this.calleServicioId = calleServicioId;
        this.calleServicioNumeroOrden = calleServicioNumeroOrden;
    }

    public Integer getCalleServicioId() {
        return calleServicioId;
    }

    public void setCalleServicioId(Integer calleServicioId) {
        this.calleServicioId = calleServicioId;
    }

    public int getCalleServicioNumeroOrden() {
        return calleServicioNumeroOrden;
    }

    public void setCalleServicioNumeroOrden(int calleServicioNumeroOrden) {
        this.calleServicioNumeroOrden = calleServicioNumeroOrden;
    }

    public Boolean getCalleServicioSentido() {
        return calleServicioSentido;
    }

    public void setCalleServicioSentido(Boolean calleServicioSentido) {
        this.calleServicioSentido = calleServicioSentido;
    }

    public Calle getCalleServicioIdCalle() {
        return calleServicioIdCalle;
    }

    public void setCalleServicioIdCalle(Calle calleServicioIdCalle) {
        this.calleServicioIdCalle = calleServicioIdCalle;
    }

    public Servicio getCalleServicioIdServicio() {
        return calleServicioIdServicio;
    }

    public void setCalleServicioIdServicio(Servicio calleServicioIdServicio) {
        this.calleServicioIdServicio = calleServicioIdServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calleServicioId != null ? calleServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalleServicio)) {
            return false;
        }
        CalleServicio other = (CalleServicio) object;
        return (this.calleServicioId != null || other.calleServicioId == null) && (this.calleServicioId == null || this.calleServicioId.equals(other.calleServicioId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CalleServicio[ calleServicioId=" + calleServicioId + " ]";
    }
    
}
