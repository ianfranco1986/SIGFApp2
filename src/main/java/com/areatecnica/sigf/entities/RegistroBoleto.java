/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Cacheable(false)
@EntityListeners(AuditListener.class)
@Table(name = "registro_boleto", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroBoleto.findAll", query = "SELECT r FROM RegistroBoleto r")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoId", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoId = :registroBoletoId")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoSerie", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoSerie = :registroBoletoSerie")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoValor", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoValor = :registroBoletoValor")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoInicio", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoInicio = :registroBoletoInicio")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoTermino", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoTermino = :registroBoletoTermino")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoCantidad", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoCantidad = :registroBoletoCantidad")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoTotal", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoTotal = :registroBoletoTotal")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoEsNuevo", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoEsNuevo = :registroBoletoEsNuevo")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoObservacion", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoObservacion = :registroBoletoObservacion")})
public class RegistroBoleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registro_boleto_id", nullable = false)
    private Integer registroBoletoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_boleto_serie", nullable = false)
    private int registroBoletoSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_boleto_valor", nullable = false)
    private int registroBoletoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_boleto_inicio", nullable = false)
    private int registroBoletoInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_boleto_termino", nullable = false)
    private int registroBoletoTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_boleto_cantidad", nullable = false)
    private int registroBoletoCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_boleto_total", nullable = false)
    private int registroBoletoTotal;
    @Column(name = "registro_boleto_es_nuevo")
    private Boolean registroBoletoEsNuevo;
    @Size(max = 200)
    @Column(name = "registro_boleto_observacion", length = 200)
    private String registroBoletoObservacion;
    @JoinColumn(name = "registro_boleto_id_boleto", referencedColumnName = "boleto_id", nullable = false)
    @ManyToOne(optional = false)
    private Boleto registroBoletoIdBoleto;    
    @JoinColumn(name = "registro_boleto_id_vuelta_guia", referencedColumnName = "vuelta_guia_id", nullable = false)
    @ManyToOne(optional = false)
    private VueltaGuia registroBoletoIdVueltaGuia;

    public RegistroBoleto() {
    }

    public RegistroBoleto(Integer registroBoletoId) {
        this.registroBoletoId = registroBoletoId;
    }

    public RegistroBoleto(Integer registroBoletoId, int registroBoletoSerie, int registroBoletoValor, int registroBoletoInicio, int registroBoletoTermino, int registroBoletoCantidad, int registroBoletoTotal) {
        this.registroBoletoId = registroBoletoId;
        this.registroBoletoSerie = registroBoletoSerie;
        this.registroBoletoValor = registroBoletoValor;
        this.registroBoletoInicio = registroBoletoInicio;
        this.registroBoletoTermino = registroBoletoTermino;
        this.registroBoletoCantidad = registroBoletoCantidad;
        this.registroBoletoTotal = registroBoletoTotal;
    }

    public RegistroBoleto(int registroBoletoTermino, int registroBoletoInicio, int registroBoletoValor) {
        this.registroBoletoTermino = registroBoletoTermino;
        this.registroBoletoInicio = registroBoletoInicio;
        this.registroBoletoValor = registroBoletoValor;
        this.registroBoletoCantidad = this.registroBoletoTermino - this.registroBoletoInicio;
        this.registroBoletoTotal = this.registroBoletoCantidad * this.registroBoletoValor;
    }

    public Integer getRegistroBoletoId() {
        return registroBoletoId;
    }

    public void setRegistroBoletoId(Integer registroBoletoId) {
        this.registroBoletoId = registroBoletoId;
    }

    public int getRegistroBoletoSerie() {
        return registroBoletoSerie;
    }

    public void setRegistroBoletoSerie(int registroBoletoSerie) {
        this.registroBoletoSerie = registroBoletoSerie;
    }

    public int getRegistroBoletoValor() {
        return registroBoletoValor;
    }

    public void setRegistroBoletoValor(int registroBoletoValor) {
        this.registroBoletoValor = registroBoletoValor;
    }

    public int getRegistroBoletoInicio() {
        return registroBoletoInicio;
    }

    public void setRegistroBoletoInicio(int registroBoletoInicio) {
        this.registroBoletoInicio = registroBoletoInicio;
    }

    public int getRegistroBoletoTermino() {
        return registroBoletoTermino;
    }

    public void setRegistroBoletoTermino(int registroBoletoTermino) {
        this.registroBoletoTermino = registroBoletoTermino;
    }

    public int getRegistroBoletoCantidad() {
        return registroBoletoCantidad;
    }

    public void setRegistroBoletoCantidad(int registroBoletoCantidad) {
        this.registroBoletoCantidad = registroBoletoCantidad;
    }

    public int getRegistroBoletoTotal() {
        return registroBoletoTotal;
    }

    public void setRegistroBoletoTotal(int registroBoletoTotal) {
        this.registroBoletoTotal = registroBoletoTotal;
    }

    public Boolean getRegistroBoletoEsNuevo() {
        return registroBoletoEsNuevo;
    }

    public void setRegistroBoletoEsNuevo(Boolean registroBoletoEsNuevo) {
        this.registroBoletoEsNuevo = registroBoletoEsNuevo;
    }

    public String getRegistroBoletoObservacion() {
        return registroBoletoObservacion;
    }

    public void setRegistroBoletoObservacion(String registroBoletoObservacion) {
        this.registroBoletoObservacion = registroBoletoObservacion;
    }

    public Boleto getRegistroBoletoIdBoleto() {
        return registroBoletoIdBoleto;
    }

    public void setRegistroBoletoIdBoleto(Boleto registroBoletoIdBoleto) {
        this.registroBoletoIdBoleto = registroBoletoIdBoleto;
    }
    
    public VueltaGuia getRegistroBoletoIdVueltaGuia() {
        return registroBoletoIdVueltaGuia;
    }

    public void setRegistroBoletoIdVueltaGuia(VueltaGuia registroBoletoIdVueltaGuia) {
        this.registroBoletoIdVueltaGuia = registroBoletoIdVueltaGuia;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroBoletoId != null ? registroBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroBoleto)) {
            return false;
        }
        RegistroBoleto other = (RegistroBoleto) object;
        if ((this.registroBoletoId == null && other.registroBoletoId != null) || (this.registroBoletoId != null && !this.registroBoletoId.equals(other.registroBoletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RegistroBoleto[ registroBoletoId=" + registroBoletoId + " ]";
    }

}
