/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

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
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByCuentaId", query = "SELECT c FROM Cuenta c WHERE c.cuentaId = :cuentaId"),
    @NamedQuery(name = "Cuenta.findByCuentaAdministrador", query = "SELECT c FROM Cuenta c WHERE c.cuentaAdministrador = :cuentaAdministrador"),
    @NamedQuery(name = "Cuenta.findByCuentaRut", query = "SELECT c FROM Cuenta c WHERE c.cuentaRut = :cuentaRut"),
    @NamedQuery(name = "Cuenta.findByCuentaActiva", query = "SELECT c FROM Cuenta c WHERE c.cuentaActiva = :cuentaActiva")})
public class Cuenta extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_id", nullable = false)
    private Integer cuentaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cuenta_administrador", nullable = false, length = 200)
    private String cuentaAdministrador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "cuenta_rut", nullable = false, length = 9)
    private String cuentaRut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_activa", nullable = false)
    private boolean cuentaActiva;

    @JoinColumn(name = "cuenta_id_tipo", referencedColumnName = "tipo_cuenta_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCuenta cuentaIdTipo;

    public Cuenta() {
    }

    public Cuenta(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Cuenta(Integer cuentaId, String cuentaAdministrador, String cuentaRut, boolean cuentaActiva) {
        this.cuentaId = cuentaId;
        this.cuentaAdministrador = cuentaAdministrador;
        this.cuentaRut = cuentaRut;
        this.cuentaActiva = cuentaActiva;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getCuentaAdministrador() {
        return cuentaAdministrador;
    }

    public void setCuentaAdministrador(String cuentaAdministrador) {
        this.cuentaAdministrador = cuentaAdministrador;
    }

    public String getCuentaRut() {
        return cuentaRut;
    }

    public void setCuentaRut(String cuentaRut) {
        this.cuentaRut = cuentaRut;
    }

    public boolean getCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    public TipoCuenta getCuentaIdTipo() {
        return cuentaIdTipo;
    }

    public void setCuentaIdTipo(TipoCuenta cuentaIdTipo) {
        this.cuentaIdTipo = cuentaIdTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaId != null ? cuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        return (this.cuentaId != null || other.cuentaId == null) && (this.cuentaId == null || this.cuentaId.equals(other.cuentaId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Cuenta[ cuentaId=" + cuentaId + " ]";
    }

}
