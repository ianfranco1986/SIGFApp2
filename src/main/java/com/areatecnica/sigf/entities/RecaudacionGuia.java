/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "recaudacion_guia")
@XmlRootElement
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RecaudacionGuia.findAll", query = "SELECT r FROM RecaudacionGuia r")
    , @NamedQuery(name = "RecaudacionGuia.findByRecaudacionGuiaId", query = "SELECT r FROM RecaudacionGuia r WHERE r.recaudacionGuiaId = :recaudacionGuiaId")
    , @NamedQuery(name = "RecaudacionGuia.findByRecaudacionGuiaIdRecaudacionEgreso", query = "SELECT r FROM RecaudacionGuia r WHERE r.recaudacionGuiaIdRecaudacion.recaudacionId =:recaudacionId AND r.recaudacionGuiaIdEgreso = :recaudacionGuiaIdEgreso")
    , @NamedQuery(name = "RecaudacionGuia.findByCajaFecha", query = "SELECT r FROM RecaudacionGuia r WHERE r.recaudacionGuiaIdRecaudacion.recaudacionIdCaja = :recaudacionIdCaja AND r.recaudacionGuiaIdRecaudacion.recaudacionFecha = :recaudacionFecha ORDER BY r.recaudacionGuiaIdRecaudacion.recaudacionId")
    , @NamedQuery(name = "RecaudacionGuia.findByBusBetweenFechaRecaudacion", query = "SELECT r FROM RecaudacionGuia r WHERE r.recaudacionGuiaIdGuia.guiaIdBus = :guiaIdBus AND r.recaudacionGuiaIdRecaudacion.recaudacionFecha BETWEEN :from AND :to ")
    , @NamedQuery(name = "RecaudacionGuia.findByRecaudacionGuiaMonto", query = "SELECT r FROM RecaudacionGuia r WHERE r.recaudacionGuiaMonto = :recaudacionGuiaMonto")})
public class RecaudacionGuia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_guia_id", nullable = false)
    private Integer recaudacionGuiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_guia_monto", nullable = false)
    private int recaudacionGuiaMonto;
    @JoinColumn(name = "recaudacion_guia_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso recaudacionGuiaIdEgreso;
    @JoinColumn(name = "recaudacion_guia_id_guia", referencedColumnName = "guia_id", nullable = false)
    @ManyToOne(optional = false)
    private Guia recaudacionGuiaIdGuia;
    @JoinColumn(name = "recaudacion_guia_id_recaudacion", referencedColumnName = "recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private Recaudacion recaudacionGuiaIdRecaudacion;

    public RecaudacionGuia() {
    }

    public RecaudacionGuia(Integer recaudacionGuiaId) {
        this.recaudacionGuiaId = recaudacionGuiaId;
    }

    public RecaudacionGuia(Integer recaudacionGuiaId, int recaudacionGuiaMonto) {
        this.recaudacionGuiaId = recaudacionGuiaId;
        this.recaudacionGuiaMonto = recaudacionGuiaMonto;
    }

    public Integer getRecaudacionGuiaId() {
        return recaudacionGuiaId;
    }

    public void setRecaudacionGuiaId(Integer recaudacionGuiaId) {
        this.recaudacionGuiaId = recaudacionGuiaId;
    }

    public int getRecaudacionGuiaMonto() {
        return recaudacionGuiaMonto;
    }

    public void setRecaudacionGuiaMonto(int recaudacionGuiaMonto) {
        this.recaudacionGuiaMonto = recaudacionGuiaMonto;
    }

    public Egreso getRecaudacionGuiaIdEgreso() {
        return recaudacionGuiaIdEgreso;
    }

    public void setRecaudacionGuiaIdEgreso(Egreso recaudacionGuiaIdEgreso) {
        this.recaudacionGuiaIdEgreso = recaudacionGuiaIdEgreso;
    }

    public Guia getRecaudacionGuiaIdGuia() {
        return recaudacionGuiaIdGuia;
    }

    public void setRecaudacionGuiaIdGuia(Guia recaudacionGuiaIdGuia) {
        this.recaudacionGuiaIdGuia = recaudacionGuiaIdGuia;
    }

    public Recaudacion getRecaudacionGuiaIdRecaudacion() {
        return recaudacionGuiaIdRecaudacion;
    }

    public void setRecaudacionGuiaIdRecaudacion(Recaudacion recaudacionGuiaIdRecaudacion) {
        this.recaudacionGuiaIdRecaudacion = recaudacionGuiaIdRecaudacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionGuiaId != null ? recaudacionGuiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionGuia)) {
            return false;
        }
        RecaudacionGuia other = (RecaudacionGuia) object;
        return (this.recaudacionGuiaId != null || other.recaudacionGuiaId == null) && (this.recaudacionGuiaId == null || this.recaudacionGuiaId.equals(other.recaudacionGuiaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionGuia[ recaudacionGuiaId=" + recaudacionGuiaId + " ]";
    }

}
