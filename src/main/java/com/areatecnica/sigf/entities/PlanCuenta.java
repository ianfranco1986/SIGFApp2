/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "plan_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanCuenta.findAll", query = "SELECT p FROM PlanCuenta p"),
    @NamedQuery(name = "PlanCuenta.findByPlanCuentaId", query = "SELECT p FROM PlanCuenta p WHERE p.planCuentaId = :planCuentaId")})
public class PlanCuenta extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "plan_cuenta_id")
    private Integer planCuentaId;
    @JoinColumn(name = "plan_cuenta_empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa planCuentaEmpresaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planCuentaSubTipoIdPlan", fetch = FetchType.LAZY)
    private List<PlanCuentaSubTipo> planCuentaSubTipoList;

    public PlanCuenta() {
    }

    public PlanCuenta(Integer planCuentaId) {
        this.planCuentaId = planCuentaId;
    }

    public Integer getPlanCuentaId() {
        return planCuentaId;
    }

    public void setPlanCuentaId(Integer planCuentaId) {
        this.planCuentaId = planCuentaId;
    }

    public Empresa getPlanCuentaEmpresaId() {
        return planCuentaEmpresaId;
    }

    public void setPlanCuentaEmpresaId(Empresa planCuentaEmpresaId) {
        this.planCuentaEmpresaId = planCuentaEmpresaId;
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
        hash += (planCuentaId != null ? planCuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanCuenta)) {
            return false;
        }
        PlanCuenta other = (PlanCuenta) object;
        return (this.planCuentaId != null || other.planCuentaId == null) && (this.planCuentaId == null || this.planCuentaId.equals(other.planCuentaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PlanCuenta[ planCuentaId=" + planCuentaId + " ]";
    }
    
}
