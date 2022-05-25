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
@Table(name = "tipo_institucion_apv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoInstitucionApv.findAll", query = "SELECT t FROM TipoInstitucionApv t")
    , @NamedQuery(name = "TipoInstitucionApv.findByTipoInstitucionApvId", query = "SELECT t FROM TipoInstitucionApv t WHERE t.tipoInstitucionApvId = :tipoInstitucionApvId")
    , @NamedQuery(name = "TipoInstitucionApv.findByTipoInstitucionApvNombre", query = "SELECT t FROM TipoInstitucionApv t WHERE t.tipoInstitucionApvNombre = :tipoInstitucionApvNombre")})
public class TipoInstitucionApv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_institucion_apv_id", nullable = false)
    private Integer tipoInstitucionApvId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_institucion_apv_nombre", nullable = false, length = 100)
    private String tipoInstitucionApvNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionApvIdTipo")
    private List<InstitucionApv> institucionApvList;

    public TipoInstitucionApv() {
    }

    public TipoInstitucionApv(Integer tipoInstitucionApvId) {
        this.tipoInstitucionApvId = tipoInstitucionApvId;
    }

    public TipoInstitucionApv(Integer tipoInstitucionApvId, String tipoInstitucionApvNombre) {
        this.tipoInstitucionApvId = tipoInstitucionApvId;
        this.tipoInstitucionApvNombre = tipoInstitucionApvNombre;
    }

    public Integer getTipoInstitucionApvId() {
        return tipoInstitucionApvId;
    }

    public void setTipoInstitucionApvId(Integer tipoInstitucionApvId) {
        this.tipoInstitucionApvId = tipoInstitucionApvId;
    }

    public String getTipoInstitucionApvNombre() {
        return tipoInstitucionApvNombre;
    }

    public void setTipoInstitucionApvNombre(String tipoInstitucionApvNombre) {
        this.tipoInstitucionApvNombre = tipoInstitucionApvNombre;
    }

    @XmlTransient
    public List<InstitucionApv> getInstitucionApvList() {
        return institucionApvList;
    }

    public void setInstitucionApvList(List<InstitucionApv> institucionApvList) {
        this.institucionApvList = institucionApvList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoInstitucionApvId != null ? tipoInstitucionApvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInstitucionApv)) {
            return false;
        }
        TipoInstitucionApv other = (TipoInstitucionApv) object;
        return (this.tipoInstitucionApvId != null || other.tipoInstitucionApvId == null) && (this.tipoInstitucionApvId == null || this.tipoInstitucionApvId.equals(other.tipoInstitucionApvId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoInstitucionApv[ tipoInstitucionApvId=" + tipoInstitucionApvId + " ]";
    }
    
}
