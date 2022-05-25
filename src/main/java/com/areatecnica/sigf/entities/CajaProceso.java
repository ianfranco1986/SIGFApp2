/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "caja_proceso")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaProceso.findAll", query = "SELECT c FROM CajaProceso c")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoId", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoId = :cajaProcesoId")
    , @NamedQuery(name = "CajaProceso.findByCajaProcesoActivo", query = "SELECT c FROM CajaProceso c WHERE c.cajaProcesoActivo = :cajaProcesoActivo")})
public class CajaProceso extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caja_proceso_id", nullable = false)
    private Integer cajaProcesoId;
    @Column(name = "caja_proceso_activo")
    private Boolean cajaProcesoActivo;
    @JoinColumn(name = "caja_proceso_id_caja", referencedColumnName = "caja_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private CajaRecaudacion cajaProcesoIdCaja;
    @JoinColumn(name = "caja_proceso_id_proceso", referencedColumnName = "proceso_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ProcesoRecaudacion cajaProcesoIdProceso;

    public CajaProceso() {
    }

    public CajaProceso(Integer cajaProcesoId) {
        this.cajaProcesoId = cajaProcesoId;
    }

    public Integer getCajaProcesoId() {
        return cajaProcesoId;
    }

    public void setCajaProcesoId(Integer cajaProcesoId) {
        this.cajaProcesoId = cajaProcesoId;
    }

    public Boolean getCajaProcesoActivo() {
        return cajaProcesoActivo;
    }

    public void setCajaProcesoActivo(Boolean cajaProcesoActivo) {
        this.cajaProcesoActivo = cajaProcesoActivo;
    }

    public CajaRecaudacion getCajaProcesoIdCaja() {
        return cajaProcesoIdCaja;
    }

    public void setCajaProcesoIdCaja(CajaRecaudacion cajaProcesoIdCaja) {
        this.cajaProcesoIdCaja = cajaProcesoIdCaja;
    }

    public ProcesoRecaudacion getCajaProcesoIdProceso() {
        return cajaProcesoIdProceso;
    }

    public void setCajaProcesoIdProceso(ProcesoRecaudacion cajaProcesoIdProceso) {
        this.cajaProcesoIdProceso = cajaProcesoIdProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cajaProcesoId != null ? cajaProcesoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaProceso)) {
            return false;
        }
        CajaProceso other = (CajaProceso) object;
        return (this.cajaProcesoId != null || other.cajaProcesoId == null) && (this.cajaProcesoId == null || this.cajaProcesoId.equals(other.cajaProcesoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CajaProceso[ cajaProcesoId=" + cajaProcesoId + " ]";
    }
    
}
