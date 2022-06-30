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
@Table(name = "recaudacion_minuto")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RecaudacionMinuto.findAll", query = "SELECT r FROM RecaudacionMinuto r")
    , @NamedQuery(name = "RecaudacionMinuto.findByFechaRecaudacionMinutoCaja", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRecaudacion.recaudacionIdCaja = :recaudacionIdCaja AND r.recaudacionMinutoIdRecaudacion.recaudacionFecha = :recaudacionFecha ORDER BY r.recaudacionMinutoIdRecaudacion.recaudacionId ASC")
    , @NamedQuery(name = "RecaudacionMinuto.findRecibidosBusFechas", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRegistroMinuto.registroMinutoHastaIdBus = :registroMinutoHastaIdBus AND r.recaudacionMinutoIdRecaudacion.recaudacionFecha BETWEEN :from AND :to ORDER BY r.recaudacionMinutoIdRecaudacion.recaudacionId ASC")
    , @NamedQuery(name = "RecaudacionMinuto.findByRecaudacionMinutoId", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoId = :recaudacionMinutoId")
    , @NamedQuery(name = "RecaudacionMinuto.findByRecaudacionMinutoMonto", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoMonto = :recaudacionMinutoMonto")})
public class RecaudacionMinuto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_minuto_id", nullable = false)
    private Integer recaudacionMinutoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_minuto_monto", nullable = false)
    private int recaudacionMinutoMonto;
    @JoinColumn(name = "recaudacion_minuto_id_recaudacion", referencedColumnName = "recaudacion_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recaudacion recaudacionMinutoIdRecaudacion;
    @JoinColumn(name = "recaudacion_minuto_id_registro_minuto", referencedColumnName = "registro_minuto_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RegistroMinuto recaudacionMinutoIdRegistroMinuto;

    public RecaudacionMinuto() {
    }

    public RecaudacionMinuto(Integer recaudacionMinutoId) {
        this.recaudacionMinutoId = recaudacionMinutoId;
    }

    public RecaudacionMinuto(Integer recaudacionMinutoId, int recaudacionMinutoMonto) {
        this.recaudacionMinutoId = recaudacionMinutoId;
        this.recaudacionMinutoMonto = recaudacionMinutoMonto;
    }

    public Integer getRecaudacionMinutoId() {
        return recaudacionMinutoId;
    }

    public void setRecaudacionMinutoId(Integer recaudacionMinutoId) {
        this.recaudacionMinutoId = recaudacionMinutoId;
    }

    public int getRecaudacionMinutoMonto() {
        return recaudacionMinutoMonto;
    }

    public void setRecaudacionMinutoMonto(int recaudacionMinutoMonto) {
        this.recaudacionMinutoMonto = recaudacionMinutoMonto;
    }

    public Recaudacion getRecaudacionMinutoIdRecaudacion() {
        return recaudacionMinutoIdRecaudacion;
    }

    public void setRecaudacionMinutoIdRecaudacion(Recaudacion recaudacionMinutoIdRecaudacion) {
        this.recaudacionMinutoIdRecaudacion = recaudacionMinutoIdRecaudacion;
    }

    public RegistroMinuto getRecaudacionMinutoIdRegistroMinuto() {
        return recaudacionMinutoIdRegistroMinuto;
    }

    public void setRecaudacionMinutoIdRegistroMinuto(RegistroMinuto recaudacionMinutoIdRegistroMinuto) {
        this.recaudacionMinutoIdRegistroMinuto = recaudacionMinutoIdRegistroMinuto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionMinutoId != null ? recaudacionMinutoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionMinuto)) {
            return false;
        }
        RecaudacionMinuto other = (RecaudacionMinuto) object;
        return (this.recaudacionMinutoId != null || other.recaudacionMinutoId == null) && (this.recaudacionMinutoId == null || this.recaudacionMinutoId.equals(other.recaudacionMinutoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionMinuto[ recaudacionMinutoId=" + recaudacionMinutoId + " ]";
    }
    
}
