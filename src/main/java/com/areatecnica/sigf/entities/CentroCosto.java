/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "centro_costo", catalog = "sigfdb", schema = "")
@EntityListeners(AuditListener.class)
@NamedQueries({
    @NamedQuery(name = "CentroCosto.findAll", query = "SELECT c FROM CentroCosto c"),
    @NamedQuery(name = "CentroCosto.findByCentroCostoId", query = "SELECT c FROM CentroCosto c WHERE c.centroCostoId = :centroCostoId"),
    @NamedQuery(name = "CentroCosto.findByCentroCostoIdCuenta", query = "SELECT c FROM CentroCosto c WHERE c.centroCostoIdCuenta = :centroCostoIdCuenta"),
    @NamedQuery(name = "CentroCosto.findByCentroCostoNombre", query = "SELECT c FROM CentroCosto c WHERE c.centroCostoNombre = :centroCostoNombre")})
public class CentroCosto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "centro_costo_id")
    private Integer centroCostoId;
    @JoinColumn(name = "centro_costo_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta centroCostoIdCuenta;
    @Basic(optional = false)
    @Column(name = "centro_costo_nombre")
    private String centroCostoNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voucherMovimientoCentroCostoId")
    private List<VoucherMovimiento> voucherMovimientoList;

    public CentroCosto() {
    }

    public CentroCosto(Integer centroCostoId) {
        this.centroCostoId = centroCostoId;
    }

    public CentroCosto(Integer centroCostoId, String centroCostoNombre) {
        this.centroCostoId = centroCostoId;
        this.centroCostoNombre = centroCostoNombre;
    }

    public Integer getCentroCostoId() {
        return centroCostoId;
    }

    public void setCentroCostoId(Integer centroCostoId) {
        this.centroCostoId = centroCostoId;
    }

    public Cuenta getCentroCostoIdCuenta() {
        return centroCostoIdCuenta;
    }

    public void setCentroCostoIdCuenta(Cuenta centroCostoIdCuenta) {
        this.centroCostoIdCuenta = centroCostoIdCuenta;
    }

    public String getCentroCostoNombre() {
        return centroCostoNombre;
    }

    public void setCentroCostoNombre(String centroCostoNombre) {
        this.centroCostoNombre = centroCostoNombre;
    }

    public List<VoucherMovimiento> getVoucherMovimientoList() {
        return voucherMovimientoList;
    }

    public void setVoucherMovimientoList(List<VoucherMovimiento> voucherMovimientoList) {
        this.voucherMovimientoList = voucherMovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (centroCostoId != null ? centroCostoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroCosto)) {
            return false;
        }
        CentroCosto other = (CentroCosto) object;
        if ((this.centroCostoId == null && other.centroCostoId != null) || (this.centroCostoId != null && !this.centroCostoId.equals(other.centroCostoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CentroCosto[ centroCostoId=" + centroCostoId + " ]";
    }
    
}
