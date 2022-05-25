/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_boleta_honorario")
@XmlRootElement
@EntityListeners(AuditListener.class)
@NamedQueries({
    @NamedQuery(name = "TipoBoletaHonorario.findAll", query = "SELECT t FROM TipoBoletaHonorario t"),
    @NamedQuery(name = "TipoBoletaHonorario.findByTipoBoletaHonorarioId", query = "SELECT t FROM TipoBoletaHonorario t WHERE t.tipoBoletaHonorarioId = :tipoBoletaHonorarioId"),
    @NamedQuery(name = "TipoBoletaHonorario.findByTipoBoletaHonorarioNombre", query = "SELECT t FROM TipoBoletaHonorario t WHERE t.tipoBoletaHonorarioNombre = :tipoBoletaHonorarioNombre")})
public class TipoBoletaHonorario extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_boleta_honorario_id")
    private Integer tipoBoletaHonorarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_boleta_honorario_nombre")
    private String tipoBoletaHonorarioNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "honorarioTipoBoletaId")
    private List<Honorario> honorarioList;

    public TipoBoletaHonorario() {
    }

    public TipoBoletaHonorario(Integer tipoBoletaHonorarioId) {
        this.tipoBoletaHonorarioId = tipoBoletaHonorarioId;
    }

    public TipoBoletaHonorario(Integer tipoBoletaHonorarioId, String tipoBoletaHonorarioNombre) {
        this.tipoBoletaHonorarioId = tipoBoletaHonorarioId;
        this.tipoBoletaHonorarioNombre = tipoBoletaHonorarioNombre;
    }

    public Integer getTipoBoletaHonorarioId() {
        return tipoBoletaHonorarioId;
    }

    public void setTipoBoletaHonorarioId(Integer tipoBoletaHonorarioId) {
        this.tipoBoletaHonorarioId = tipoBoletaHonorarioId;
    }

    public String getTipoBoletaHonorarioNombre() {
        return tipoBoletaHonorarioNombre;
    }

    public void setTipoBoletaHonorarioNombre(String tipoBoletaHonorarioNombre) {
        this.tipoBoletaHonorarioNombre = tipoBoletaHonorarioNombre;
    }

    @XmlTransient
    public List<Honorario> getHonorarioList() {
        return honorarioList;
    }

    public void setHonorarioList(List<Honorario> honorarioList) {
        this.honorarioList = honorarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoBoletaHonorarioId != null ? tipoBoletaHonorarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBoletaHonorario)) {
            return false;
        }
        TipoBoletaHonorario other = (TipoBoletaHonorario) object;
        return (this.tipoBoletaHonorarioId != null || other.tipoBoletaHonorarioId == null) && (this.tipoBoletaHonorarioId == null || this.tipoBoletaHonorarioId.equals(other.tipoBoletaHonorarioId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoBoletaHonorario[ tipoBoletaHonorarioId=" + tipoBoletaHonorarioId + " ]";
    }
    
}
