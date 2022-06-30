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
@Table(name = "mutual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mutual.findAll", query = "SELECT m FROM Mutual m")
    , @NamedQuery(name = "Mutual.findByMutualId", query = "SELECT m FROM Mutual m WHERE m.mutualId = :mutualId")
    , @NamedQuery(name = "Mutual.findByMutualNombre", query = "SELECT m FROM Mutual m WHERE m.mutualNombre = :mutualNombre")
    , @NamedQuery(name = "Mutual.findByMutualComision", query = "SELECT m FROM Mutual m WHERE m.mutualComision = :mutualComision")
    , @NamedQuery(name = "Mutual.findByMutualPorcentajeDescuento", query = "SELECT m FROM Mutual m WHERE m.mutualPorcentajeDescuento = :mutualPorcentajeDescuento")})
public class Mutual extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mutual_id", nullable = false)
    private Integer mutualId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mutual_nombre", nullable = false, length = 255)
    private String mutualNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mutual_comision", precision = 5, scale = 3)
    private Float mutualComision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mutual_porcentaje_descuento", nullable = false)
    private float mutualPorcentajeDescuento;
    @JoinColumn(name = "mutual_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta mutualIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaIdMutual", fetch = FetchType.LAZY)
    private List<Empresa> empresaList;

    public Mutual() {
    }

    public Mutual(Integer mutualId) {
        this.mutualId = mutualId;
    }

    public Mutual(Integer mutualId, String mutualNombre, float mutualPorcentajeDescuento) {
        this.mutualId = mutualId;
        this.mutualNombre = mutualNombre;
        this.mutualPorcentajeDescuento = mutualPorcentajeDescuento;
    }

    public Integer getMutualId() {
        return mutualId;
    }

    public void setMutualId(Integer mutualId) {
        this.mutualId = mutualId;
    }

    public String getMutualNombre() {
        return mutualNombre;
    }

    public void setMutualNombre(String mutualNombre) {
        this.mutualNombre = mutualNombre;
    }

    public Float getMutualComision() {
        return mutualComision;
    }

    public void setMutualComision(Float mutualComision) {
        this.mutualComision = mutualComision;
    }

    public float getMutualPorcentajeDescuento() {
        return mutualPorcentajeDescuento;
    }

    public void setMutualPorcentajeDescuento(float mutualPorcentajeDescuento) {
        this.mutualPorcentajeDescuento = mutualPorcentajeDescuento;
    }

    public Cuenta getMutualIdCuenta() {
        return mutualIdCuenta;
    }

    public void setMutualIdCuenta(Cuenta mutualIdCuenta) {
        this.mutualIdCuenta = mutualIdCuenta;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mutualId != null ? mutualId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mutual)) {
            return false;
        }
        Mutual other = (Mutual) object;
        return (this.mutualId != null || other.mutualId == null) && (this.mutualId == null || this.mutualId.equals(other.mutualId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Mutual[ mutualId=" + mutualId + " ]";
    }

}
