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
@Table(name = "tipo_plan_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPlanCuenta.findAll", query = "SELECT t FROM TipoPlanCuenta t"),
    @NamedQuery(name = "TipoPlanCuenta.findByTipoPlanCuentaId", query = "SELECT t FROM TipoPlanCuenta t WHERE t.tipoPlanCuentaId = :tipoPlanCuentaId"),
    @NamedQuery(name = "TipoPlanCuenta.findByTipoPlanCuentaNombre", query = "SELECT t FROM TipoPlanCuenta t WHERE t.tipoPlanCuentaNombre = :tipoPlanCuentaNombre")})
public class TipoPlanCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_plan_cuenta_id")
    private Integer tipoPlanCuentaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_plan_cuenta_nombre")
    private String tipoPlanCuentaNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planCuentaSubTipoIdTipoPlan")
    private List<PlanCuentaSubTipo> planCuentaSubTipoList;

    public TipoPlanCuenta() {
    }

    public TipoPlanCuenta(Integer tipoPlanCuentaId) {
        this.tipoPlanCuentaId = tipoPlanCuentaId;
    }

    public TipoPlanCuenta(Integer tipoPlanCuentaId, String tipoPlanCuentaNombre) {
        this.tipoPlanCuentaId = tipoPlanCuentaId;
        this.tipoPlanCuentaNombre = tipoPlanCuentaNombre;
    }

    public Integer getTipoPlanCuentaId() {
        return tipoPlanCuentaId;
    }

    public void setTipoPlanCuentaId(Integer tipoPlanCuentaId) {
        this.tipoPlanCuentaId = tipoPlanCuentaId;
    }

    public String getTipoPlanCuentaNombre() {
        return tipoPlanCuentaNombre;
    }

    public void setTipoPlanCuentaNombre(String tipoPlanCuentaNombre) {
        this.tipoPlanCuentaNombre = tipoPlanCuentaNombre;
    }

    @XmlTransient
    public List<PlanCuentaSubTipo> getPlanCuentaSubTipoList() {
        return planCuentaSubTipoList;
    }

    public void setPlanCuentaSubTipoList(List<PlanCuentaSubTipo> planCuentaSubTipoList) {
        this.planCuentaSubTipoList = planCuentaSubTipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoPlanCuentaId != null ? tipoPlanCuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPlanCuenta)) {
            return false;
        }
        TipoPlanCuenta other = (TipoPlanCuenta) object;
        return (this.tipoPlanCuentaId != null || other.tipoPlanCuentaId == null) && (this.tipoPlanCuentaId == null || this.tipoPlanCuentaId.equals(other.tipoPlanCuentaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoPlanCuenta[ tipoPlanCuentaId=" + tipoPlanCuentaId + " ]";
    }
    
}
