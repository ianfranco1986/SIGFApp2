/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "metodo_pago", catalog = "sigfdb_baquedano", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MetodoPago.findAll", query = "SELECT m FROM MetodoPago m")
    , @NamedQuery(name = "MetodoPago.findByMetodoPagoId", query = "SELECT m FROM MetodoPago m WHERE m.metodoPagoId = :metodoPagoId")
    , @NamedQuery(name = "MetodoPago.findByMetodoPagoNombre", query = "SELECT m FROM MetodoPago m WHERE m.metodoPagoNombre = :metodoPagoNombre")
    , @NamedQuery(name = "MetodoPago.findByMetodoPagoValor", query = "SELECT m FROM MetodoPago m WHERE m.metodoPagoValor = :metodoPagoValor")
    , @NamedQuery(name = "MetodoPago.findByMetodoPagoActivo", query = "SELECT m FROM MetodoPago m WHERE m.metodoPagoActivo = :metodoPagoActivo")})
public class MetodoPago extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "metodo_pago_id", nullable = false)
    private Integer metodoPagoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "metodo_pago_nombre", nullable = false, length = 100)
    private String metodoPagoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "metodo_pago_valor", nullable = false)
    private int metodoPagoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "metodo_pago_activo", nullable = false)
    private boolean metodoPagoActivo;

    public MetodoPago() {
    }

    public MetodoPago(Integer metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public MetodoPago(Integer metodoPagoId, String metodoPagoNombre, int metodoPagoValor, boolean metodoPagoActivo) {
        this.metodoPagoId = metodoPagoId;
        this.metodoPagoNombre = metodoPagoNombre;
        this.metodoPagoValor = metodoPagoValor;
        this.metodoPagoActivo = metodoPagoActivo;
    }

    public Integer getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Integer metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public String getMetodoPagoNombre() {
        return metodoPagoNombre;
    }

    public void setMetodoPagoNombre(String metodoPagoNombre) {
        this.metodoPagoNombre = metodoPagoNombre;
    }

    public int getMetodoPagoValor() {
        return metodoPagoValor;
    }

    public void setMetodoPagoValor(int metodoPagoValor) {
        this.metodoPagoValor = metodoPagoValor;
    }

    public boolean getMetodoPagoActivo() {
        return metodoPagoActivo;
    }

    public void setMetodoPagoActivo(boolean metodoPagoActivo) {
        this.metodoPagoActivo = metodoPagoActivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metodoPagoId != null ? metodoPagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoPago)) {
            return false;
        }
        MetodoPago other = (MetodoPago) object;
        if ((this.metodoPagoId == null && other.metodoPagoId != null) || (this.metodoPagoId != null && !this.metodoPagoId.equals(other.metodoPagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.MetodoPago[ metodoPagoId=" + metodoPagoId + " ]";
    }
    
}
