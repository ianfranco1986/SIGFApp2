/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

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
@Table(name = "tipo_estacionalidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstacionalidad.findAll", query = "SELECT t FROM TipoEstacionalidad t")
    , @NamedQuery(name = "TipoEstacionalidad.findByTipoEstacionalidadId", query = "SELECT t FROM TipoEstacionalidad t WHERE t.tipoEstacionalidadId = :tipoEstacionalidadId")
    , @NamedQuery(name = "TipoEstacionalidad.findByTipoEstacionalidadNombre", query = "SELECT t FROM TipoEstacionalidad t WHERE t.tipoEstacionalidadNombre = :tipoEstacionalidadNombre")})
public class TipoEstacionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_estacionalidad_id", nullable = false)
    private Integer tipoEstacionalidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_estacionalidad_nombre", nullable = false, length = 100)
    private String tipoEstacionalidadNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horarioServicioIdTipoEstacionalidad")
    private List<HorarioServicio> horarioServicioList;

    public TipoEstacionalidad() {
    }

    public TipoEstacionalidad(Integer tipoEstacionalidadId) {
        this.tipoEstacionalidadId = tipoEstacionalidadId;
    }

    public TipoEstacionalidad(Integer tipoEstacionalidadId, String tipoEstacionalidadNombre) {
        this.tipoEstacionalidadId = tipoEstacionalidadId;
        this.tipoEstacionalidadNombre = tipoEstacionalidadNombre;
    }

    public Integer getTipoEstacionalidadId() {
        return tipoEstacionalidadId;
    }

    public void setTipoEstacionalidadId(Integer tipoEstacionalidadId) {
        this.tipoEstacionalidadId = tipoEstacionalidadId;
    }

    public String getTipoEstacionalidadNombre() {
        return tipoEstacionalidadNombre;
    }

    public void setTipoEstacionalidadNombre(String tipoEstacionalidadNombre) {
        this.tipoEstacionalidadNombre = tipoEstacionalidadNombre;
    }

    @XmlTransient
    public List<HorarioServicio> getHorarioServicioList() {
        return horarioServicioList;
    }

    public void setHorarioServicioList(List<HorarioServicio> horarioServicioList) {
        this.horarioServicioList = horarioServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoEstacionalidadId != null ? tipoEstacionalidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstacionalidad)) {
            return false;
        }
        TipoEstacionalidad other = (TipoEstacionalidad) object;
        return (this.tipoEstacionalidadId != null || other.tipoEstacionalidadId == null) && (this.tipoEstacionalidadId == null || this.tipoEstacionalidadId.equals(other.tipoEstacionalidadId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoEstacionalidad[ tipoEstacionalidadId=" + tipoEstacionalidadId + " ]";
    }
    
}
