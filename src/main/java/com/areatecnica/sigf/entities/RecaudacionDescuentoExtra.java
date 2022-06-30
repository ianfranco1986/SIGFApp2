/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "recaudacion_descuento_extra")
@XmlRootElement
@EntityListeners(AuditListener.class)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RecaudacionDescuentoExtra.findAll", query = "SELECT r FROM RecaudacionDescuentoExtra r")
    , @NamedQuery(name = "RecaudacionDescuentoExtra.findByRecaudacionDescuentoExtraId", query = "SELECT r FROM RecaudacionDescuentoExtra r WHERE r.recaudacionDescuentoExtraId = :recaudacionDescuentoExtraId")})
public class RecaudacionDescuentoExtra extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_descuento_extra_id", nullable = false)
    private Integer recaudacionDescuentoExtraId;
    @JoinColumn(name = "recaudacion_descuento_extra_id_dcto", referencedColumnName = "descuento_extra_bus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DescuentoExtraBus recaudacionDescuentoExtraIdDcto;
    @JoinColumn(name = "recaudacion_descuento_extra_id_recaudacion", referencedColumnName = "recaudacion_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recaudacion recaudacionDescuentoExtraIdRecaudacion;

    public RecaudacionDescuentoExtra() {
    }

    public RecaudacionDescuentoExtra(Integer recaudacionDescuentoExtraId) {
        this.recaudacionDescuentoExtraId = recaudacionDescuentoExtraId;
    }

    public Integer getRecaudacionDescuentoExtraId() {
        return recaudacionDescuentoExtraId;
    }

    public void setRecaudacionDescuentoExtraId(Integer recaudacionDescuentoExtraId) {
        this.recaudacionDescuentoExtraId = recaudacionDescuentoExtraId;
    }

    public DescuentoExtraBus getRecaudacionDescuentoExtraIdDcto() {
        return recaudacionDescuentoExtraIdDcto;
    }

    public void setRecaudacionDescuentoExtraIdDcto(DescuentoExtraBus recaudacionDescuentoExtraIdDcto) {
        this.recaudacionDescuentoExtraIdDcto = recaudacionDescuentoExtraIdDcto;
    }

    public Recaudacion getRecaudacionDescuentoExtraIdRecaudacion() {
        return recaudacionDescuentoExtraIdRecaudacion;
    }

    public void setRecaudacionDescuentoExtraIdRecaudacion(Recaudacion recaudacionDescuentoExtraIdRecaudacion) {
        this.recaudacionDescuentoExtraIdRecaudacion = recaudacionDescuentoExtraIdRecaudacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionDescuentoExtraId != null ? recaudacionDescuentoExtraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionDescuentoExtra)) {
            return false;
        }
        RecaudacionDescuentoExtra other = (RecaudacionDescuentoExtra) object;
        return (this.recaudacionDescuentoExtraId != null || other.recaudacionDescuentoExtraId == null) && (this.recaudacionDescuentoExtraId == null || this.recaudacionDescuentoExtraId.equals(other.recaudacionDescuentoExtraId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionDescuentoExtra[ recaudacionDescuentoExtraId=" + recaudacionDescuentoExtraId + " ]";
    }
    
}
