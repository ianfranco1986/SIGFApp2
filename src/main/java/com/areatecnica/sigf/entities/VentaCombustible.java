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
@Cacheable(false)
@Table(name = "venta_combustible")
@EntityListeners(AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaCombustible.findAll", query = "SELECT v FROM VentaCombustible v")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleId", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleId = :ventaCombustibleId")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleTerminalSinRecaudar", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleIdSurtidor.surtidorIdTerminal = :surtidorIdTerminal AND v.ventaCombustibleRecaudado = false ORDER BY v.ventaCombustibleNumeroBoleta ASC")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleFecha", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleFecha = :ventaCombustibleFecha ORDER BY v.ventaCombustibleNumeroBoleta DESC")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleFechaBus", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleFecha = :ventaCombustibleFecha AND v.ventaCombustibleIdBus = :ventaCombustibleIdBus")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleBetweenDates", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleFecha BETWEEN :from AND :to ORDER BY v.ventaCombustibleFecha ASC")        
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleBusBetweenDates", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleFecha BETWEEN :from AND :to AND v.ventaCombustibleIdBus = :ventaCombustibleIdBus")        
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleHora", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleHora = :ventaCombustibleHora")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustiblePrecio", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustiblePrecio = :ventaCombustiblePrecio")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleCantidad", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleCantidad = :ventaCombustibleCantidad")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleTotal", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleTotal = :ventaCombustibleTotal")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleNumeroBoleta", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleNumeroBoleta = :ventaCombustibleNumeroBoleta")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleRecaudado", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleRecaudado = :ventaCombustibleRecaudado")})
public class VentaCombustible extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_combustible_id", nullable = false)
    private Integer ventaCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ventaCombustibleFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_hora", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date ventaCombustibleHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_precio", nullable = false)
    private float ventaCombustiblePrecio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_cantidad", nullable = false)
    private float ventaCombustibleCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_total", nullable = false)
    private int ventaCombustibleTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_numero_boleta", nullable = false)
    private int ventaCombustibleNumeroBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_recaudado", nullable = false)
    private boolean ventaCombustibleRecaudado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionCombustibleIdVentaCombustible")
    private List<RecaudacionCombustible> recaudacionCombustibleList;
    @JoinColumn(name = "venta_combustible_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus ventaCombustibleIdBus;
    @JoinColumn(name = "venta_combustible_id_surtidor", referencedColumnName = "surtidor_id", nullable = false)
    @ManyToOne(optional = false)
    private Surtidor ventaCombustibleIdSurtidor;

    public VentaCombustible() {
    }

    public VentaCombustible(Integer ventaCombustibleId) {
        this.ventaCombustibleId = ventaCombustibleId;
    }

    public VentaCombustible(Integer ventaCombustibleId, Date ventaCombustibleFecha, Date ventaCombustibleHora, float ventaCombustiblePrecio, float ventaCombustibleCantidad, int ventaCombustibleTotal, int ventaCombustibleNumeroBoleta, boolean ventaCombustibleRecaudado) {
        this.ventaCombustibleId = ventaCombustibleId;
        this.ventaCombustibleFecha = ventaCombustibleFecha;
        this.ventaCombustibleHora = ventaCombustibleHora;
        this.ventaCombustiblePrecio = ventaCombustiblePrecio;
        this.ventaCombustibleCantidad = ventaCombustibleCantidad;
        this.ventaCombustibleTotal = ventaCombustibleTotal;
        this.ventaCombustibleNumeroBoleta = ventaCombustibleNumeroBoleta;
        this.ventaCombustibleRecaudado = ventaCombustibleRecaudado;
    }

    public Integer getVentaCombustibleId() {
        return ventaCombustibleId;
    }

    public void setVentaCombustibleId(Integer ventaCombustibleId) {
        this.ventaCombustibleId = ventaCombustibleId;
    }

    public Date getVentaCombustibleFecha() {
        return ventaCombustibleFecha;
    }

    public void setVentaCombustibleFecha(Date ventaCombustibleFecha) {
        this.ventaCombustibleFecha = ventaCombustibleFecha;
    }

    public Date getVentaCombustibleHora() {
        return ventaCombustibleHora;
    }

    public void setVentaCombustibleHora(Date ventaCombustibleHora) {
        this.ventaCombustibleHora = ventaCombustibleHora;
    }

    public float getVentaCombustiblePrecio() {
        return ventaCombustiblePrecio;
    }

    public void setVentaCombustiblePrecio(float ventaCombustiblePrecio) {
        this.ventaCombustiblePrecio = ventaCombustiblePrecio;
    }

    public float getVentaCombustibleCantidad() {
        return ventaCombustibleCantidad;
    }

    public void setVentaCombustibleCantidad(float ventaCombustibleCantidad) {
        this.ventaCombustibleCantidad = ventaCombustibleCantidad;
    }

    public int getVentaCombustibleTotal() {
        return ventaCombustibleTotal;
    }

    public void setVentaCombustibleTotal(int ventaCombustibleTotal) {
        this.ventaCombustibleTotal = ventaCombustibleTotal;
    }

    public int getVentaCombustibleNumeroBoleta() {
        return ventaCombustibleNumeroBoleta;
    }

    public void setVentaCombustibleNumeroBoleta(int ventaCombustibleNumeroBoleta) {
        this.ventaCombustibleNumeroBoleta = ventaCombustibleNumeroBoleta;
    }

    public boolean getVentaCombustibleRecaudado() {
        return ventaCombustibleRecaudado;
    }

    public void setVentaCombustibleRecaudado(boolean ventaCombustibleRecaudado) {
        this.ventaCombustibleRecaudado = ventaCombustibleRecaudado;
    }

    @XmlTransient
    public List<RecaudacionCombustible> getRecaudacionCombustibleList() {
        return recaudacionCombustibleList;
    }

    public void setRecaudacionCombustibleList(List<RecaudacionCombustible> recaudacionCombustibleList) {
        this.recaudacionCombustibleList = recaudacionCombustibleList;
    }

    public Bus getVentaCombustibleIdBus() {
        return ventaCombustibleIdBus;
    }

    public void setVentaCombustibleIdBus(Bus ventaCombustibleIdBus) {
        this.ventaCombustibleIdBus = ventaCombustibleIdBus;
    }

    public Surtidor getVentaCombustibleIdSurtidor() {
        return ventaCombustibleIdSurtidor;
    }

    public void setVentaCombustibleIdSurtidor(Surtidor ventaCombustibleIdSurtidor) {
        this.ventaCombustibleIdSurtidor = ventaCombustibleIdSurtidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaCombustibleId != null ? ventaCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaCombustible)) {
            return false;
        }
        VentaCombustible other = (VentaCombustible) object;
        return (this.ventaCombustibleId != null || other.ventaCombustibleId == null) && (this.ventaCombustibleId == null || this.ventaCombustibleId.equals(other.ventaCombustibleId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VentaCombustible[ ventaCombustibleId=" + ventaCombustibleId + " ]";
    }
    
}
