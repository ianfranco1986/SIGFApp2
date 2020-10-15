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
@Table(name = "plan_cuenta_sub_tipo", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanCuentaSubTipo.findAll", query = "SELECT p FROM PlanCuentaSubTipo p"),
    @NamedQuery(name = "PlanCuentaSubTipo.findByPlanCuentaSubTipoId", query = "SELECT p FROM PlanCuentaSubTipo p WHERE p.planCuentaSubTipoId = :planCuentaSubTipoId"),
    @NamedQuery(name = "PlanCuentaSubTipo.findByPlanCuentaSubTipoNombre", query = "SELECT p FROM PlanCuentaSubTipo p WHERE p.planCuentaSubTipoNombre = :planCuentaSubTipoNombre")})
public class PlanCuentaSubTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "plan_cuenta_sub_tipo_id")
    private Integer planCuentaSubTipoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "plan_cuenta_sub_tipo_nombre")
    private String planCuentaSubTipoNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaMayorSubTipoId")
    private List<CuentaMayor> cuentaMayorList;
    @JoinColumn(name = "plan_cuenta_sub_tipo_id_plan", referencedColumnName = "plan_cuenta_id")
    @ManyToOne(optional = false)
    private PlanCuenta planCuentaSubTipoIdPlan;
    @JoinColumn(name = "plan_cuenta_sub_tipo_id_tipo_plan", referencedColumnName = "tipo_plan_cuenta_id")
    @ManyToOne(optional = false)
    private TipoPlanCuenta planCuentaSubTipoIdTipoPlan;

    public PlanCuentaSubTipo() {
    }

    public PlanCuentaSubTipo(Integer planCuentaSubTipoId) {
        this.planCuentaSubTipoId = planCuentaSubTipoId;
    }

    public PlanCuentaSubTipo(Integer planCuentaSubTipoId, String planCuentaSubTipoNombre) {
        this.planCuentaSubTipoId = planCuentaSubTipoId;
        this.planCuentaSubTipoNombre = planCuentaSubTipoNombre;
    }

    public Integer getPlanCuentaSubTipoId() {
        return planCuentaSubTipoId;
    }

    public void setPlanCuentaSubTipoId(Integer planCuentaSubTipoId) {
        this.planCuentaSubTipoId = planCuentaSubTipoId;
    }

    public String getPlanCuentaSubTipoNombre() {
        return planCuentaSubTipoNombre;
    }

    public void setPlanCuentaSubTipoNombre(String planCuentaSubTipoNombre) {
        this.planCuentaSubTipoNombre = planCuentaSubTipoNombre;
    }

    @XmlTransient
    public List<CuentaMayor> getCuentaMayorList() {
        return cuentaMayorList;
    }

    public void setCuentaMayorList(List<CuentaMayor> cuentaMayorList) {
        this.cuentaMayorList = cuentaMayorList;
    }

    public PlanCuenta getPlanCuentaSubTipoIdPlan() {
        return planCuentaSubTipoIdPlan;
    }

    public void setPlanCuentaSubTipoIdPlan(PlanCuenta planCuentaSubTipoIdPlan) {
        this.planCuentaSubTipoIdPlan = planCuentaSubTipoIdPlan;
    }

    public TipoPlanCuenta getPlanCuentaSubTipoIdTipoPlan() {
        return planCuentaSubTipoIdTipoPlan;
    }

    public void setPlanCuentaSubTipoIdTipoPlan(TipoPlanCuenta planCuentaSubTipoIdTipoPlan) {
        this.planCuentaSubTipoIdTipoPlan = planCuentaSubTipoIdTipoPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planCuentaSubTipoId != null ? planCuentaSubTipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanCuentaSubTipo)) {
            return false;
        }
        PlanCuentaSubTipo other = (PlanCuentaSubTipo) object;
        if ((this.planCuentaSubTipoId == null && other.planCuentaSubTipoId != null) || (this.planCuentaSubTipoId != null && !this.planCuentaSubTipoId.equals(other.planCuentaSubTipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PlanCuentaSubTipo[ planCuentaSubTipoId=" + planCuentaSubTipoId + " ]";
    }
    
}
