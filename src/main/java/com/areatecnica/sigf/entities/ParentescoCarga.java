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
@Table(name = "parentesco_carga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentescoCarga.findAll", query = "SELECT p FROM ParentescoCarga p")
    , @NamedQuery(name = "ParentescoCarga.findByParentescoCargaId", query = "SELECT p FROM ParentescoCarga p WHERE p.parentescoCargaId = :parentescoCargaId")
    , @NamedQuery(name = "ParentescoCarga.findByParentescoCargaNombre", query = "SELECT p FROM ParentescoCarga p WHERE p.parentescoCargaNombre = :parentescoCargaNombre")})
public class ParentescoCarga extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parentesco_carga_id", nullable = false)
    private Integer parentescoCargaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "parentesco_carga_nombre", nullable = false, length = 45)
    private String parentescoCargaNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargaTrabajadorIdParentesco")
    private List<CargaTrabajador> cargaTrabajadorList;

    public ParentescoCarga() {
    }

    public ParentescoCarga(Integer parentescoCargaId) {
        this.parentescoCargaId = parentescoCargaId;
    }

    public ParentescoCarga(Integer parentescoCargaId, String parentescoCargaNombre) {
        this.parentescoCargaId = parentescoCargaId;
        this.parentescoCargaNombre = parentescoCargaNombre;
    }

    public Integer getParentescoCargaId() {
        return parentescoCargaId;
    }

    public void setParentescoCargaId(Integer parentescoCargaId) {
        this.parentescoCargaId = parentescoCargaId;
    }

    public String getParentescoCargaNombre() {
        return parentescoCargaNombre;
    }

    public void setParentescoCargaNombre(String parentescoCargaNombre) {
        this.parentescoCargaNombre = parentescoCargaNombre;
    }

    @XmlTransient
    public List<CargaTrabajador> getCargaTrabajadorList() {
        return cargaTrabajadorList;
    }

    public void setCargaTrabajadorList(List<CargaTrabajador> cargaTrabajadorList) {
        this.cargaTrabajadorList = cargaTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentescoCargaId != null ? parentescoCargaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentescoCarga)) {
            return false;
        }
        ParentescoCarga other = (ParentescoCarga) object;
        return (this.parentescoCargaId != null || other.parentescoCargaId == null) && (this.parentescoCargaId == null || this.parentescoCargaId.equals(other.parentescoCargaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ParentescoCarga[ parentescoCargaId=" + parentescoCargaId + " ]";
    }
    
}
