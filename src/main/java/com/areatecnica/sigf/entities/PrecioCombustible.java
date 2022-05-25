/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "precio_combustible")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "PrecioCombustible.findAll", query = "SELECT p FROM PrecioCombustible p")
    , @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleId", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleId = :precioCombustibleId")
    , @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleValor", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleValor = :precioCombustibleValor")
    , @NamedQuery(name = "PrecioCombustible.findByPrecioCombustibleFechaPrecioCombustible", query = "SELECT p FROM PrecioCombustible p WHERE p.precioCombustibleFechaPrecioCombustible = :precioCombustibleFechaPrecioCombustible")})
public class PrecioCombustible extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "precio_combustible_id", nullable = false)
    private Integer precioCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_combustible_valor", nullable = false)
    private int precioCombustibleValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_combustible_fecha_precio_combustible", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date precioCombustibleFechaPrecioCombustible;
    @JoinColumn(name = "precio_combustible_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta precioCombustibleIdCuenta;
    @JoinColumn(name = "precio_combustible_id_tipo_combustible", referencedColumnName = "tipo_combustible_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoCombustible precioCombustibleIdTipoCombustible;

    public PrecioCombustible() {
    }

    public PrecioCombustible(Integer precioCombustibleId) {
        this.precioCombustibleId = precioCombustibleId;
    }

    public PrecioCombustible(Integer precioCombustibleId, int precioCombustibleValor, Date precioCombustibleFechaPrecioCombustible) {
        this.precioCombustibleId = precioCombustibleId;
        this.precioCombustibleValor = precioCombustibleValor;
        this.precioCombustibleFechaPrecioCombustible = precioCombustibleFechaPrecioCombustible;
    }

    public Integer getPrecioCombustibleId() {
        return precioCombustibleId;
    }

    public void setPrecioCombustibleId(Integer precioCombustibleId) {
        this.precioCombustibleId = precioCombustibleId;
    }

    public int getPrecioCombustibleValor() {
        return precioCombustibleValor;
    }

    public void setPrecioCombustibleValor(int precioCombustibleValor) {
        this.precioCombustibleValor = precioCombustibleValor;
    }

    public Date getPrecioCombustibleFechaPrecioCombustible() {
        return precioCombustibleFechaPrecioCombustible;
    }

    public void setPrecioCombustibleFechaPrecioCombustible(Date precioCombustibleFechaPrecioCombustible) {
        this.precioCombustibleFechaPrecioCombustible = precioCombustibleFechaPrecioCombustible;
    }

    public Cuenta getPrecioCombustibleIdCuenta() {
        return precioCombustibleIdCuenta;
    }

    public void setPrecioCombustibleIdCuenta(Cuenta precioCombustibleIdCuenta) {
        this.precioCombustibleIdCuenta = precioCombustibleIdCuenta;
    }

    public TipoCombustible getPrecioCombustibleIdTipoCombustible() {
        return precioCombustibleIdTipoCombustible;
    }

    public void setPrecioCombustibleIdTipoCombustible(TipoCombustible precioCombustibleIdTipoCombustible) {
        this.precioCombustibleIdTipoCombustible = precioCombustibleIdTipoCombustible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (precioCombustibleId != null ? precioCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioCombustible)) {
            return false;
        }
        PrecioCombustible other = (PrecioCombustible) object;
        return (this.precioCombustibleId != null || other.precioCombustibleId == null) && (this.precioCombustibleId == null || this.precioCombustibleId.equals(other.precioCombustibleId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PrecioCombustible[ precioCombustibleId=" + precioCombustibleId + " ]";
    }
    
}
