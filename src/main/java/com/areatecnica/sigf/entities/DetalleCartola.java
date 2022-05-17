/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_cartola")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCartola.findAll", query = "SELECT d FROM DetalleCartola d")
    , @NamedQuery(name = "DetalleCartola.findByDetalleCartolaId", query = "SELECT d FROM DetalleCartola d WHERE d.detalleCartolaId = :detalleCartolaId")
    , @NamedQuery(name = "DetalleCartola.findByDetalleCartolaFechaMovimiento", query = "SELECT d FROM DetalleCartola d WHERE d.detalleCartolaFechaMovimiento = :detalleCartolaFechaMovimiento")
    , @NamedQuery(name = "DetalleCartola.findByDetalleCartolaNumeroDocumento", query = "SELECT d FROM DetalleCartola d WHERE d.detalleCartolaNumeroDocumento = :detalleCartolaNumeroDocumento")
    , @NamedQuery(name = "DetalleCartola.findByDetalleCartolaDescripcion", query = "SELECT d FROM DetalleCartola d WHERE d.detalleCartolaDescripcion = :detalleCartolaDescripcion")
    , @NamedQuery(name = "DetalleCartola.findByDetalleCartolaAbono", query = "SELECT d FROM DetalleCartola d WHERE d.detalleCartolaAbono = :detalleCartolaAbono")
    , @NamedQuery(name = "DetalleCartola.findByDetalleCartolaCargo", query = "SELECT d FROM DetalleCartola d WHERE d.detalleCartolaCargo = :detalleCartolaCargo")})
public class DetalleCartola extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_cartola_id")
    private Integer detalleCartolaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_cartola_fecha_movimiento")
    @Temporal(TemporalType.DATE)
    private Date detalleCartolaFechaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "detalle_cartola_numero_documento")
    private String detalleCartolaNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "detalle_cartola_descripcion")
    private String detalleCartolaDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_cartola_abono")
    private int detalleCartolaAbono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_cartola_cargo")
    private int detalleCartolaCargo;
    @JoinColumn(name = "detalle_cartola_cartola_banco_id", referencedColumnName = "cartola_banco_id")
    @ManyToOne(optional = false)
    private CartolaBanco detalleCartolaCartolaBancoId;
    @Transient
    private int saldo;

    public DetalleCartola() {
    }

    public DetalleCartola(Integer detalleCartolaId) {
        this.detalleCartolaId = detalleCartolaId;
    }

    public DetalleCartola(Integer detalleCartolaId, Date detalleCartolaFechaMovimiento, String detalleCartolaNumeroDocumento, String detalleCartolaDescripcion, int detalleCartolaAbono, int detalleCartolaCargo) {
        this.detalleCartolaId = detalleCartolaId;
        this.detalleCartolaFechaMovimiento = detalleCartolaFechaMovimiento;
        this.detalleCartolaNumeroDocumento = detalleCartolaNumeroDocumento;
        this.detalleCartolaDescripcion = detalleCartolaDescripcion;
        this.detalleCartolaAbono = detalleCartolaAbono;
        this.detalleCartolaCargo = detalleCartolaCargo;
    }

    public Integer getDetalleCartolaId() {
        return detalleCartolaId;
    }

    public void setDetalleCartolaId(Integer detalleCartolaId) {
        this.detalleCartolaId = detalleCartolaId;
    }

    public Date getDetalleCartolaFechaMovimiento() {
        return detalleCartolaFechaMovimiento;
    }

    public void setDetalleCartolaFechaMovimiento(Date detalleCartolaFechaMovimiento) {
        this.detalleCartolaFechaMovimiento = detalleCartolaFechaMovimiento;
    }

    public String getDetalleCartolaNumeroDocumento() {
        return detalleCartolaNumeroDocumento;
    }

    public void setDetalleCartolaNumeroDocumento(String detalleCartolaNumeroDocumento) {
        this.detalleCartolaNumeroDocumento = detalleCartolaNumeroDocumento;
    }

    public String getDetalleCartolaDescripcion() {
        return detalleCartolaDescripcion;
    }

    public void setDetalleCartolaDescripcion(String detalleCartolaDescripcion) {
        this.detalleCartolaDescripcion = detalleCartolaDescripcion;
    }

    public int getDetalleCartolaAbono() {
        return detalleCartolaAbono;
    }

    public void setDetalleCartolaAbono(int detalleCartolaAbono) {
        this.detalleCartolaAbono = detalleCartolaAbono;
    }

    public int getDetalleCartolaCargo() {
        return detalleCartolaCargo;
    }

    public void setDetalleCartolaCargo(int detalleCartolaCargo) {
        this.detalleCartolaCargo = detalleCartolaCargo;
    }

    public CartolaBanco getDetalleCartolaCartolaBancoId() {
        return detalleCartolaCartolaBancoId;
    }

    public void setDetalleCartolaCartolaBancoId(CartolaBanco detalleCartolaCartolaBancoId) {
        this.detalleCartolaCartolaBancoId = detalleCartolaCartolaBancoId;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCartolaId != null ? detalleCartolaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCartola)) {
            return false;
        }
        DetalleCartola other = (DetalleCartola) object;
        if ((this.detalleCartolaId == null && other.detalleCartolaId != null) || (this.detalleCartolaId != null && !this.detalleCartolaId.equals(other.detalleCartolaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleCartola[ detalleCartolaId=" + detalleCartolaId + " ]";
    }

}
