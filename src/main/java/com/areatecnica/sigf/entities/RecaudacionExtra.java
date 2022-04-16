/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "recaudacion_extra", catalog = "sigfdb", schema = "")
@XmlRootElement
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RecaudacionExtra.findAll", query = "SELECT r FROM RecaudacionExtra r"),
    @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraId", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraId = :recaudacionExtraId"),
    @NamedQuery(name = "RecaudacionExtra.findByFechaRecaudacionCaja", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraIdRecaudacion.recaudacionFecha = :recaudacionFecha AND r.recaudacionExtraIdRecaudacion.recaudacionIdCaja = :recaudacionIdCaja ORDER BY r.recaudacionExtraId"),
    @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraMonto", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraMonto = :recaudacionExtraMonto"),
    @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraDescripcion", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraDescripcion = :recaudacionExtraDescripcion"),
    @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraUsuario", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraUsuario = :recaudacionExtraUsuario")})
public class RecaudacionExtra extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_extra_id", nullable = false)
    private Integer recaudacionExtraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_extra_monto", nullable = false)
    private int recaudacionExtraMonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "recaudacion_extra_descripcion", nullable = false, length = 255)
    private String recaudacionExtraDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "recaudacion_extra_usuario", nullable = false, length = 255)
    private String recaudacionExtraUsuario;
    @JoinColumn(name = "recaudacion_extra_id_recaudacion", referencedColumnName = "recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private Recaudacion recaudacionExtraIdRecaudacion;
    @JoinColumn(name = "recaudacion_extra_id_tipo", referencedColumnName = "tipo_recaudacion_extra_id")
    @ManyToOne(optional = false)
    private TipoRecaudacionExtra recaudacionExtraIdTipo;

    public RecaudacionExtra() {
    }

    public RecaudacionExtra(Integer recaudacionExtraId) {
        this.recaudacionExtraId = recaudacionExtraId;
    }

    public RecaudacionExtra(Integer recaudacionExtraId, int recaudacionExtraMonto, String recaudacionExtraDescripcion, String recaudacionExtraUsuario) {
        this.recaudacionExtraId = recaudacionExtraId;
        this.recaudacionExtraMonto = recaudacionExtraMonto;
        this.recaudacionExtraDescripcion = recaudacionExtraDescripcion;
        this.recaudacionExtraUsuario = recaudacionExtraUsuario;
    }

    public Integer getRecaudacionExtraId() {
        return recaudacionExtraId;
    }

    public void setRecaudacionExtraId(Integer recaudacionExtraId) {
        this.recaudacionExtraId = recaudacionExtraId;
    }

    public int getRecaudacionExtraMonto() {
        return recaudacionExtraMonto;
    }

    public void setRecaudacionExtraMonto(int recaudacionExtraMonto) {
        this.recaudacionExtraMonto = recaudacionExtraMonto;
    }

    public String getRecaudacionExtraDescripcion() {
        return recaudacionExtraDescripcion;
    }

    public void setRecaudacionExtraDescripcion(String recaudacionExtraDescripcion) {
        this.recaudacionExtraDescripcion = recaudacionExtraDescripcion;
    }

    public String getRecaudacionExtraUsuario() {
        return recaudacionExtraUsuario;
    }

    public void setRecaudacionExtraUsuario(String recaudacionExtraUsuario) {
        this.recaudacionExtraUsuario = recaudacionExtraUsuario;
    }

    public Recaudacion getRecaudacionExtraIdRecaudacion() {
        return recaudacionExtraIdRecaudacion;
    }

    public void setRecaudacionExtraIdRecaudacion(Recaudacion recaudacionExtraIdRecaudacion) {
        this.recaudacionExtraIdRecaudacion = recaudacionExtraIdRecaudacion;
    }

    public TipoRecaudacionExtra getRecaudacionExtraIdTipo() {
        return recaudacionExtraIdTipo;
    }

    public void setRecaudacionExtraIdTipo(TipoRecaudacionExtra recaudacionExtraIdTipo) {
        this.recaudacionExtraIdTipo = recaudacionExtraIdTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionExtraId != null ? recaudacionExtraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionExtra)) {
            return false;
        }
        RecaudacionExtra other = (RecaudacionExtra) object;
        if ((this.recaudacionExtraId == null && other.recaudacionExtraId != null) || (this.recaudacionExtraId != null && !this.recaudacionExtraId.equals(other.recaudacionExtraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionExtra[ recaudacionExtraId=" + recaudacionExtraId + " ]";
    }

}
