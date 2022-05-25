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
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.findByCiudadId", query = "SELECT c FROM Ciudad c WHERE c.ciudadId = :ciudadId")
    , @NamedQuery(name = "Ciudad.findByCiudadNombre", query = "SELECT c FROM Ciudad c WHERE c.ciudadNombre = :ciudadNombre")})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ciudad_id", nullable = false)
    private Integer ciudadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ciudad_nombre", nullable = false, length = 45)
    private String ciudadNombre;
    @JoinColumn(name = "ciudad_id_region", referencedColumnName = "region_id", nullable = false)
    @ManyToOne(optional = false)
    private Region ciudadIdRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comunaIdCiudad")
    private List<Comuna> comunaList;

    public Ciudad() {
    }

    public Ciudad(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Ciudad(Integer ciudadId, String ciudadNombre) {
        this.ciudadId = ciudadId;
        this.ciudadNombre = ciudadNombre;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public Region getCiudadIdRegion() {
        return ciudadIdRegion;
    }

    public void setCiudadIdRegion(Region ciudadIdRegion) {
        this.ciudadIdRegion = ciudadIdRegion;
    }

    @XmlTransient
    public List<Comuna> getComunaList() {
        return comunaList;
    }

    public void setComunaList(List<Comuna> comunaList) {
        this.comunaList = comunaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciudadId != null ? ciudadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        return (this.ciudadId != null || other.ciudadId == null) && (this.ciudadId == null || this.ciudadId.equals(other.ciudadId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Ciudad[ ciudadId=" + ciudadId + " ]";
    }
    
}
