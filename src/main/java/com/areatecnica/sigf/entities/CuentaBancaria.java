/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "cuenta_bancaria", catalog = "sigfdb", schema = "")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaBancaria.findAll", query = "SELECT c FROM CuentaBancaria c"),
    @NamedQuery(name = "CuentaBancaria.findByCuentaBancariaId", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaId = :cuentaBancariaId"),
    @NamedQuery(name = "CuentaBancaria.findByCuentaBancariaNumero", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaNumero = :cuentaBancariaNumero"),
    @NamedQuery(name = "CuentaBancaria.findByCuenta", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaIdEmpresa.empresaIdCuenta = :empresaIdCuenta ORDER BY c.cuentaBancariaIdEmpresa.empresaNombre ASC"),
    @NamedQuery(name = "CuentaBancaria.findByCuentaBancariaNombreTitular", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaNombreTitular = :cuentaBancariaNombreTitular"),
    @NamedQuery(name = "CuentaBancaria.findByCuentaBancariaRutTitular", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaRutTitular = :cuentaBancariaRutTitular"),
    @NamedQuery(name = "CuentaBancaria.findByCuentaBancariaActiva", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaActiva = :cuentaBancariaActiva"),
    @NamedQuery(name = "CuentaBancaria.findByCuentaBancariaDescripcion", query = "SELECT c FROM CuentaBancaria c WHERE c.cuentaBancariaDescripcion = :cuentaBancariaDescripcion")})
public class CuentaBancaria extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_bancaria_id")
    private Integer cuentaBancariaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_bancaria_numero")
    private int cuentaBancariaNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "cuenta_bancaria_nombre_titular")
    private String cuentaBancariaNombreTitular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "cuenta_bancaria_rut_titular")
    private String cuentaBancariaRutTitular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_bancaria_activa")
    private boolean cuentaBancariaActiva;
    @Size(max = 45)
    @Column(name = "cuenta_bancaria_descripcion")
    private String cuentaBancariaDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancoProcesoIdBanco")
    private List<CuentaBancoProceso> cuentaBancoProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartolaBancoIdCuentaBancaria")
    private List<CartolaBanco> cartolaBancoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoMesCuentaBancoId")
    private List<MovimientoMes> movimientoMesList;
    @JoinColumn(name = "cuenta_bancaria_id_banco", referencedColumnName = "banco_id")
    @ManyToOne(optional = false)
    private Banco cuentaBancariaIdBanco;
    @JoinColumn(name = "cuenta_bancaria_id_empresa", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa cuentaBancariaIdEmpresa;
    @JoinColumn(name = "cuenta_bancaria_id_tipo_cuenta", referencedColumnName = "tipo_cuenta_banco_id")
    @ManyToOne(optional = false)
    private TipoCuentaBanco cuentaBancariaIdTipoCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleDepositoRecaudacionIdCuenta")
    private List<DetalleDepositoRecaudacion> detalleDepositoRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancoTrabajadorCuenta")
    private List<CuentaBancoTrabajador> cuentaBancoTrabajadorList;

    public CuentaBancaria() {
    }

    public CuentaBancaria(Integer cuentaBancariaId) {
        this.cuentaBancariaId = cuentaBancariaId;
    }

    public CuentaBancaria(Integer cuentaBancariaId, int cuentaBancariaNumero, String cuentaBancariaNombreTitular, String cuentaBancariaRutTitular, boolean cuentaBancariaActiva) {
        this.cuentaBancariaId = cuentaBancariaId;
        this.cuentaBancariaNumero = cuentaBancariaNumero;
        this.cuentaBancariaNombreTitular = cuentaBancariaNombreTitular;
        this.cuentaBancariaRutTitular = cuentaBancariaRutTitular;
        this.cuentaBancariaActiva = cuentaBancariaActiva;
    }

    public Integer getCuentaBancariaId() {
        return cuentaBancariaId;
    }

    public void setCuentaBancariaId(Integer cuentaBancariaId) {
        this.cuentaBancariaId = cuentaBancariaId;
    }

    public int getCuentaBancariaNumero() {
        return cuentaBancariaNumero;
    }

    public void setCuentaBancariaNumero(int cuentaBancariaNumero) {
        this.cuentaBancariaNumero = cuentaBancariaNumero;
    }

    public String getCuentaBancariaNombreTitular() {
        return cuentaBancariaNombreTitular;
    }

    public void setCuentaBancariaNombreTitular(String cuentaBancariaNombreTitular) {
        this.cuentaBancariaNombreTitular = cuentaBancariaNombreTitular;
    }

    public String getCuentaBancariaRutTitular() {
        return cuentaBancariaRutTitular;
    }

    public void setCuentaBancariaRutTitular(String cuentaBancariaRutTitular) {
        this.cuentaBancariaRutTitular = cuentaBancariaRutTitular;
    }

    public boolean getCuentaBancariaActiva() {
        return cuentaBancariaActiva;
    }

    public void setCuentaBancariaActiva(boolean cuentaBancariaActiva) {
        this.cuentaBancariaActiva = cuentaBancariaActiva;
    }

    public String getCuentaBancariaDescripcion() {
        return cuentaBancariaDescripcion;
    }

    public void setCuentaBancariaDescripcion(String cuentaBancariaDescripcion) {
        this.cuentaBancariaDescripcion = cuentaBancariaDescripcion;
    }

    @XmlTransient
    public List<CuentaBancoProceso> getCuentaBancoProcesoList() {
        return cuentaBancoProcesoList;
    }

    public void setCuentaBancoProcesoList(List<CuentaBancoProceso> cuentaBancoProcesoList) {
        this.cuentaBancoProcesoList = cuentaBancoProcesoList;
    }

    @XmlTransient
    public List<CartolaBanco> getCartolaBancoList() {
        return cartolaBancoList;
    }

    public void setCartolaBancoList(List<CartolaBanco> cartolaBancoList) {
        this.cartolaBancoList = cartolaBancoList;
    }

    @XmlTransient
    public List<MovimientoMes> getMovimientoMesList() {
        return movimientoMesList;
    }

    public void setMovimientoMesList(List<MovimientoMes> movimientoMesList) {
        this.movimientoMesList = movimientoMesList;
    }

    public Banco getCuentaBancariaIdBanco() {
        return cuentaBancariaIdBanco;
    }

    public void setCuentaBancariaIdBanco(Banco cuentaBancariaIdBanco) {
        this.cuentaBancariaIdBanco = cuentaBancariaIdBanco;
    }

    public Empresa getCuentaBancariaIdEmpresa() {
        return cuentaBancariaIdEmpresa;
    }

    public void setCuentaBancariaIdEmpresa(Empresa cuentaBancariaIdEmpresa) {
        this.cuentaBancariaIdEmpresa = cuentaBancariaIdEmpresa;
    }

    public TipoCuentaBanco getCuentaBancariaIdTipoCuenta() {
        return cuentaBancariaIdTipoCuenta;
    }

    public void setCuentaBancariaIdTipoCuenta(TipoCuentaBanco cuentaBancariaIdTipoCuenta) {
        this.cuentaBancariaIdTipoCuenta = cuentaBancariaIdTipoCuenta;
    }

    @XmlTransient
    public List<DetalleDepositoRecaudacion> getDetalleDepositoRecaudacionList() {
        return detalleDepositoRecaudacionList;
    }

    public void setDetalleDepositoRecaudacionList(List<DetalleDepositoRecaudacion> detalleDepositoRecaudacionList) {
        this.detalleDepositoRecaudacionList = detalleDepositoRecaudacionList;
    }

    @XmlTransient
    public List<CuentaBancoTrabajador> getCuentaBancoTrabajadorList() {
        return cuentaBancoTrabajadorList;
    }

    public void setCuentaBancoTrabajadorList(List<CuentaBancoTrabajador> cuentaBancoTrabajadorList) {
        this.cuentaBancoTrabajadorList = cuentaBancoTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaBancariaId != null ? cuentaBancariaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBancaria)) {
            return false;
        }
        CuentaBancaria other = (CuentaBancaria) object;
        if ((this.cuentaBancariaId == null && other.cuentaBancariaId != null) || (this.cuentaBancariaId != null && !this.cuentaBancariaId.equals(other.cuentaBancariaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CuentaBancaria[ cuentaBancariaId=" + cuentaBancariaId + " ]";
    }
    
}
