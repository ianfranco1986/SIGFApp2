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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "registro_minuto")
@EntityListeners(AuditListener.class)
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RegistroMinuto.findAll", query = "SELECT r FROM RegistroMinuto r"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoDesdeIdBus", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoDesdeIdBus = :registroMinutoDesdeIdBus AND r.registroMinutoFechaMinuto BETWEEN :from AND :to ORDER BY r.registroMinutoFechaMinuto ASC"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoId", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoId = :registroMinutoId"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoFechaMinuto", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoFechaMinuto = :registroMinutoFechaMinuto"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoDesdeSinRecaudar", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoDesdeIdBus = :registroMinutoDesdeIdBus AND r.registroMinutoRecaudado = false ORDER BY r.registroMinutoFechaMinuto ASC"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoCantidad", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoCantidad = :registroMinutoCantidad"),
    @NamedQuery(name = "Re0gistroMinuto.findByRegistroMinutoMonto", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoMonto = :registroMinutoMonto"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoFechaIngreso", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoFechaIngreso = :registroMinutoFechaIngreso"),
    @NamedQuery(name = "RegistroMinuto.findByRegistroMinutoRecaudado", query = "SELECT r FROM RegistroMinuto r WHERE r.registroMinutoRecaudado = :registroMinutoRecaudado")})
public class RegistroMinuto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registro_minuto_id", nullable = false)
    private Integer registroMinutoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_minuto_fecha_minuto", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registroMinutoFechaMinuto;
    @Column(name = "registro_minuto_cantidad")
    private Integer registroMinutoCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_minuto_monto", nullable = false)
    private int registroMinutoMonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_minuto_fecha_ingreso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroMinutoFechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_minuto_recaudado", nullable = false)
    private boolean registroMinutoRecaudado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionMinutoIdRegistroMinuto", fetch = FetchType.LAZY)
    private List<RecaudacionMinuto> recaudacionMinutoList;
    @JoinColumn(name = "registro_minuto_desde_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bus registroMinutoDesdeIdBus;
    @JoinColumn(name = "registro_minuto_hasta_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bus registroMinutoHastaIdBus;

    public RegistroMinuto() {
    }

    public RegistroMinuto(Integer registroMinutoId) {
        this.registroMinutoId = registroMinutoId;
    }

    public RegistroMinuto(Integer registroMinutoId, Date registroMinutoFechaMinuto, int registroMinutoMonto, Date registroMinutoFechaIngreso, boolean registroMinutoRecaudado) {
        this.registroMinutoId = registroMinutoId;
        this.registroMinutoFechaMinuto = registroMinutoFechaMinuto;
        this.registroMinutoMonto = registroMinutoMonto;
        this.registroMinutoFechaIngreso = registroMinutoFechaIngreso;
        this.registroMinutoRecaudado = registroMinutoRecaudado;
    }

    public Integer getRegistroMinutoId() {
        return registroMinutoId;
    }

    public void setRegistroMinutoId(Integer registroMinutoId) {
        this.registroMinutoId = registroMinutoId;
    }

    public Date getRegistroMinutoFechaMinuto() {
        return registroMinutoFechaMinuto;
    }

    public void setRegistroMinutoFechaMinuto(Date registroMinutoFechaMinuto) {
        this.registroMinutoFechaMinuto = registroMinutoFechaMinuto;
    }

    public Integer getRegistroMinutoCantidad() {
        return registroMinutoCantidad;
    }

    public void setRegistroMinutoCantidad(Integer registroMinutoCantidad) {
        this.registroMinutoCantidad = registroMinutoCantidad;
    }

    public int getRegistroMinutoMonto() {
        return registroMinutoMonto;
    }

    public void setRegistroMinutoMonto(int registroMinutoMonto) {
        this.registroMinutoMonto = registroMinutoMonto;
    }

    public Date getRegistroMinutoFechaIngreso() {
        return registroMinutoFechaIngreso;
    }

    public void setRegistroMinutoFechaIngreso(Date registroMinutoFechaIngreso) {
        this.registroMinutoFechaIngreso = registroMinutoFechaIngreso;
    }

    public boolean getRegistroMinutoRecaudado() {
        return registroMinutoRecaudado;
    }

    public void setRegistroMinutoRecaudado(boolean registroMinutoRecaudado) {
        this.registroMinutoRecaudado = registroMinutoRecaudado;
    }

    @XmlTransient
    public List<RecaudacionMinuto> getRecaudacionMinutoList() {
        return recaudacionMinutoList;
    }

    public void setRecaudacionMinutoList(List<RecaudacionMinuto> recaudacionMinutoList) {
        this.recaudacionMinutoList = recaudacionMinutoList;
    }

    public Bus getRegistroMinutoDesdeIdBus() {
        return registroMinutoDesdeIdBus;
    }

    public void setRegistroMinutoDesdeIdBus(Bus registroMinutoDesdeIdBus) {
        this.registroMinutoDesdeIdBus = registroMinutoDesdeIdBus;
    }

    public Bus getRegistroMinutoHastaIdBus() {
        return registroMinutoHastaIdBus;
    }

    public void setRegistroMinutoHastaIdBus(Bus registroMinutoHastaIdBus) {
        this.registroMinutoHastaIdBus = registroMinutoHastaIdBus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroMinutoId != null ? registroMinutoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroMinuto)) {
            return false;
        }
        RegistroMinuto other = (RegistroMinuto) object;
        return (this.registroMinutoId != null || other.registroMinutoId == null) && (this.registroMinutoId == null || this.registroMinutoId.equals(other.registroMinutoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RegistroMinuto[ registroMinutoId=" + registroMinutoId + " ]";
    }

}
