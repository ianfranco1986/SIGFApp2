/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "institucion_prevision", catalog = "sigfdb_baquedano", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstitucionPrevision.findAll", query = "SELECT i FROM InstitucionPrevision i")
    , @NamedQuery(name = "InstitucionPrevision.findByInstitucionPrevisionId", query = "SELECT i FROM InstitucionPrevision i WHERE i.institucionPrevisionId = :institucionPrevisionId")
    , @NamedQuery(name = "InstitucionPrevision.findByInstitucionPrevisionNombre", query = "SELECT i FROM InstitucionPrevision i WHERE i.institucionPrevisionNombre = :institucionPrevisionNombre")
    , @NamedQuery(name = "InstitucionPrevision.findByInstitucionPrevisionComision", query = "SELECT i FROM InstitucionPrevision i WHERE i.institucionPrevisionComision = :institucionPrevisionComision")
    , @NamedQuery(name = "InstitucionPrevision.findByInstitucionPrevisionPorcentajeDescuento", query = "SELECT i FROM InstitucionPrevision i WHERE i.institucionPrevisionPorcentajeDescuento = :institucionPrevisionPorcentajeDescuento")})
public class InstitucionPrevision extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "institucion_prevision_id", nullable = false)
    private Integer institucionPrevisionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "institucion_prevision_nombre", nullable = false, length = 255)
    private String institucionPrevisionNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "institucion_prevision_comision", precision = 5, scale = 3)
    private Float institucionPrevisionComision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "institucion_prevision_porcentaje_descuento", nullable = false)
    private float institucionPrevisionPorcentajeDescuento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdInstitucionPrevision")
    private List<Trabajador> trabajadorList;
    @JoinColumn(name = "institucion_prevision_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta institucionPrevisionIdCuenta;

    public InstitucionPrevision() {
    }

    public InstitucionPrevision(Integer institucionPrevisionId) {
        this.institucionPrevisionId = institucionPrevisionId;
    }

    public InstitucionPrevision(Integer institucionPrevisionId, String institucionPrevisionNombre, float institucionPrevisionPorcentajeDescuento) {
        this.institucionPrevisionId = institucionPrevisionId;
        this.institucionPrevisionNombre = institucionPrevisionNombre;
        this.institucionPrevisionPorcentajeDescuento = institucionPrevisionPorcentajeDescuento;
    }

    public Integer getInstitucionPrevisionId() {
        return institucionPrevisionId;
    }

    public void setInstitucionPrevisionId(Integer institucionPrevisionId) {
        this.institucionPrevisionId = institucionPrevisionId;
    }

    public String getInstitucionPrevisionNombre() {
        return institucionPrevisionNombre;
    }

    public void setInstitucionPrevisionNombre(String institucionPrevisionNombre) {
        this.institucionPrevisionNombre = institucionPrevisionNombre;
    }

    public Float getInstitucionPrevisionComision() {
        return institucionPrevisionComision;
    }

    public void setInstitucionPrevisionComision(Float institucionPrevisionComision) {
        this.institucionPrevisionComision = institucionPrevisionComision;
    }

    public float getInstitucionPrevisionPorcentajeDescuento() {
        return institucionPrevisionPorcentajeDescuento;
    }

    public void setInstitucionPrevisionPorcentajeDescuento(float institucionPrevisionPorcentajeDescuento) {
        this.institucionPrevisionPorcentajeDescuento = institucionPrevisionPorcentajeDescuento;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public Cuenta getInstitucionPrevisionIdCuenta() {
        return institucionPrevisionIdCuenta;
    }

    public void setInstitucionPrevisionIdCuenta(Cuenta institucionPrevisionIdCuenta) {
        this.institucionPrevisionIdCuenta = institucionPrevisionIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (institucionPrevisionId != null ? institucionPrevisionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstitucionPrevision)) {
            return false;
        }
        InstitucionPrevision other = (InstitucionPrevision) object;
        if ((this.institucionPrevisionId == null && other.institucionPrevisionId != null) || (this.institucionPrevisionId != null && !this.institucionPrevisionId.equals(other.institucionPrevisionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.InstitucionPrevision[ institucionPrevisionId=" + institucionPrevisionId + " ]";
    }
    
}
