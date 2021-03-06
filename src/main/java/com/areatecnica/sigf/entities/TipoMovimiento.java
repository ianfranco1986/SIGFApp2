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
@Cacheable(false)
@Table(name = "tipo_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMovimiento.findAll", query = "SELECT t FROM TipoMovimiento t ORDER BY t.tipoMovimientoNombre ASC")
    , @NamedQuery(name = "TipoMovimiento.findByTipoMovimientoId", query = "SELECT t FROM TipoMovimiento t WHERE t.tipoMovimientoId = :tipoMovimientoId")
    , @NamedQuery(name = "TipoMovimiento.findByTipoMovimientoAbono", query = "SELECT t FROM TipoMovimiento t WHERE t.tipoMovimientoAbono = true ORDER BY t.tipoMovimientoNombre")
    , @NamedQuery(name = "TipoMovimiento.findByTipoMovimientoDescuento", query = "SELECT t FROM TipoMovimiento t WHERE t.tipoMovimientoDescuento = true ORDER BY t.tipoMovimientoNombre")
    , @NamedQuery(name = "TipoMovimiento.findByTipoMovimientoNombre", query = "SELECT t FROM TipoMovimiento t WHERE t.tipoMovimientoNombre = :tipoMovimientoNombre")
    , @NamedQuery(name = "TipoMovimiento.findByTipoMovimientoMontoDefecto", query = "SELECT t FROM TipoMovimiento t WHERE t.tipoMovimientoMontoDefecto = :tipoMovimientoMontoDefecto")})
public class TipoMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_movimiento_id", nullable = false)
    private Integer tipoMovimientoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_movimiento_nombre", nullable = false, length = 45)
    private String tipoMovimientoNombre;
    @Column(name = "tipo_movimiento_descripcion")
    private String tipoMovimientoDescripcion;
    @Column(name = "tipo_movimiento_monto_defecto")
    private Integer tipoMovimientoMontoDefecto;
    @Column(name = "tipo_movimiento_abono")
    private Boolean tipoMovimientoAbono;
    @Column(name = "tipo_movimiento_descuento")
    private Boolean tipoMovimientoDescuento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoMesTipoMvtoId")
    private List<MovimientoMes> movimientomMesList;

    public TipoMovimiento() {
    }

    public TipoMovimiento(Integer tipoMovimientoId) {
        this.tipoMovimientoId = tipoMovimientoId;
    }

    public TipoMovimiento(Integer tipoMovimientoId, String tipoMovimientoNombre) {
        this.tipoMovimientoId = tipoMovimientoId;
        this.tipoMovimientoNombre = tipoMovimientoNombre;
    }

    public TipoMovimiento(Integer tipoMovimientoId, String tipoMovimientoNombre, String tipoMovimientoDescripcion, Integer tipoMovimientoMontoDefecto, boolean tipoMovimientoAbono, boolean tipoMovimientoDescuento) {
        this.tipoMovimientoId = tipoMovimientoId;
        this.tipoMovimientoNombre = tipoMovimientoNombre;
        this.tipoMovimientoDescripcion = tipoMovimientoDescripcion;
        this.tipoMovimientoMontoDefecto = tipoMovimientoMontoDefecto;
        this.tipoMovimientoAbono = tipoMovimientoAbono;
        this.tipoMovimientoDescuento = tipoMovimientoDescuento;
    }

    public Integer getTipoMovimientoId() {
        return tipoMovimientoId;
    }

    public void setTipoMovimientoId(Integer tipoMovimientoId) {
        this.tipoMovimientoId = tipoMovimientoId;
    }

    public String getTipoMovimientoNombre() {
        return tipoMovimientoNombre;
    }

    public void setTipoMovimientoNombre(String tipoMovimientoNombre) {
        this.tipoMovimientoNombre = tipoMovimientoNombre;
    }

    public void setTipoMovimientoDescripcion(String tipoMovimientoDescripcion) {
        this.tipoMovimientoDescripcion = tipoMovimientoDescripcion;
    }

    public String getTipoMovimientoDescripcion() {
        return tipoMovimientoDescripcion;
    }

    public Boolean getTipoMovimientoDescuento() {
        return tipoMovimientoDescuento;
    }

    public void setTipoMovimientoDescuento(Boolean tipoMovimientoDescuento) {
        this.tipoMovimientoDescuento = tipoMovimientoDescuento;
    }

    public Boolean getTipoMovimientoAbono() {
        return tipoMovimientoAbono;
    }

    public void setTipoMovimientoAbono(Boolean tipoMovimientoAbono) {
        this.tipoMovimientoAbono = tipoMovimientoAbono;
    }

    public Integer getTipoMovimientoMontoDefecto() {
        return tipoMovimientoMontoDefecto;
    }

    public void setTipoMovimientoMontoDefecto(Integer tipoMovimientoMontoDefecto) {
        this.tipoMovimientoMontoDefecto = tipoMovimientoMontoDefecto;
    }

    @XmlTransient
    public List<MovimientoMes> getMovimientomMesList() {
        return movimientomMesList;
    }

    public void setMovimientomMesList(List<MovimientoMes> movimientomMesList) {
        this.movimientomMesList = movimientomMesList;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoMovimientoId != null ? tipoMovimientoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimiento)) {
            return false;
        }
        TipoMovimiento other = (TipoMovimiento) object;
        return (this.tipoMovimientoId != null || other.tipoMovimientoId == null) && (this.tipoMovimientoId == null || this.tipoMovimientoId.equals(other.tipoMovimientoId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoMovimiento[ tipoMovimientoId=" + tipoMovimientoId + " ]";
    }

}
