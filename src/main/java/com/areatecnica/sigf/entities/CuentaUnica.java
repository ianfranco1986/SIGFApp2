/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

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
@Table(name = "cuenta_unica")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaUnica.findAll", query = "SELECT c FROM CuentaUnica c ORDER BY c.cuentaUnicaNombre ")}
    )
public class CuentaUnica extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_unica_id")
    private Integer cuentaUnicaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cuenta_unica_nombre")
    private String cuentaUnicaNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaMayorUnicaId")
    private List<CuentaMayor> cuentaMayorList;
    
    public CuentaUnica() {
    }

    public CuentaUnica(Integer cuentaUnicaId) {
        this.cuentaUnicaId = cuentaUnicaId;
    }

    public CuentaUnica(Integer cuentaUnicaId, String cuentaUnicaNombre) {
        this.cuentaUnicaId = cuentaUnicaId;
        this.cuentaUnicaNombre = cuentaUnicaNombre;
    }

    public Integer getCuentaUnicaId() {
        return cuentaUnicaId;
    }

    public void setCuentaUnicaId(Integer cuentaUnicaId) {
        this.cuentaUnicaId = cuentaUnicaId;
    }

    public String getCuentaUnicaNombre() {
        return cuentaUnicaNombre;
    }

    public void setCuentaUnicaNombre(String cuentaUnicaNombre) {
        this.cuentaUnicaNombre = cuentaUnicaNombre;
    }

    @XmlTransient
    public List<CuentaMayor> getCuentaMayorList() {
        return cuentaMayorList;
    }

    public void setCuentaMayorList(List<CuentaMayor> cuentaMayorList) {
        this.cuentaMayorList = cuentaMayorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaUnicaId != null ? cuentaUnicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaUnica)) {
            return false;
        }
        CuentaUnica other = (CuentaUnica) object;
        return (this.cuentaUnicaId != null || other.cuentaUnicaId == null) && (this.cuentaUnicaId == null || this.cuentaUnicaId.equals(other.cuentaUnicaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CuentaUnica[ cuentaUnicaId=" + cuentaUnicaId + " ]";
    }
    
}
