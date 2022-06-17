/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "movimiento_bancario", catalog = "sigfdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "MovimientoBancario.findAll", query = "SELECT m FROM MovimientoBancario m"),
    @NamedQuery(name = "MovimientoBancario.findByMovimientoBancarioId", query = "SELECT m FROM MovimientoBancario m WHERE m.movimientoBancarioId = :movimientoBancarioId"),
    @NamedQuery(name = "MovimientoBancario.findByMovimientoBancarioFolio", query = "SELECT m FROM MovimientoBancario m WHERE m.movimientoBancarioFolio = :movimientoBancarioFolio"),
    @NamedQuery(name = "MovimientoBancario.findByMovimientoBancarioFecha", query = "SELECT m FROM MovimientoBancario m WHERE m.movimientoBancarioFecha = :movimientoBancarioFecha"),
    @NamedQuery(name = "MovimientoBancario.findByMovimientoBancarioEsDebito", query = "SELECT m FROM MovimientoBancario m WHERE m.movimientoBancarioEsDebito = :movimientoBancarioEsDebito"),
    @NamedQuery(name = "MovimientoBancario.findByMovimientoBancarioMonto", query = "SELECT m FROM MovimientoBancario m WHERE m.movimientoBancarioMonto = :movimientoBancarioMonto"),
    @NamedQuery(name = "MovimientoBancario.findByMovimientoBancarioEstado", query = "SELECT m FROM MovimientoBancario m WHERE m.movimientoBancarioEstado = :movimientoBancarioEstado")})
public class MovimientoBancario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movimiento_bancario_id")
    private Integer movimientoBancarioId;
    @Column(name = "movimiento_bancario_folio")
    private Integer movimientoBancarioFolio;
    @Column(name = "movimiento_bancario_fecha")
    @Temporal(TemporalType.DATE)
    private Date movimientoBancarioFecha;
    @Column(name = "movimiento_bancario_es_debito")
    private Short movimientoBancarioEsDebito;
    @Column(name = "movimiento_bancario_monto")
    private Integer movimientoBancarioMonto;
    @Column(name = "movimiento_bancario_estado")
    private Short movimientoBancarioEstado;
    @JoinColumn(name = "movimiento_bancario_tipo_id", referencedColumnName = "tipo_movimiento_bancario_id")
    @ManyToOne(optional = false)
    private TipoMovimientoBancario movimientoBancarioTipoId;
    @JoinColumn(name = "movimiento_bancario_vmov_id", referencedColumnName = "voucher_movimiento_id")
    @ManyToOne(optional = false)
    private VoucherMovimiento movimientoBancarioVmovId;

    public MovimientoBancario() {
    }

    public MovimientoBancario(Integer movimientoBancarioId) {
        this.movimientoBancarioId = movimientoBancarioId;
    }

    public Integer getMovimientoBancarioId() {
        return movimientoBancarioId;
    }

    public void setMovimientoBancarioId(Integer movimientoBancarioId) {
        this.movimientoBancarioId = movimientoBancarioId;
    }

    public Integer getMovimientoBancarioFolio() {
        return movimientoBancarioFolio;
    }

    public void setMovimientoBancarioFolio(Integer movimientoBancarioFolio) {
        this.movimientoBancarioFolio = movimientoBancarioFolio;
    }

    public Date getMovimientoBancarioFecha() {
        return movimientoBancarioFecha;
    }

    public void setMovimientoBancarioFecha(Date movimientoBancarioFecha) {
        this.movimientoBancarioFecha = movimientoBancarioFecha;
    }

    public Short getMovimientoBancarioEsDebito() {
        return movimientoBancarioEsDebito;
    }

    public void setMovimientoBancarioEsDebito(Short movimientoBancarioEsDebito) {
        this.movimientoBancarioEsDebito = movimientoBancarioEsDebito;
    }

    public Integer getMovimientoBancarioMonto() {
        return movimientoBancarioMonto;
    }

    public void setMovimientoBancarioMonto(Integer movimientoBancarioMonto) {
        this.movimientoBancarioMonto = movimientoBancarioMonto;
    }

    public Short getMovimientoBancarioEstado() {
        return movimientoBancarioEstado;
    }

    public void setMovimientoBancarioEstado(Short movimientoBancarioEstado) {
        this.movimientoBancarioEstado = movimientoBancarioEstado;
    }

    public TipoMovimientoBancario getMovimientoBancarioTipoId() {
        return movimientoBancarioTipoId;
    }

    public void setMovimientoBancarioTipoId(TipoMovimientoBancario movimientoBancarioTipoId) {
        this.movimientoBancarioTipoId = movimientoBancarioTipoId;
    }

    public VoucherMovimiento getMovimientoBancarioVmovId() {
        return movimientoBancarioVmovId;
    }

    public void setMovimientoBancarioVmovId(VoucherMovimiento movimientoBancarioVmovId) {
        this.movimientoBancarioVmovId = movimientoBancarioVmovId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientoBancarioId != null ? movimientoBancarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoBancario)) {
            return false;
        }
        MovimientoBancario other = (MovimientoBancario) object;
        if ((this.movimientoBancarioId == null && other.movimientoBancarioId != null) || (this.movimientoBancarioId != null && !this.movimientoBancarioId.equals(other.movimientoBancarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.MovimientoBancario[ movimientoBancarioId=" + movimientoBancarioId + " ]";
    }
    
}
