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
@Table(name = "institucion_apv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstitucionApv.findAll", query = "SELECT i FROM InstitucionApv i")
    , @NamedQuery(name = "InstitucionApv.findByInstitucionApvId", query = "SELECT i FROM InstitucionApv i WHERE i.institucionApvId = :institucionApvId")
    , @NamedQuery(name = "InstitucionApv.findByInstitucionApvNombre", query = "SELECT i FROM InstitucionApv i WHERE i.institucionApvNombre = :institucionApvNombre")})
public class InstitucionApv extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "institucion_apv_id", nullable = false)
    private Integer institucionApvId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "institucion_apv_nombre", nullable = false, length = 100)
    private String institucionApvNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdInstitucionApv", fetch = FetchType.LAZY)
    private List<Trabajador> trabajadorList;
    @JoinColumn(name = "institucion_apv_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta institucionApvIdCuenta;
    @JoinColumn(name = "institucion_apv_id_tipo", referencedColumnName = "tipo_institucion_apv_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoInstitucionApv institucionApvIdTipo;

    public InstitucionApv() {
    }

    public InstitucionApv(Integer institucionApvId) {
        this.institucionApvId = institucionApvId;
    }

    public InstitucionApv(Integer institucionApvId, String institucionApvNombre) {
        this.institucionApvId = institucionApvId;
        this.institucionApvNombre = institucionApvNombre;
    }

    public Integer getInstitucionApvId() {
        return institucionApvId;
    }

    public void setInstitucionApvId(Integer institucionApvId) {
        this.institucionApvId = institucionApvId;
    }

    public String getInstitucionApvNombre() {
        return institucionApvNombre;
    }

    public void setInstitucionApvNombre(String institucionApvNombre) {
        this.institucionApvNombre = institucionApvNombre;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public Cuenta getInstitucionApvIdCuenta() {
        return institucionApvIdCuenta;
    }

    public void setInstitucionApvIdCuenta(Cuenta institucionApvIdCuenta) {
        this.institucionApvIdCuenta = institucionApvIdCuenta;
    }

    public TipoInstitucionApv getInstitucionApvIdTipo() {
        return institucionApvIdTipo;
    }

    public void setInstitucionApvIdTipo(TipoInstitucionApv institucionApvIdTipo) {
        this.institucionApvIdTipo = institucionApvIdTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (institucionApvId != null ? institucionApvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstitucionApv)) {
            return false;
        }
        InstitucionApv other = (InstitucionApv) object;
        return (this.institucionApvId != null || other.institucionApvId == null) && (this.institucionApvId == null || this.institucionApvId.equals(other.institucionApvId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.InstitucionApv[ institucionApvId=" + institucionApvId + " ]";
    }
    
}
