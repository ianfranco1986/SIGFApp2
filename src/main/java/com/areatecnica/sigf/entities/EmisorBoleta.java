/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "emisor_boleta")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmisorBoleta.findAll", query = "SELECT e FROM EmisorBoleta e"),
    @NamedQuery(name = "EmisorBoleta.findByEmisorBoletaId", query = "SELECT e FROM EmisorBoleta e WHERE e.emisorBoletaId = :emisorBoletaId"),
    @NamedQuery(name = "EmisorBoleta.findByEmisorBoletaNombre", query = "SELECT e FROM EmisorBoleta e WHERE e.emisorBoletaNombre = :emisorBoletaNombre"),
    @NamedQuery(name = "EmisorBoleta.findByEmisorBoletaRut", query = "SELECT e FROM EmisorBoleta e WHERE e.emisorBoletaRut = :emisorBoletaRut")})
public class EmisorBoleta extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emisor_boleta_id")
    private Integer emisorBoletaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "emisor_boleta_nombre")
    private String emisorBoletaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "emisor_boleta_rut")
    private String emisorBoletaRut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "honorarioEmisorId")
    private List<Honorario> honorarioList;

    public EmisorBoleta() {
    }

    public EmisorBoleta(Integer emisorBoletaId) {
        this.emisorBoletaId = emisorBoletaId;
    }

    public EmisorBoleta(Integer emisorBoletaId, String emisorBoletaNombre, String emisorBoletaRut) {
        this.emisorBoletaId = emisorBoletaId;
        this.emisorBoletaNombre = emisorBoletaNombre;
        this.emisorBoletaRut = emisorBoletaRut;
    }

    public Integer getEmisorBoletaId() {
        return emisorBoletaId;
    }

    public void setEmisorBoletaId(Integer emisorBoletaId) {
        this.emisorBoletaId = emisorBoletaId;
    }

    public String getEmisorBoletaNombre() {
        return emisorBoletaNombre;
    }

    public void setEmisorBoletaNombre(String emisorBoletaNombre) {
        this.emisorBoletaNombre = emisorBoletaNombre;
    }

    public String getEmisorBoletaRut() {
        return emisorBoletaRut;
    }

    public void setEmisorBoletaRut(String emisorBoletaRut) {
        this.emisorBoletaRut = emisorBoletaRut;
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
        hash += (emisorBoletaId != null ? emisorBoletaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmisorBoleta)) {
            return false;
        }
        EmisorBoleta other = (EmisorBoleta) object;
        if ((this.emisorBoletaId == null && other.emisorBoletaId != null) || (this.emisorBoletaId != null && !this.emisorBoletaId.equals(other.emisorBoletaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EmisorBoleta[ emisorBoletaId=" + emisorBoletaId + " ]";
    }
    
}
