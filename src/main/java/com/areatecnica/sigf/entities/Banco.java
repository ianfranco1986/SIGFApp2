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
@Table(name = "banco")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b")
    , @NamedQuery(name = "Banco.findByBancoId", query = "SELECT b FROM Banco b WHERE b.bancoId = :bancoId")
    , @NamedQuery(name = "Banco.findByBancoNombre", query = "SELECT b FROM Banco b WHERE b.bancoNombre = :bancoNombre")})
public class Banco extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "banco_id", nullable = false)
    private Integer bancoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "banco_nombre", nullable = false, length = 45)
    private String bancoNombre;
    @JoinColumn(name = "banco_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta bancoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancariaIdBanco", fetch = FetchType.LAZY)
    private List<CuentaBancaria> cuentaBancariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleResumenChequeIdBanco", fetch = FetchType.LAZY)
    private List<DetalleResumenCheque> detalleResumenChequeList;

    public Banco() {
    }

    public Banco(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public Banco(Integer bancoId, String bancoNombre) {
        this.bancoId = bancoId;
        this.bancoNombre = bancoNombre;
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public String getBancoNombre() {
        return bancoNombre;
    }

    public void setBancoNombre(String bancoNombre) {
        this.bancoNombre = bancoNombre;
    }

    public Cuenta getBancoIdCuenta() {
        return bancoIdCuenta;
    }

    public void setBancoIdCuenta(Cuenta bancoIdCuenta) {
        this.bancoIdCuenta = bancoIdCuenta;
    }

    @XmlTransient
    public List<CuentaBancaria> getCuentaBancariaList() {
        return cuentaBancariaList;
    }

    public void setCuentaBancariaList(List<CuentaBancaria> cuentaBancariaList) {
        this.cuentaBancariaList = cuentaBancariaList;
    }

    @XmlTransient
    public List<DetalleResumenCheque> getDetalleResumenChequeList() {
        return detalleResumenChequeList;
    }

    public void setDetalleResumenChequeList(List<DetalleResumenCheque> detalleResumenChequeList) {
        this.detalleResumenChequeList = detalleResumenChequeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bancoId != null ? bancoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        return (this.bancoId != null || other.bancoId == null) && (this.bancoId == null || this.bancoId.equals(other.bancoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Banco[ bancoId=" + bancoId + " ]";
    }
    
}
