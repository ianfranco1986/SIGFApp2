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
@Table(name = "plan_cuenta_sub_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanCuentaSubTipo.findAll", query = "SELECT p FROM PlanCuentaSubTipo p ORDER BY p.planCuentaSubTipoId"),
    @NamedQuery(name = "PlanCuentaSubTipo.findByPlanCuentaSubTipoId", query = "SELECT p FROM PlanCuentaSubTipo p WHERE p.planCuentaSubTipoId = :planCuentaSubTipoId"),
    @NamedQuery(name = "PlanCuentaSubTipo.findByTipoPlan", query = "SELECT p FROM PlanCuentaSubTipo p WHERE p.planCuentaSubTipoIdTipoPlan = :planCuentaSubTipoIdTipoPlan ORDER BY p.planCuentaSubTipoCodigo ASC"),
    @NamedQuery(name = "PlanCuentaSubTipo.findByPlanCuentaSubTipoNombre", query = "SELECT p FROM PlanCuentaSubTipo p WHERE p.planCuentaSubTipoNombre = :planCuentaSubTipoNombre")})
public class PlanCuentaSubTipo extends BaseEntity implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "plan_cuenta_sub_tipo_codigo_tipo", nullable = false)
    private int planCuentaSubTipoCodigoTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plan_cuenta_sub_tipo_codigo", nullable = false)
    private String planCuentaSubTipoCodigo;
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

    public PlanCuentaSubTipo(Integer planCuentaSubTipoId, String planCuentaSubTipoNombre, int planCuentaSubTipoCodigoTipo, String planCuentaSubTipoCodigo) {
        this.planCuentaSubTipoId = planCuentaSubTipoId;
        this.planCuentaSubTipoNombre = planCuentaSubTipoNombre;
        this.planCuentaSubTipoCodigo = planCuentaSubTipoCodigo;
        this.planCuentaSubTipoCodigoTipo = planCuentaSubTipoCodigoTipo;
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

    public String getPlanCuentaSubTipoCodigo() {
        return planCuentaSubTipoCodigo;
    }

    public void setPlanCuentaSubTipoCodigo(String planCuentaSubTipoCodigo) {
        this.planCuentaSubTipoCodigo = planCuentaSubTipoCodigo;
    }

    public void setPlanCuentaSubTipoCodigoTipo(int planCuentaSubTipoCodigoTipo) {
        this.planCuentaSubTipoCodigoTipo = planCuentaSubTipoCodigoTipo;
    }

    public int getPlanCuentaSubTipoCodigoTipo() {
        return planCuentaSubTipoCodigoTipo;
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
        return (this.planCuentaSubTipoId != null || other.planCuentaSubTipoId == null) && (this.planCuentaSubTipoId == null || this.planCuentaSubTipoId.equals(other.planCuentaSubTipoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PlanCuentaSubTipo[ planCuentaSubTipoId=" + planCuentaSubTipoId + " ]";
    }

}
