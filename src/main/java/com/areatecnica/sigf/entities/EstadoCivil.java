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
@Table(name = "estado_civil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e")
    , @NamedQuery(name = "EstadoCivil.findByEstadoCivilId", query = "SELECT e FROM EstadoCivil e WHERE e.estadoCivilId = :estadoCivilId")
    , @NamedQuery(name = "EstadoCivil.findByEstadoCivilNombre", query = "SELECT e FROM EstadoCivil e WHERE e.estadoCivilNombre = :estadoCivilNombre")})
public class EstadoCivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estado_civil_id", nullable = false)
    private Integer estadoCivilId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado_civil_nombre", nullable = false, length = 45)
    private String estadoCivilNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdEstadoCivil")
    private List<Trabajador> trabajadorList;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public EstadoCivil(Integer estadoCivilId, String estadoCivilNombre) {
        this.estadoCivilId = estadoCivilId;
        this.estadoCivilNombre = estadoCivilNombre;
    }

    public Integer getEstadoCivilId() {
        return estadoCivilId;
    }

    public void setEstadoCivilId(Integer estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public String getEstadoCivilNombre() {
        return estadoCivilNombre;
    }

    public void setEstadoCivilNombre(String estadoCivilNombre) {
        this.estadoCivilNombre = estadoCivilNombre;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoCivilId != null ? estadoCivilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil)) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        return (this.estadoCivilId != null || other.estadoCivilId == null) && (this.estadoCivilId == null || this.estadoCivilId.equals(other.estadoCivilId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EstadoCivil[ estadoCivilId=" + estadoCivilId + " ]";
    }
    
}
