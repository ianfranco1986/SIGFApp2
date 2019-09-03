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
@Table(name = "tipo_plan_cuenta", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPlanCuenta.findAll", query = "SELECT t FROM TipoPlanCuenta t")
    , @NamedQuery(name = "TipoPlanCuenta.findByTipoPlanCuentaId", query = "SELECT t FROM TipoPlanCuenta t WHERE t.tipoPlanCuentaId = :tipoPlanCuentaId")
    , @NamedQuery(name = "TipoPlanCuenta.findByTipoPlanCuentaNombre", query = "SELECT t FROM TipoPlanCuenta t WHERE t.tipoPlanCuentaNombre = :tipoPlanCuentaNombre")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planCuentaTipoId")
    private List<PlanCuenta> planCuentaList;

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
    public List<PlanCuenta> getPlanCuentaList() {
        return planCuentaList;
    }

    public void setPlanCuentaList(List<PlanCuenta> planCuentaList) {
        this.planCuentaList = planCuentaList;
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
        if ((this.tipoPlanCuentaId == null && other.tipoPlanCuentaId != null) || (this.tipoPlanCuentaId != null && !this.tipoPlanCuentaId.equals(other.tipoPlanCuentaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoPlanCuenta[ tipoPlanCuentaId=" + tipoPlanCuentaId + " ]";
    }
    
}
