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
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "carga_retroactiva")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaRetroactiva.findAll", query = "SELECT c FROM CargaRetroactiva c")
    , @NamedQuery(name = "CargaRetroactiva.findByCargaRetroactivaId", query = "SELECT c FROM CargaRetroactiva c WHERE c.cargaRetroactivaId = :cargaRetroactivaId")
    , @NamedQuery(name = "CargaRetroactiva.findByCargaRetroactivaMonto", query = "SELECT c FROM CargaRetroactiva c WHERE c.cargaRetroactivaMonto = :cargaRetroactivaMonto")
    , @NamedQuery(name = "CargaRetroactiva.findByCargaRetroactivaFecha", query = "SELECT c FROM CargaRetroactiva c WHERE c.cargaRetroactivaFecha = :cargaRetroactivaFecha")})
public class CargaRetroactiva extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carga_retroactiva_id", nullable = false)
    private Integer cargaRetroactivaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_retroactiva_monto", nullable = false)
    private int cargaRetroactivaMonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_retroactiva_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date cargaRetroactivaFecha;
    @JoinColumn(name = "carga_retroactiva_id_carga_trabajador", referencedColumnName = "carga_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private CargaTrabajador cargaRetroactivaIdCargaTrabajador;
    @JoinColumn(name = "carga_retroactiva_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador cargaRetroactivaIdTrabajador;

    public CargaRetroactiva() {
    }

    public CargaRetroactiva(Integer cargaRetroactivaId) {
        this.cargaRetroactivaId = cargaRetroactivaId;
    }

    public CargaRetroactiva(Integer cargaRetroactivaId, int cargaRetroactivaMonto, Date cargaRetroactivaFecha) {
        this.cargaRetroactivaId = cargaRetroactivaId;
        this.cargaRetroactivaMonto = cargaRetroactivaMonto;
        this.cargaRetroactivaFecha = cargaRetroactivaFecha;
    }

    public Integer getCargaRetroactivaId() {
        return cargaRetroactivaId;
    }

    public void setCargaRetroactivaId(Integer cargaRetroactivaId) {
        this.cargaRetroactivaId = cargaRetroactivaId;
    }

    public int getCargaRetroactivaMonto() {
        return cargaRetroactivaMonto;
    }

    public void setCargaRetroactivaMonto(int cargaRetroactivaMonto) {
        this.cargaRetroactivaMonto = cargaRetroactivaMonto;
    }

    public Date getCargaRetroactivaFecha() {
        return cargaRetroactivaFecha;
    }

    public void setCargaRetroactivaFecha(Date cargaRetroactivaFecha) {
        this.cargaRetroactivaFecha = cargaRetroactivaFecha;
    }

    public CargaTrabajador getCargaRetroactivaIdCargaTrabajador() {
        return cargaRetroactivaIdCargaTrabajador;
    }

    public void setCargaRetroactivaIdCargaTrabajador(CargaTrabajador cargaRetroactivaIdCargaTrabajador) {
        this.cargaRetroactivaIdCargaTrabajador = cargaRetroactivaIdCargaTrabajador;
    }

    public Trabajador getCargaRetroactivaIdTrabajador() {
        return cargaRetroactivaIdTrabajador;
    }

    public void setCargaRetroactivaIdTrabajador(Trabajador cargaRetroactivaIdTrabajador) {
        this.cargaRetroactivaIdTrabajador = cargaRetroactivaIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargaRetroactivaId != null ? cargaRetroactivaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargaRetroactiva)) {
            return false;
        }
        CargaRetroactiva other = (CargaRetroactiva) object;
        return (this.cargaRetroactivaId != null || other.cargaRetroactivaId == null) && (this.cargaRetroactivaId == null || this.cargaRetroactivaId.equals(other.cargaRetroactivaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CargaRetroactiva[ cargaRetroactivaId=" + cargaRetroactivaId + " ]";
    }
    
}
