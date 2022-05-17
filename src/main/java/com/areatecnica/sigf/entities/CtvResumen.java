/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "ctv_resumen", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ctv_resumen_numero"})})
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtvResumen.findAll", query = "SELECT c FROM CtvResumen c")
    , @NamedQuery(name = "CtvResumen.findByCtvResumenId", query = "SELECT c FROM CtvResumen c WHERE c.ctvResumenId = :ctvResumenId")
    , @NamedQuery(name = "CtvResumen.findByCtvResumenNumero", query = "SELECT c FROM CtvResumen c WHERE c.ctvResumenNumero = :ctvResumenNumero")
    , @NamedQuery(name = "CtvResumen.findByCtvResumenCantidadBolsas", query = "SELECT c FROM CtvResumen c WHERE c.ctvResumenCantidadBolsas = :ctvResumenCantidadBolsas")
    , @NamedQuery(name = "CtvResumen.findByCtvResumenTotalTransportado", query = "SELECT c FROM CtvResumen c WHERE c.ctvResumenTotalTransportado = :ctvResumenTotalTransportado")
    , @NamedQuery(name = "CtvResumen.findByCtvResumenFechaHoraRetiro", query = "SELECT c FROM CtvResumen c WHERE c.ctvResumenFechaHoraRetiro = :ctvResumenFechaHoraRetiro")})
public class CtvResumen extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ctv_resumen_id", nullable = false)
    private Integer ctvResumenId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctv_resumen_numero", nullable = false)
    private int ctvResumenNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctv_resumen_cantidad_bolsas", nullable = false)
    private int ctvResumenCantidadBolsas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctv_resumen_total_transportado", nullable = false)
    private int ctvResumenTotalTransportado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctv_resumen_fecha_hora_retiro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctvResumenFechaHoraRetiro;
    @JoinColumn(name = "ctv_resumen_id_resumen_recaudacion", referencedColumnName = "resumen_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ResumenRecaudacion ctvResumenIdResumenRecaudacion;

    public CtvResumen() {
    }

    public CtvResumen(Integer ctvResumenId) {
        this.ctvResumenId = ctvResumenId;
    }

    public CtvResumen(Integer ctvResumenId, int ctvResumenNumero, int ctvResumenCantidadBolsas, int ctvResumenTotalTransportado, Date ctvResumenFechaHoraRetiro) {
        this.ctvResumenId = ctvResumenId;
        this.ctvResumenNumero = ctvResumenNumero;
        this.ctvResumenCantidadBolsas = ctvResumenCantidadBolsas;
        this.ctvResumenTotalTransportado = ctvResumenTotalTransportado;
        this.ctvResumenFechaHoraRetiro = ctvResumenFechaHoraRetiro;
    }

    public Integer getCtvResumenId() {
        return ctvResumenId;
    }

    public void setCtvResumenId(Integer ctvResumenId) {
        this.ctvResumenId = ctvResumenId;
    }

    public int getCtvResumenNumero() {
        return ctvResumenNumero;
    }

    public void setCtvResumenNumero(int ctvResumenNumero) {
        this.ctvResumenNumero = ctvResumenNumero;
    }

    public int getCtvResumenCantidadBolsas() {
        return ctvResumenCantidadBolsas;
    }

    public void setCtvResumenCantidadBolsas(int ctvResumenCantidadBolsas) {
        this.ctvResumenCantidadBolsas = ctvResumenCantidadBolsas;
    }

    public int getCtvResumenTotalTransportado() {
        return ctvResumenTotalTransportado;
    }

    public void setCtvResumenTotalTransportado(int ctvResumenTotalTransportado) {
        this.ctvResumenTotalTransportado = ctvResumenTotalTransportado;
    }

    public Date getCtvResumenFechaHoraRetiro() {
        return ctvResumenFechaHoraRetiro;
    }

    public void setCtvResumenFechaHoraRetiro(Date ctvResumenFechaHoraRetiro) {
        this.ctvResumenFechaHoraRetiro = ctvResumenFechaHoraRetiro;
    }

    public ResumenRecaudacion getCtvResumenIdResumenRecaudacion() {
        return ctvResumenIdResumenRecaudacion;
    }

    public void setCtvResumenIdResumenRecaudacion(ResumenRecaudacion ctvResumenIdResumenRecaudacion) {
        this.ctvResumenIdResumenRecaudacion = ctvResumenIdResumenRecaudacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctvResumenId != null ? ctvResumenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtvResumen)) {
            return false;
        }
        CtvResumen other = (CtvResumen) object;
        if ((this.ctvResumenId == null && other.ctvResumenId != null) || (this.ctvResumenId != null && !this.ctvResumenId.equals(other.ctvResumenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CtvResumen[ ctvResumenId=" + ctvResumenId + " ]";
    }
    
}
