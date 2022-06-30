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
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@EntityListeners(AuditListener.class)
@Cacheable(false)
@Table(name = "cuenta_mayor")
@NamedQueries({
    @NamedQuery(name = "CuentaMayor.findAll", query = "SELECT c FROM CuentaMayor c ORDER BY c.cuentaMayorSubTipoId.planCuentaSubTipoIdTipoPlan.tipoPlanCuentaId, c.cuentaMayorSubTipoId.planCuentaSubTipoCodigo"),
    @NamedQuery(name = "CuentaMayor.findById", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorId =:cuentaMayorId ORDER BY c.cuentaMayorId"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorSubTipoId", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorSubTipoId = :cuentaMayorSubTipoId"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorNombre", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorNombre = :cuentaMayorNombre"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorDescripcion", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorDescripcion = :cuentaMayorDescripcion"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorCc", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorCc = :cuentaMayorCc"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorCodigo", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorCodigo = :cuentaMayorCodigo"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorCompras", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorCompras = true"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorHonorarios", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorHonorarios = true"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorRemuneraciones", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorRemuneraciones = true"),
    @NamedQuery(name = "CuentaMayor.findBySubTipo", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorSubTipoId = :cuentaMayorSubTipoId ORDER BY c.cuentaMayorSubTipoId.planCuentaSubTipoIdTipoPlan.tipoPlanCuentaId"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorTesoreria", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorTesoreria = true"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorPresupuesto", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorPresupuesto = true"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorVentas", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorVentas = true"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorBanco", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorBanco = true"),
    @NamedQuery(name = "CuentaMayor.findByCuentaMayorActivosFijos", query = "SELECT c FROM CuentaMayor c WHERE c.cuentaMayorActivosFijos = true")})
public class CuentaMayor extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_mayor_id")
    private Integer cuentaMayorId;
    @Basic(optional = false)
    @Column(name = "cuenta_mayor_nombre")
    private String cuentaMayorNombre;
    @Column(name = "cuenta_mayor_descripcion")
    private String cuentaMayorDescripcion;
    @Column(name = "cuenta_mayor_cc")
    private Boolean cuentaMayorCc;
    @Basic(optional = false)
    @Column(name = "cuenta_mayor_codigo")
    private String cuentaMayorCodigo;
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
    @JoinColumn(name = "cuenta_mayor_sub_tipo_id", referencedColumnName = "plan_cuenta_sub_tipo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlanCuentaSubTipo cuentaMayorSubTipoId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voucherMovimientoCuentaId", fetch = FetchType.LAZY)
    private List<VoucherMovimiento> voucherMovimientoList;
    @JoinColumn(name = "cuenta_mayor_unica_id", referencedColumnName = "cuenta_unica_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CuentaUnica cuentaMayorUnicaId;

    public CuentaMayor() {
    }

    public CuentaMayor(Integer cuentaMayorId) {
        this.cuentaMayorId = cuentaMayorId;
    }

    public CuentaMayor(Integer cuentaMayorId, String cuentaMayorNombre, String cuentaMayorCodigo) {
        this.cuentaMayorId = cuentaMayorId;
        this.cuentaMayorNombre = cuentaMayorNombre;
        this.cuentaMayorCodigo = cuentaMayorCodigo;
    }

    public Integer getCuentaMayorId() {
        return cuentaMayorId;
    }

    public void setCuentaMayorId(Integer cuentaMayorId) {
        this.cuentaMayorId = cuentaMayorId;
    }

    public PlanCuentaSubTipo getCuentaMayorSubTipoId() {
        return cuentaMayorSubTipoId;
    }

    public void setCuentaMayorSubTipoId(PlanCuentaSubTipo cuentaMayorSubTipoId) {
        this.cuentaMayorSubTipoId = cuentaMayorSubTipoId;
    }

    public String getCuentaMayorNombre() {
        return cuentaMayorNombre;
    }

    public void setCuentaMayorNombre(String cuentaMayorNombre) {
        this.cuentaMayorNombre = cuentaMayorNombre;
    }

    public String getCuentaMayorDescripcion() {
        return cuentaMayorDescripcion;
    }

    public void setCuentaMayorDescripcion(String cuentaMayorDescripcion) {
        this.cuentaMayorDescripcion = cuentaMayorDescripcion;
    }

    public Boolean getCuentaMayorCc() {
        return cuentaMayorCc;
    }

    public void setCuentaMayorCc(Boolean cuentaMayorCc) {
        this.cuentaMayorCc = cuentaMayorCc;
    }

    public String getCuentaMayorCodigo() {
        return cuentaMayorCodigo;
    }

    public void setCuentaMayorCodigo(String cuentaMayorCodigo) {
        this.cuentaMayorCodigo = cuentaMayorCodigo;
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

    public List<VoucherMovimiento> getVoucherMovimientoList() {
        return voucherMovimientoList;
    }

    public void setVoucherMovimientoList(List<VoucherMovimiento> voucherMovimientoList) {
        this.voucherMovimientoList = voucherMovimientoList;
    }

    public CuentaUnica getCuentaMayorUnicaId() {
        return cuentaMayorUnicaId;
    }

    public void setCuentaMayorUnicaId(CuentaUnica cuentaMayorUnicaId) {
        this.cuentaMayorUnicaId = cuentaMayorUnicaId;
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
