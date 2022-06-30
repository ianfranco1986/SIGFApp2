/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "recaudacion_boleto")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RecaudacionBoleto.findAll", query = "SELECT r FROM RecaudacionBoleto r")
    , @NamedQuery(name = "RecaudacionBoleto.findByRecaudacionBoletoId", query = "SELECT r FROM RecaudacionBoleto r WHERE r.recaudacionBoletoId = :recaudacionBoletoId")
    , @NamedQuery(name = "RecaudacionBoleto.findByRecaudacionBoletoMonto", query = "SELECT r FROM RecaudacionBoleto r WHERE r.recaudacionBoletoMonto = :recaudacionBoletoMonto")})
public class RecaudacionBoleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_boleto_id", nullable = false)
    private Integer recaudacionBoletoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_boleto_monto", nullable = false)
    private int recaudacionBoletoMonto;
    @JoinColumn(name = "recaudacion_boleto_id_recaudacion", referencedColumnName = "recaudacion_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recaudacion recaudacionBoletoIdRecaudacion;
    @JoinColumn(name = "recaudacion_boleto_id_venta_boleto", referencedColumnName = "venta_boleto_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VentaBoleto recaudacionBoletoIdVentaBoleto;

    public RecaudacionBoleto() {
    }

    public RecaudacionBoleto(Integer recaudacionBoletoId) {
        this.recaudacionBoletoId = recaudacionBoletoId;
    }

    public RecaudacionBoleto(Integer recaudacionBoletoId, int recaudacionBoletoMonto) {
        this.recaudacionBoletoId = recaudacionBoletoId;
        this.recaudacionBoletoMonto = recaudacionBoletoMonto;
    }

    public Integer getRecaudacionBoletoId() {
        return recaudacionBoletoId;
    }

    public void setRecaudacionBoletoId(Integer recaudacionBoletoId) {
        this.recaudacionBoletoId = recaudacionBoletoId;
    }

    public int getRecaudacionBoletoMonto() {
        return recaudacionBoletoMonto;
    }

    public void setRecaudacionBoletoMonto(int recaudacionBoletoMonto) {
        this.recaudacionBoletoMonto = recaudacionBoletoMonto;
    }

    public Recaudacion getRecaudacionBoletoIdRecaudacion() {
        return recaudacionBoletoIdRecaudacion;
    }

    public void setRecaudacionBoletoIdRecaudacion(Recaudacion recaudacionBoletoIdRecaudacion) {
        this.recaudacionBoletoIdRecaudacion = recaudacionBoletoIdRecaudacion;
    }

    public VentaBoleto getRecaudacionBoletoIdVentaBoleto() {
        return recaudacionBoletoIdVentaBoleto;
    }

    public void setRecaudacionBoletoIdVentaBoleto(VentaBoleto recaudacionBoletoIdVentaBoleto) {
        this.recaudacionBoletoIdVentaBoleto = recaudacionBoletoIdVentaBoleto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionBoletoId != null ? recaudacionBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionBoleto)) {
            return false;
        }
        RecaudacionBoleto other = (RecaudacionBoleto) object;
        return (this.recaudacionBoletoId != null || other.recaudacionBoletoId == null) && (this.recaudacionBoletoId == null || this.recaudacionBoletoId.equals(other.recaudacionBoletoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionBoleto[ recaudacionBoletoId=" + recaudacionBoletoId + " ]";
    }
    
}
