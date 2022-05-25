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
@Table(name = "hora_extra_trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoraExtraTrabajador.findAll", query = "SELECT h FROM HoraExtraTrabajador h")
    , @NamedQuery(name = "HoraExtraTrabajador.findByHoraExtraTrabajadorId", query = "SELECT h FROM HoraExtraTrabajador h WHERE h.horaExtraTrabajadorId = :horaExtraTrabajadorId")
    , @NamedQuery(name = "HoraExtraTrabajador.findByHoraExtraTrabajadorCantidad", query = "SELECT h FROM HoraExtraTrabajador h WHERE h.horaExtraTrabajadorCantidad = :horaExtraTrabajadorCantidad")
    , @NamedQuery(name = "HoraExtraTrabajador.findByHoraExtraTrabajadorValor", query = "SELECT h FROM HoraExtraTrabajador h WHERE h.horaExtraTrabajadorValor = :horaExtraTrabajadorValor")
    , @NamedQuery(name = "HoraExtraTrabajador.findByHoraExtraTrabajadorFecha", query = "SELECT h FROM HoraExtraTrabajador h WHERE h.horaExtraTrabajadorFecha = :horaExtraTrabajadorFecha")})
public class HoraExtraTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hora_extra_trabajador_id", nullable = false)
    private Integer horaExtraTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_extra_trabajador_cantidad", nullable = false)
    private int horaExtraTrabajadorCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_extra_trabajador_valor", nullable = false)
    private int horaExtraTrabajadorValor;
    @Column(name = "hora_extra_trabajador_fecha")
    @Temporal(TemporalType.DATE)
    private Date horaExtraTrabajadorFecha;
    @JoinColumn(name = "hora_extra_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador horaExtraTrabajadorIdTrabajador;

    public HoraExtraTrabajador() {
    }

    public HoraExtraTrabajador(Integer horaExtraTrabajadorId) {
        this.horaExtraTrabajadorId = horaExtraTrabajadorId;
    }

    public HoraExtraTrabajador(Integer horaExtraTrabajadorId, int horaExtraTrabajadorCantidad, int horaExtraTrabajadorValor) {
        this.horaExtraTrabajadorId = horaExtraTrabajadorId;
        this.horaExtraTrabajadorCantidad = horaExtraTrabajadorCantidad;
        this.horaExtraTrabajadorValor = horaExtraTrabajadorValor;
    }

    public Integer getHoraExtraTrabajadorId() {
        return horaExtraTrabajadorId;
    }

    public void setHoraExtraTrabajadorId(Integer horaExtraTrabajadorId) {
        this.horaExtraTrabajadorId = horaExtraTrabajadorId;
    }

    public int getHoraExtraTrabajadorCantidad() {
        return horaExtraTrabajadorCantidad;
    }

    public void setHoraExtraTrabajadorCantidad(int horaExtraTrabajadorCantidad) {
        this.horaExtraTrabajadorCantidad = horaExtraTrabajadorCantidad;
    }

    public int getHoraExtraTrabajadorValor() {
        return horaExtraTrabajadorValor;
    }

    public void setHoraExtraTrabajadorValor(int horaExtraTrabajadorValor) {
        this.horaExtraTrabajadorValor = horaExtraTrabajadorValor;
    }

    public Date getHoraExtraTrabajadorFecha() {
        return horaExtraTrabajadorFecha;
    }

    public void setHoraExtraTrabajadorFecha(Date horaExtraTrabajadorFecha) {
        this.horaExtraTrabajadorFecha = horaExtraTrabajadorFecha;
    }

    public Trabajador getHoraExtraTrabajadorIdTrabajador() {
        return horaExtraTrabajadorIdTrabajador;
    }

    public void setHoraExtraTrabajadorIdTrabajador(Trabajador horaExtraTrabajadorIdTrabajador) {
        this.horaExtraTrabajadorIdTrabajador = horaExtraTrabajadorIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horaExtraTrabajadorId != null ? horaExtraTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoraExtraTrabajador)) {
            return false;
        }
        HoraExtraTrabajador other = (HoraExtraTrabajador) object;
        return (this.horaExtraTrabajadorId != null || other.horaExtraTrabajadorId == null) && (this.horaExtraTrabajadorId == null || this.horaExtraTrabajadorId.equals(other.horaExtraTrabajadorId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.HoraExtraTrabajador[ horaExtraTrabajadorId=" + horaExtraTrabajadorId + " ]";
    }
    
}
