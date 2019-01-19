/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "cartola_banco", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CartolaBanco.findAll", query = "SELECT c FROM CartolaBanco c")
    , @NamedQuery(name = "CartolaBanco.findByCartolaBancoId", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoId = :cartolaBancoId")
    , @NamedQuery(name = "CartolaBanco.findByEmpresaBetweenDates", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoIdCuentaBancaria.cuentaBancariaIdEmpresa = :cuentaBancariaIdEmpresa AND c.cartolaBancoFechaInicial BETWEEN :from AND :to ORDER BY c.cartolaBancoFechaInicial ASC")
    , @NamedQuery(name = "CartolaBanco.findByCuentaBancoBetweenDates", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoIdCuentaBancaria = :cartolaBancoIdCuentaBancaria AND c.cartolaBancoFechaInicial BETWEEN :from AND :to ORDER BY c.cartolaBancoFechaInicial ASC")
    , @NamedQuery(name = "CartolaBanco.findByCuentaBancoFecha", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoIdCuentaBancaria = :cartolaBancoIdCuentaBancaria AND c.cartolaBancoFechaInicial BETWEEN :from AND :to ORDER BY c.cartolaBancoFechaFinal DESC")
    , @NamedQuery(name = "CartolaBanco.findByCartolaBancoNumero", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoNumero = :cartolaBancoNumero")
    , @NamedQuery(name = "CartolaBanco.findByCartolaBancoFechaInicial", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoFechaInicial = :cartolaBancoFechaInicial")
    , @NamedQuery(name = "CartolaBanco.findByCartolaBancoFechaFinal", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoFechaFinal = :cartolaBancoFechaFinal")
    , @NamedQuery(name = "CartolaBanco.findByCartolaBancoSaldoInicial", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoSaldoInicial = :cartolaBancoSaldoInicial")
    , @NamedQuery(name = "CartolaBanco.findByCartolaBancoSaldoFinal", query = "SELECT c FROM CartolaBanco c WHERE c.cartolaBancoSaldoFinal = :cartolaBancoSaldoFinal")})
public class CartolaBanco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cartola_banco_id")
    private Integer cartolaBancoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cartola_banco_numero")
    private int cartolaBancoNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cartola_banco_fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date cartolaBancoFechaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cartola_banco_fecha_final")
    @Temporal(TemporalType.DATE)
    private Date cartolaBancoFechaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cartola_banco_saldo_inicial")
    private int cartolaBancoSaldoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cartola_banco_saldo_final")
    private int cartolaBancoSaldoFinal;
    @JoinColumn(name = "cartola_banco_id_cuenta_bancaria", referencedColumnName = "cuenta_bancaria_id")
    @ManyToOne(optional = false)
    private CuentaBancaria cartolaBancoIdCuentaBancaria;
    @OrderBy("detalleCartolaFechaMovimiento ASC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleCartolaCartolaBancoId")
    private List<DetalleCartola> detalleCartolaList;

    public CartolaBanco() {
    }

    public CartolaBanco(Integer cartolaBancoId) {
        this.cartolaBancoId = cartolaBancoId;
    }

    public CartolaBanco(Integer cartolaBancoId, int cartolaBancoNumero, Date cartolaBancoFechaInicial, Date cartolaBancoFechaFinal, int cartolaBancoSaldoInicial, int cartolaBancoSaldoFinal) {
        this.cartolaBancoId = cartolaBancoId;
        this.cartolaBancoNumero = cartolaBancoNumero;
        this.cartolaBancoFechaInicial = cartolaBancoFechaInicial;
        this.cartolaBancoFechaFinal = cartolaBancoFechaFinal;
        this.cartolaBancoSaldoInicial = cartolaBancoSaldoInicial;
        this.cartolaBancoSaldoFinal = cartolaBancoSaldoFinal;
    }

    public Integer getCartolaBancoId() {
        return cartolaBancoId;
    }

    public void setCartolaBancoId(Integer cartolaBancoId) {
        this.cartolaBancoId = cartolaBancoId;
    }

    public int getCartolaBancoNumero() {
        return cartolaBancoNumero;
    }

    public void setCartolaBancoNumero(int cartolaBancoNumero) {
        this.cartolaBancoNumero = cartolaBancoNumero;
    }

    public Date getCartolaBancoFechaInicial() {
        return cartolaBancoFechaInicial;
    }

    public void setCartolaBancoFechaInicial(Date cartolaBancoFechaInicial) {
        this.cartolaBancoFechaInicial = cartolaBancoFechaInicial;
    }

    public Date getCartolaBancoFechaFinal() {
        return cartolaBancoFechaFinal;
    }

    public void setCartolaBancoFechaFinal(Date cartolaBancoFechaFinal) {
        this.cartolaBancoFechaFinal = cartolaBancoFechaFinal;
    }

    public int getCartolaBancoSaldoInicial() {
        return cartolaBancoSaldoInicial;
    }

    public void setCartolaBancoSaldoInicial(int cartolaBancoSaldoInicial) {
        this.cartolaBancoSaldoInicial = cartolaBancoSaldoInicial;
    }

    public int getCartolaBancoSaldoFinal() {
        return cartolaBancoSaldoFinal;
    }

    public void setCartolaBancoSaldoFinal(int cartolaBancoSaldoFinal) {
        this.cartolaBancoSaldoFinal = cartolaBancoSaldoFinal;
    }

    public CuentaBancaria getCartolaBancoIdCuentaBancaria() {
        return cartolaBancoIdCuentaBancaria;
    }

    public void setCartolaBancoIdCuentaBancaria(CuentaBancaria cartolaBancoIdCuentaBancaria) {
        this.cartolaBancoIdCuentaBancaria = cartolaBancoIdCuentaBancaria;
    }

    @XmlTransient
    public List<DetalleCartola> getDetalleCartolaList() {
        return detalleCartolaList;
    }

    public void setDetalleCartolaList(List<DetalleCartola> detalleCartolaList) {
        this.detalleCartolaList = detalleCartolaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartolaBancoId != null ? cartolaBancoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartolaBanco)) {
            return false;
        }
        CartolaBanco other = (CartolaBanco) object;
        if ((this.cartolaBancoId == null && other.cartolaBancoId != null) || (this.cartolaBancoId != null && !this.cartolaBancoId.equals(other.cartolaBancoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CartolaBanco[ cartolaBancoId=" + cartolaBancoId + " ]";
    }
    
}
