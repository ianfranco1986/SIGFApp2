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
@Table(name = "cuenta_mayor", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaMayor.findAll", query = "SELECT c FROM CuentaMayor c"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorId", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorId = :cuentaMayorId"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorNombre", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorNombre = :cuentaMayorNombre"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorCompras", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorCompras = :cuentaMayorCompras"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorHonorarios", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorHonorarios = :cuentaMayorHonorarios"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorRemuneraciones", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorRemuneraciones = :cuentaMayorRemuneraciones"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorTesoreria", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorTesoreria = :cuentaMayorTesoreria"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorPresupuesto", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorPresupuesto = :cuentaMayorPresupuesto"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorVentas", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorVentas = :cuentaMayorVentas"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorBanco", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorBanco = :cuentaMayorBanco"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorActivosFijos", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorActivosFijos = :cuentaMayorActivosFijos")})
public class CuentaMayor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_mayor_id")
    private Integer cuentaMayorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cuenta_mayor_nombre")
    private String cuentaMayorNombre;
    @Column(name = "cuenta_mayor_compras")
    private Boolean cuentaMayorCompras;
    @Column(name = "cuenta_mayor_honorarios")
    private Boolean cuentaMayorHonorarios;
    @Column(name = "cuenta_mayor_remuneraciones")
    private Boolean cuentaMayorRemuneraciones;
    @Column(name = "cuenta_mayor_tesoreria")
    private Boolean cuentaMayorTesoreria;
    @Column(name = "cuenta_mayor_presupuesto")
    private Boolean cuentaMayorPresupuesto;
    @Column(name = "cuenta_mayor_ventas")
    private Boolean cuentaMayorVentas;
    @Column(name = "cuenta_mayor_banco")
    private Boolean cuentaMayorBanco;
    @Column(name = "cuenta_mayor_activos_fijos")
    private Boolean cuentaMayorActivosFijos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraCuentaMayorId")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anticipoCuentaMayorId")
    private List<Anticipo> anticipoList;
    @JoinColumn(name = "cuenta_mayor_sub_tipo_id", referencedColumnName = "plan_cuenta_sub_tipo_id")
    @ManyToOne(optional = false)
    private PlanCuentaSubTipo cuentaMayorSubTipoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaCuentaMayorId")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "honorarioCuentaMayorId")
    private List<Honorario> honorarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraPetroleoCuentaMayorId")
    private List<CompraPetroleo> compraPetroleoList;

    public CuentaMayor() {
    }

    public CuentaMayor(Integer cuentaMayorId) {
        this.cuentaMayorId = cuentaMayorId;
    }

    public CuentaMayor(Integer cuentaMayorId, String cuentaMayorNombre) {
        this.cuentaMayorId = cuentaMayorId;
        this.cuentaMayorNombre = cuentaMayorNombre;
    }

    public Integer getCuentaMayorId() {
        return cuentaMayorId;
    }

    public void setCuentaMayorId(Integer cuentaMayorId) {
        this.cuentaMayorId = cuentaMayorId;
    }

    public String getCuentaMayorNombre() {
        return cuentaMayorNombre;
    }

    public void setCuentaMayorNombre(String cuentaMayorNombre) {
        this.cuentaMayorNombre = cuentaMayorNombre;
    }

    public Boolean getCuentaMayorCompras() {
        return cuentaMayorCompras;
    }

    public void setCuentaMayorCompras(Boolean cuentaMayorCompras) {
        this.cuentaMayorCompras = cuentaMayorCompras;
    }

    public Boolean getCuentaMayorHonorarios() {
        return cuentaMayorHonorarios;
    }

    public void setCuentaMayorHonorarios(Boolean cuentaMayorHonorarios) {
        this.cuentaMayorHonorarios = cuentaMayorHonorarios;
    }

    public Boolean getCuentaMayorRemuneraciones() {
        return cuentaMayorRemuneraciones;
    }

    public void setCuentaMayorRemuneraciones(Boolean cuentaMayorRemuneraciones) {
        this.cuentaMayorRemuneraciones = cuentaMayorRemuneraciones;
    }

    public Boolean getCuentaMayorTesoreria() {
        return cuentaMayorTesoreria;
    }

    public void setCuentaMayorTesoreria(Boolean cuentaMayorTesoreria) {
        this.cuentaMayorTesoreria = cuentaMayorTesoreria;
    }

    public Boolean getCuentaMayorPresupuesto() {
        return cuentaMayorPresupuesto;
    }

    public void setCuentaMayorPresupuesto(Boolean cuentaMayorPresupuesto) {
        this.cuentaMayorPresupuesto = cuentaMayorPresupuesto;
    }

    public Boolean getCuentaMayorVentas() {
        return cuentaMayorVentas;
    }

    public void setCuentaMayorVentas(Boolean cuentaMayorVentas) {
        this.cuentaMayorVentas = cuentaMayorVentas;
    }

    public Boolean getCuentaMayorBanco() {
        return cuentaMayorBanco;
    }

    public void setCuentaMayorBanco(Boolean cuentaMayorBanco) {
        this.cuentaMayorBanco = cuentaMayorBanco;
    }

    public Boolean getCuentaMayorActivosFijos() {
        return cuentaMayorActivosFijos;
    }

    public void setCuentaMayorActivosFijos(Boolean cuentaMayorActivosFijos) {
        this.cuentaMayorActivosFijos = cuentaMayorActivosFijos;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<Anticipo> getAnticipoList() {
        return anticipoList;
    }

    public void setAnticipoList(List<Anticipo> anticipoList) {
        this.anticipoList = anticipoList;
    }

    public PlanCuentaSubTipo getCuentaMayorSubTipoId() {
        return cuentaMayorSubTipoId;
    }

    public void setCuentaMayorSubTipoId(PlanCuentaSubTipo cuentaMayorSubTipoId) {
        this.cuentaMayorSubTipoId = cuentaMayorSubTipoId;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Honorario> getHonorarioList() {
        return honorarioList;
    }

    public void setHonorarioList(List<Honorario> honorarioList) {
        this.honorarioList = honorarioList;
    }

    @XmlTransient
    public List<CompraPetroleo> getCompraPetroleoList() {
        return compraPetroleoList;
    }

    public void setCompraPetroleoList(List<CompraPetroleo> compraPetroleoList) {
        this.compraPetroleoList = compraPetroleoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaMayorId != null ? cuentaMayorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaMayor)) {
            return false;
        }
        CuentaMayor other = (CuentaMayor) object;
        if ((this.cuentaMayorId == null && other.cuentaMayorId != null) || (this.cuentaMayorId != null && !this.cuentaMayorId.equals(other.cuentaMayorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CuentaMayor[ cuentaMayorId=" + cuentaMayorId + " ]";
    }
    
}
